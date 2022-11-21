package com.usa.presidents.controller

import com.usa.presidents.exception.POTUSNotFoundException
import com.usa.presidents.model.POTUS
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/presidents")
class PresidentsController {

  private var POTUSs = mutableListOf(
    POTUS(1, "George Washington", "Unaffiliated"),
    POTUS(2, "John Adams", "Federalist"),
    POTUS(3, "Thomas Jefferson", "Democratic-Republican"),
    POTUS(4, "James Madison", "Democratic-Republican"),
    POTUS(5, "James Monroe", "Democratic-Republican"),
  )  

  @GetMapping("")
  fun getPOTUSs() = POTUSs

  @GetMapping("/homepage")
  fun getHomePage() = "Data of USA Presidents"
  
  @GetMapping("/{id}")
  fun getPOTUSById(@PathVariable id: Int): POTUS {
  val potus = POTUSs.firstOrNull { it.id == id }
  return potus ?: throw POTUSNotFoundException()
  }

  @PostMapping("")
  @ResponseStatus(HttpStatus.CREATED)
  fun postPOTUS(@RequestBody potus: POTUS): POTUS {
    val maxId = POTUSs.map { it.id }.maxOrNull() ?: 0
    val nextId = maxId + 1
    val newPotus = POTUS(id = nextId, name = potus.name, party = potus.party)
    POTUSs.add(newPotus)
    return newPotus
  }

  @PutMapping("/{id}")
  fun putPOTUS(@PathVariable id: Int, @RequestBody potus: POTUS): ResponseEntity<POTUS> {
    val existingPotus = POTUSs.firstOrNull { it.id == id }
    return if (existingPotus == null) {
      ResponseEntity(postPOTUS(potus), HttpStatus.CREATED)
    } else {
      val updatedPotus = existingPotus.copy(name = potus.name, party = potus.party)
      ResponseEntity(updatedPotus, HttpStatus.OK)
    }
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  fun deletePOTUS(@PathVariable id: Int) {
    POTUSs = (POTUSs.filter { it.id != id }).toMutableList()
  }
  
}