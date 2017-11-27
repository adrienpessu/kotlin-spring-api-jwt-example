package me.adrien.gift.account.models

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed

/**
 * Created by adrien on 26/06/2017.
 */

data class Present(
        @Id val id: String?,
        val label: String,
        val childId: String,
        val santaName: String?,
        val url: String?,
        val url2: String?,
        val url3: String?,
        val pics: String?,
        val order: Int = 0
)