package me.adrien.gift.account.models

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed

/**
 * Created by adrien on 26/06/2017.
 */

class Child (
        @Id var id: String?,
        @Indexed(unique = true) var name: String,
        var displayName: String?)
