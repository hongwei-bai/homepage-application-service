package com.hongwei.security.filters

import com.hongwei.constants.SecurityConfigurations
import com.hongwei.security.service.AuthorisationService
import com.hongwei.security.service.MyUserDetailsService
import org.apache.log4j.LogManager
import org.apache.log4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtRequestFilter : OncePerRequestFilter() {
    private val myLogger: Logger = LogManager.getLogger(JwtRequestFilter::class.java)

    @Autowired
    private lateinit var userDetailsService: MyUserDetailsService

    @Autowired
    private lateinit var authorisationService: AuthorisationService

    @Autowired
    private lateinit var securityConfigurations: SecurityConfigurations

    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        val authorizationHeader = request.getHeader(securityConfigurations.authorizationHeader)

        if (doCORSFilter(request, response)) {
            grantAccess(request)
        } else if (authorizationHeader != null && authorizationHeader.startsWith(securityConfigurations.authorizationBearer)) {
            val jwt = authorizationHeader.substring(securityConfigurations.authorizationBearer.length + 1)
            val authorisationResponse = authorisationService.authorise(jwt)

            if (SecurityContextHolder.getContext().authentication == null
                    && authorisationResponse.validated == true) {
                grantAccess(request)
            }
        }

        chain.doFilter(request, response)
    }

    private fun doCORSFilter(request: HttpServletRequest, response: HttpServletResponse): Boolean {
        // Authorize (allow) all domains to consume the content
//        response.addHeader("Access-Control-Allow-Origin", "*")
//        response.addHeader("Access-Control-Allow-Methods", "GET, OPTIONS, HEAD, PUT, POST")
//        response.addHeader("Access-Control-Request-Headers", securityConfigurations.authorizationHeader)

        // For HTTP OPTIONS verb/method reply with ACCEPTED status code -- per CORS handshake
        if (request.method == "OPTIONS") {
//            response.status = HttpServletResponse.SC_ACCEPTED
            return true
        }

        // pass the request along the filter chain
        return false
    }

    private fun grantAccess(request: HttpServletRequest) {
        val userDetails = userDetailsService.loadUserByUsername("stub")

        // Grant access
        val usernamePasswordAuthenticationToken = UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.authorities)
        usernamePasswordAuthenticationToken.details = WebAuthenticationDetailsSource().buildDetails(request)
        SecurityContextHolder.getContext().authentication = usernamePasswordAuthenticationToken
    }
}