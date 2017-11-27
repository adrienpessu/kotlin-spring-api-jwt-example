package me.adrien.gift.account.models

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed

class Familly (
        @Id var id: String,
        @Indexed(unique = true) var dnsName: String,
        var childs: List<Child>)

