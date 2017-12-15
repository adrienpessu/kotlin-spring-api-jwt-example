package me.adrien.gift.account.config

import me.adrien.gift.account.repository.UserRepository
import me.adrien.gift.account.security.JWTAuthenticationFilter
import me.adrien.gift.account.security.JWTLoginFilter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class WebSecurityConfig : WebSecurityConfigurerAdapter() {

    @Autowired lateinit var repository: UserRepository

    @Autowired internal var mongoOperation: MongoOperations? = null

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        val source = UrlBasedCorsConfigurationSource()
        val config = CorsConfiguration()

        config.addAllowedOrigin("*")
        config.addAllowedHeader("*")
        config.addAllowedMethod("GET")
        config.addAllowedMethod("PUT")
        config.addAllowedMethod("POST")
        config.addAllowedMethod("DELETE")
        config.addAllowedMethod("PATCH")
        config.addAllowedMethod("OPTIONS")
        source.registerCorsConfiguration("/**", config)

        http.cors().and().csrf().disable().authorizeRequests()
                .antMatchers("/").permitAll()
                .anyRequest().authenticated()
                .and()
                // We filter the api/login requests
                .addFilterBefore(JWTLoginFilter("/login", authenticationManager()),
                        UsernamePasswordAuthenticationFilter::class.java)
                // And filter other requests to check the presence of JWT in header
                .addFilterBefore(JWTAuthenticationFilter(),
                        UsernamePasswordAuthenticationFilter::class.java)
    }

    @Throws(Exception::class)
    override fun configure(auth: AuthenticationManagerBuilder?) {

        repository.findAll().forEach {
            auth!!.inMemoryAuthentication()
                .withUser(it.username)
                .password(it.password)
                .roles(it.role)
        }

        // Create a default account
        auth!!.inMemoryAuthentication()
                .withUser("admin")
                .password("password")
                .roles("ADMIN")
    }
}