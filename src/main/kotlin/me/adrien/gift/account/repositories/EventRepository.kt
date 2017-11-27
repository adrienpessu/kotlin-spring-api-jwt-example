package me.adrien.gift.account.repositories

import me.adrien.gift.account.models.Event;
import org.springframework.data.mongodb.repository.MongoRepository

interface EventRepository : MongoRepository<Event, String> {
    override fun findAll(): List<Event>
}
