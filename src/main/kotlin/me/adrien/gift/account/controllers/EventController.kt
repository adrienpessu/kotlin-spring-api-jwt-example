package me.adrien.gift.account.controllers

import me.adrien.gift.account.models.Event
import me.adrien.gift.account.repositories.EventRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.security.core.Authentication
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid

@RestController
class EventController() {

    @Autowired lateinit var repository:EventRepository

    @Autowired internal var mongoOperation: MongoOperations? = null

    @PostMapping("/event")
    fun post(@Valid @RequestBody event: Event): Any {
        return if(SecurityContextHolder.getContext().getAuthentication().authorities.stream().anyMatch { "ADMIN".equals(it.authority) }){
            view(repository.save(event.copy()))
        }
        else{
            view(Event("", "", "", "", null, null, null))
        }

    }

    @GetMapping("/event")
    fun getCurrent(@RequestParam() dnsName: String): Any {
        val query = Query()
        query.addCriteria(Criteria.where("dnsName").`is`(dnsName)
                .and("start").lte(Date())
                .and("end").gte(Date()))
        return view((mongoOperation?.find(query, Event::class.java)?: throw Exception("invalid")).first())
    }

    @GetMapping("/event/{id}")
    fun get(@PathVariable() id: String): Any {
        return repository.findById(id).map { view(it) }
    }

    fun view(event: Event) = mapOf("event" to event)

}