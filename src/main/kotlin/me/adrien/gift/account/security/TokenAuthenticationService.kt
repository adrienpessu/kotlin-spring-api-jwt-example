package me.adrien.gift.account.security

import io.jsonwebtoken.Jwts
import me.adrien.gift.account.model.User
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import java.util.*
import java.util.Collections.emptyList
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


/**
 * Created by adrien on 24/06/2017.
 */
class TokenAuthenticationService {

    val secret : String? = System.getenv("JWT_SECRET")

    internal fun addAuthentication(res: HttpServletResponse, authentication: Authentication) {
        res.addHeader("Authorization", "Bearer " + Jwts.builder()
                .setSubject(User(username = authentication.name, role = authentication.authorities.joinToString(", ", "[", "]")).toString())
                .setExpiration(Date(System.currentTimeMillis() + 864000000)) // 10 days
                .signWith(io.jsonwebtoken.SignatureAlgorithm.HS512, secret)
                .compact())
    }

    internal fun getAuthentication(request: HttpServletRequest): Authentication? {
        val token = request.getHeader("Authorization") ?: return null

        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token.replace("Bearer ", ""))
                .getBody()
                .getSubject()?.let{UsernamePasswordAuthenticationToken(it, null, emptyList<GrantedAuthority>())}
    }
}
