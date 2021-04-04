package com.hongwei.security.service

import com.hongwei.constants.InternalServerError
import com.hongwei.constants.SecurityConfigurations
import com.hongwei.constants.Unauthorized
import com.hongwei.security.model.AuthorisationRequest
import com.hongwei.security.model.AuthorisationResponse
import org.apache.log4j.LogManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestTemplate

@Service
class AuthorisationService {
    private val logger = LogManager.getLogger(AuthorisationService::class.java)

    @Autowired
    private lateinit var securityConfigurations: SecurityConfigurations

    @Throws(HttpClientErrorException::class, InternalServerError::class)
    fun authorise(jwt: String): AuthorisationResponse {
        try {
            val uri = "${securityConfigurations.authorizationDomain}${securityConfigurations.authorizationEndpoint}"
            val headers = HttpHeaders()
            headers.contentType = MediaType.APPLICATION_JSON;
            val response: ResponseEntity<AuthorisationResponse> =
                    RestTemplate().postForEntity(uri, HttpEntity(
                            AuthorisationRequest(jwt)
                    ), AuthorisationResponse::class.java)

            if (response.statusCode.is2xxSuccessful && response.body?.validated == true) {
                return response.body
            }
            throw Unauthorized
        } catch (e: HttpClientErrorException) {
            throw e
        } catch (e: Unauthorized) {
            throw e
        } catch (e: Exception) {
            e.printStackTrace()
            throw InternalServerError
        }
    }
}