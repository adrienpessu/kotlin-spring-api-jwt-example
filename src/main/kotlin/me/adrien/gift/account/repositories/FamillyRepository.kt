package me.adrien.gift.account.repositories

import me.adrien.gift.account.models.Familly
import org.springframework.data.mongodb.repository.MongoRepository

interface FamillyRepository : MongoRepository<Familly, String> {

    override fun findAll(): List<Familly>
}
