package me.adrien.gift.account.repositories

import me.adrien.gift.account.models.Present
import org.springframework.data.mongodb.repository.MongoRepository

interface PresentRepository : MongoRepository<Present, String> {
    override fun findAll(): List<Present>

    fun findByChildId(childId: String): List<Present>
}