package com.mike.controller;

import com.mike.Config;
import com.mike.model.DictVerInfoDto;

import java.util.ArrayList;
import java.util.List;

public class DictVerInfoBuilder {
    public static final String DICT_VER_FILE_BASE = Config.FILE_SERVER + "/dict/ver.";

    public static DictVerInfoDto buildDto(long ver) {
        DictVerInfoDto dto = new DictVerInfoDto();
        dto.setVersion(ver);
        dto.setUpgradeType(1);
        List<String> filelist = new ArrayList<>();
        filelist.add(DICT_VER_FILE_BASE + ver + "/dict_db");
        filelist.add(DICT_VER_FILE_BASE + ver + "/dict_db-journal");
        filelist.add(DICT_VER_FILE_BASE + ver + "/word_db");
        filelist.add(DICT_VER_FILE_BASE + ver + "/word_db-journal");
        dto.setFilelist(filelist);
        return dto;
    }
}
