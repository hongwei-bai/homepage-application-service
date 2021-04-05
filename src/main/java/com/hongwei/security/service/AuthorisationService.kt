package com.hongwei.security.service

import com.hongwei.constants.AppTokenExpiredException
import com.hongwei.constants.InternalServerError
import com.hongwei.constants.SecurityConfigurations
import com.hongwei.constants.Unauthorized
import com.hongwei.security.model.AuthorisationRequest
import com.hongwei.security.model.AuthorisationResponse
import com.hongwei.security.model.AuthoriseObject
import org.apache.log4j.LogManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.*
import org.springframework.stereotype.Service
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestTemplate

@Service
class AuthorisationService {
    private val logger = LogManager.getLogger(AuthorisationService::class.java)

    @Autowired
    private lateinit var securityConfigurations: SecurityConfigurations

    @Autowired
    private lateinit var cache: AuthorisationCache

    @Throws(HttpClientErrorException::class, InternalServerError::class)
    fun authorise(jwt: String, method: String, requestUri: String): Boolean {
        val authoriseObject: AuthoriseObject? = cache.getFromCache(jwt) ?: try {
            val uri = "${securityConfigurations.authorizationDomain}${securityConfigurations.authorizationEndpoint}"
            val headers = HttpHeaders()
            headers.contentType = MediaType.APPLICATION_JSON;
            val response: ResponseEntity<AuthorisationResponse> =
                    RestTemplate().postForEntity(uri, HttpEntity(
                            AuthorisationRequest(jwt)
                    ), AuthorisationResponse::class.java)

            if (response.statusCode.is2xxSuccessful && response.body?.validated == true) {
                cache.update(jwt, response.body!!)
            } else null
        } catch (e: Unauthorized) {
            throw e
        } catch (e: Exception) {
            if (e.message?.contains("ExpiredJwtException") == true) {
                throw AppTokenExpiredException
            }
            e.printStackTrace()
            throw InternalServerError
        }

        return authoriseObject?.run {
            if (validated) {
                checkPermission(userName, method, requestUri)
            } else false
        } ?: false
    }

    private fun AuthoriseObject.checkPermission(userName: String, method: String, uri: String): Boolean {
        if (uri.startsWith("/covid19/", true)) {
            return true
        }

        if (blogEntry && uri.startsWith("/blog/", true)) {
            return when (method) {
                HttpMethod.GET.name -> true
                HttpMethod.PUT.name,
                HttpMethod.POST.name -> blogCreate || blogModifyAll
                else -> false
            }
        }

        return false
    }
}