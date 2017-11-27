package me.adrien.gift.account.models

import org.springframework.data.annotation.Id
import java.util.*

data class Event (@Id var id: String?,
                  var name: String,
                  var famillyId: String,
                  var dnsName: String?,
                  var childs: List<Child>?,
                  var start: Date?,
                  var end: Date?)

