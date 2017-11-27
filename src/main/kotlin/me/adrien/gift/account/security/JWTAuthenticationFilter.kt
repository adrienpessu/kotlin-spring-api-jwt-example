package me.adrien.gift.account.security

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.GenericFilterBean
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest

/**
 * Created by adrien on 24/06/2017.
 */
class JWTAuthenticationFilter : GenericFilterBean() {

    @Throws(IOException::class, ServletException::class)
    override fun doFilter(request: ServletRequest,
                          response: ServletResponse,
                          filterChain: FilterChain) {

        val authentication = TokenAuthenticationService()
                .getAuthentication(request as HttpServletRequest)

        SecurityContextHolder.getContext().authentication = authentication
        filterChain.doFilter(request, response)
    }
}