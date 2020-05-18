package com.hongwei.controller.admin

import com.google.gson.Gson
import com.hongwei.Constant
import com.hongwei.model.jpa.GuestRepository
import com.hongwei.model.jpa.User
import com.hongwei.model.jpa.UserRepository
import com.hongwei.model.soap.common.Response
import com.hongwei.model.soap.common.SoapConstant.CODE_ERROR
import com.hongwei.model.soap.common.SoapConstant.CODE_SUCCESS
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/admin")
@CrossOrigin
class UserController {
    @Autowired
    private lateinit var userRepository: UserRepository
    @Autowired
    private lateinit var guestRepository: GuestRepository

    private val tmpToken: String = getTmpToken()

    @RequestMapping(path = ["/test.do"])
    @ResponseBody
    fun test(): String {
        val userCount = userRepository.count()
        val guestCount = guestRepository.count()

        val stringBuilder = StringBuilder("userCount: $userCount\nguestCount: $guestCount\n")

        stringBuilder.append("table User: id |  user_name | password_hash | token | roles\n")
        userRepository.findAll().forEachIndexed { index, user ->
            stringBuilder.append(" $index | ${user.user_name} | ${user.password_hash} | ${user.token} | ${user.roles}\n")
        }

        stringBuilder.append("table Guest: id |  guest_code | expire_time | token | roles\n")
        guestRepository.findAll().forEachIndexed { index, guest ->
            stringBuilder.append(" $index | ${guest.guest_code} | ${guest.expire_time} | ${guest.token} | ${guest.roles}\n")
        }

        return stringBuilder.toString()
    }

    @PostMapping(path = ["/user.do"])
    @ResponseBody
    fun addUser(userName: String, roles: String, passwordHash: String, sign: String?): String {
        userRepository.findByUserName(userName)?.let {
            return Gson().toJson(Response.from(CODE_ERROR, "user already exists!"))
        }
        userRepository.save(User().apply {
            user_name = userName
            password_hash = passwordHash
            this.roles = roles
            token = tmpToken
        })
        return Gson().toJson(Response.from(CODE_SUCCESS, "user create succeed."))
    }

    @DeleteMapping(path = ["/user.do"])
    @ResponseBody
    fun delete(userName: String, sign: String?): String {
        userRepository.findByUserName(userName)
                ?: return Gson().toJson(Response.from(CODE_ERROR, "user does not exists!"))
        userRepository.deleteByUserName(userName)
        return Gson().toJson(Response.from(CODE_SUCCESS, "user delete succeed."))
    }

    @GetMapping(path = ["/user.do"])
    @ResponseBody
    fun getUser(userName: String?, sign: String?): String {
        return Gson().toJson(Response.from(CODE_ERROR, "get user succeed.", userRepository.findByUserName(userName)))
    }

    @PutMapping(path = ["/user.do"])
    @ResponseBody
    fun updateUser(userName: String?, roles: String?, passwordHash: String?, sign: String?): String {
        val user = userRepository.findByUserName(userName)
                ?: return Gson().toJson(Response.from(CODE_ERROR, "user does not exists!"))
        userRepository.save(user.apply {
            roles?.let { this.roles = roles }
            passwordHash?.let { password_hash = passwordHash }
        })

        return Gson().toJson(Response.from(CODE_ERROR, "update user succeed."))
    }

    private fun getTmpToken(): String {
        return Constant.ACCESS_TOKEN
    }
}