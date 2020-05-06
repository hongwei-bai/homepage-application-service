package com.mike.controller;

import com.mike.model.DictVerInfoDto;
import com.mike.model.common.BaseDto;
import com.mike.model.jpa.User;
import com.mike.model.jpa.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500", maxAge = 3600)
public class DictController {
    @Autowired
    private UserRepository userRepository;

    // Test only
    @RequestMapping(path = "/save")
    @ResponseBody
    public String save() {
        if (userRepository.findByName("AAA") != null) {
            return "already fill data, save failed!";
        }

        userRepository.save(new User("AAA", 10));
        userRepository.save(new User("BBB", 20));
        userRepository.save(new User("CCC", 30));
        userRepository.save(new User("DDD", 40));
        userRepository.save(new User("EEE", 50));
        userRepository.save(new User("FFF", 60));
        userRepository.save(new User("GGG", 70));
        userRepository.save(new User("HHH", 80));
        userRepository.save(new User("III", 90));
        userRepository.save(new User("JJJ", 100));

        return "save done!";
    }

    @RequestMapping(path = "/hello")
    @ResponseBody
    public String hello() {
        return "Hello Tu Dictionary! CCC' age is " + userRepository.findByName("CCC").getAge();
    }

    @PostMapping(value = "/submit.do")
    @ResponseBody
    public User submit(@Valid @RequestBody User user) {
        System.out.println("submit succeed! user.name: " + user.getName() + ", age: " + user.getAge());
        return user;
    }

    @RequestMapping(path = "/latestdictver")
    @ResponseBody
    public BaseDto<Long> getLatestDictVer() {
        return buildDto(1L);
    }

    @RequestMapping(path = "/dictver/{dictver}")
    @ResponseBody
    public BaseDto<DictVerInfoDto> getDictVerInfo(@PathVariable("dictver") Long dictver) {
        if (null == dictver) {
            return buildServerErrorDto("dictver is NULL.");
        }

        if (1L == dictver) {
            return buildDto(DictVerInfoBuilder.buildDto(dictver));
        }

        return buildDataInvalidDto();
    }

    private BaseDto buildServerErrorDto(String msg) {
        BaseDto baseDto = new BaseDto();
        baseDto.setCode(1);
        baseDto.setMsg("Server Error: " + msg);
        return baseDto;
    }

    private BaseDto buildDataInvalidDto() {
        BaseDto baseDto = new BaseDto();
        baseDto.setCode(1);
        baseDto.setMsg("Invalid request data.");
        return baseDto;
    }

    private BaseDto buildDto(Object data) {
        BaseDto baseDto = new BaseDto();
        baseDto.setCode(0);
        baseDto.setMsg("success");
        baseDto.setData(data);
        return baseDto;
    }
}
