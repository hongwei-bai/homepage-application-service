package com.hongwei.controller;

import com.hongwei.enums.common.ResponseResultEnum;
import com.hongwei.model.common.Response;
import com.hongwei.model.jpa.GuestRepository;
import com.hongwei.model.jpa.UserGroupRepository;
import com.hongwei.model.jpa.UserRepository;
import com.hongwei.util.LogWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://127.0.0.1:5500", maxAge = 3600)
public class AuthController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserGroupRepository userGroupRepository;

    @Autowired
    private GuestRepository guestRepository;

    @RequestMapping(path = "/addUser/{username}/{password_hash}")
    @ResponseBody
    public String addUser(@PathVariable("username") String username, @PathVariable("password_hash") String password_hash) {


        return "";
    }

    @PutMapping("/init_request.ajax")
    public Response initRequest(Long requestId) throws Exception {
        LogWrapper.debug("logger======TestController=======initRequest======requestId:" + requestId);
//        flowRequestService.reAddRequest(requestId, requestName, applicant);
        return Response.from(ResponseResultEnum.SUCCESS.toCode(), ResponseResultEnum.SUCCESS.toDescription());
    }
}
