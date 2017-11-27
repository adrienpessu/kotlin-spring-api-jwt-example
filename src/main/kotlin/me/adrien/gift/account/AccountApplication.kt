package me.adrien.gift.account

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Configuration

@Configuration
@SpringBootApplication
class AccountApplication

fun main(args: Array<String>) {
    SpringApplication.run(AccountApplication::class.java, *args)
}
