package me.adrien.gift.account.controllers

import me.adrien.gift.account.models.Present
import me.adrien.gift.account.repositories.PresentRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid

@RestController
class PresentController() {

    @Autowired lateinit var repository: PresentRepository

    @Autowired internal var mongoOperation: MongoOperations? = null

    @PostMapping("/presents")
    fun post(@Valid @RequestBody present: Present): Any =
            repository.save(present.copy(id = UUID.randomUUID().toString()))

    @GetMapping("/presents/{childId}")
    fun get(@PathVariable() childId: String): Any = repository.findByChildId(childId)

    @DeleteMapping("/presents/{id}")
    fun delete(@PathVariable() id: String): Any = repository.deleteById(id)

    @PutMapping("/presents")
    fun put(@Valid @RequestBody present: Present): Any = repository.save(present.copy())

    @PostMapping("/present/check")
    fun check(@Valid @RequestBody present: Present): Any {
        val presentDb: Present = repository.findById(present.id).orElseThrow { Exception("") }
        return repository.save(presentDb.copy(santaName = present.santaName))
    }

    @PostMapping("/present/uncheck")
    fun uncheck(@Valid @RequestBody present: Present): Any {
        val presentDb: Present = repository.findById(present.id).orElseThrow { Exception("") }
        return repository.save(presentDb.copy(santaName = ""))
    }
}