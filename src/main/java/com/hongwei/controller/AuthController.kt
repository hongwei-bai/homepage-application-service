package com.hongwei.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.hongwei.enums.common.ResponseResultEnum
import com.hongwei.model.auth.AuthJava
import com.hongwei.model.auth.AuthYamlBean
import com.hongwei.model.common.Response
import com.hongwei.model.jpa.GuestRepository
import com.hongwei.model.jpa.UserGroupRepository
import com.hongwei.model.jpa.UserRepository
import com.hongwei.util.LogWrapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.io.File


@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = ["http://127.0.0.1:5500"], maxAge = 3600)
class AuthController {
    @Autowired
    private val userRepository: UserRepository? = null
    @Autowired
    private val userGroupRepository: UserGroupRepository? = null
    @Autowired
    private val guestRepository: GuestRepository? = null

    @RequestMapping(path = ["/test.do"])
    @ResponseBody
    fun test(): String {
        val mapper = ObjectMapper(YAMLFactory())
        mapper.findAndRegisterModules()
        val bean: AuthJava = mapper.readValue(File("src/main/resources/auth.yaml"), AuthJava::class.java)
        return bean.token
    }

    @RequestMapping(path = ["/addUser/{username}/{password_hash}"])
    @ResponseBody
    fun addUser(@PathVariable("username") username: String?, @PathVariable("password_hash") password_hash: String?): String {
        return ""
    }

    @PutMapping("/init_request.ajax")
    @Throws(Exception::class)
    fun initRequest(requestId: Long): Response<*> {
        LogWrapper.debug("logger======TestController=======initRequest======requestId:$requestId")
        //        flowRequestService.reAddRequest(requestId, requestName, applicant);
        return Response.from(ResponseResultEnum.SUCCESS.toCode(), ResponseResultEnum.SUCCESS.toDescription())
    }
}