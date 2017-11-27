package me.adrien.gift.account.model

import com.fasterxml.jackson.annotation.JsonIgnore

data class User(var email: String = "",
                @JsonIgnore
                var password: String? = "",
                var token: String? = "",
                var username: String = "",
                var bio: String? = "",
                var role: String? = "",
                var image: String? = "",
                var famillyId: String? = "",
                var id: String? = "") {
    override fun toString(): String = "{\"email\" : \"$email\", \"username\" : \"$username\", \"role\" : \"$role\"}"
}