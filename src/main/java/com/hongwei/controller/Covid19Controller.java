package com.hongwei.controller;

import com.google.gson.Gson;
import com.hongwei.model.arcgis.covid19.Attributes;
import com.hongwei.model.arcgis.covid19.BeanByAuState;
import com.hongwei.model.arcgis.covid19.Features;
import com.hongwei.model.covid19.AusDataByState;
import com.hongwei.model.covid19.AusDataByStatePerDay;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@RestController
@RequestMapping("/covid19")
@CrossOrigin
public class Covid19Controller {
    @RequestMapping(path = "/querybystate.do")
    @ResponseBody
    public String getAuDataByState(int days) {
        final String uri = "https://services1.arcgis.com/vHnIGBHHqDR6y0CR/arcgis/rest/services/COVID19_Time_Series/FeatureServer/0/query?where=1=1&outFields=*&outSR=4326&f=json";

        RestTemplate restTemplate = new RestTemplate();
        String json = restTemplate.getForObject(uri, String.class);

        BeanByAuState data = new Gson().fromJson(json, BeanByAuState.class);
        Features[] features = data.features;

        AusDataByState outData = parseFeatures(features, days);
        return new Gson().toJson(outData);
    }

    private AusDataByState parseFeatures(Features[] features, int daysIn) {
        int days = Math.min(daysIn, features.length - 1);
        int lastDay = features.length - 1;
        int dayFrom = lastDay - days;
        Attributes previous = null;
        AusDataByState date = new AusDataByState();
        date.ausDataByStatePerDays = new AusDataByStatePerDay[days];

        for (int i = dayFrom; i <= lastDay; i++) {
            Attributes current = features[i].attributes;
            if (previous != null) {
                date.ausDataByStatePerDays[days - (i - dayFrom)] = parseDateByDay(previous, current);
            }
            previous = current;
        }
        return date;
    }

    private AusDataByStatePerDay parseDateByDay(Attributes previous, Attributes current) {
        AusDataByStatePerDay data = new AusDataByStatePerDay();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm z");
        dateFormat.setTimeZone(TimeZone.getTimeZone("Australia/NSW"));
        timeFormat.setTimeZone(TimeZone.getTimeZone("Australia/NSW"));

        Date date = new Date(current.Date);
        data.date = dateFormat.format(date);
        data.timeFrom = timeFormat.format(date);
        Date date0 = new Date(previous.Date);
        data.dateFrom = dateFormat.format(date0);
        data.NSW = calcLongDiff(current.NSW, previous.NSW);
        data.VIC = calcLongDiff(current.VIC, previous.VIC);
        data.QLD = calcLongDiff(current.QLD, previous.QLD);
        data.SA = calcLongDiff(current.SA, previous.SA);
        data.WA = calcLongDiff(current.WA, previous.WA);
        data.TAS = calcLongDiff(current.TAS, previous.TAS);
        data.NT = calcLongDiff(current.NT, previous.NT);
        data.ACT = calcLongDiff(current.ACT, previous.ACT);
        return data;
    }

    private long calcLongDiff(Long current, Long previous) {
        if (current != null && previous != null) {
            return current - previous;
        } else if (current != null && previous == null) {
            return current;
        } else {
            return 0;
        }
    }
}
