package nl.daanbrocatus.pokemonraterapi.controllers

import nl.daanbrocatus.pokemonraterapi.models.FullAbility
import nl.daanbrocatus.pokemonraterapi.services.AbilityService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@CrossOrigin
@RestController
@RequestMapping("/ability")
class AbilityController(
    val abilityService: AbilityService
) {
    @GetMapping("/{id}")
    fun getAbilityById(@PathVariable id: Int): ResponseEntity<FullAbility>{
        return ResponseEntity<FullAbility>(abilityService.getAbilityById(id), HttpStatus.OK)
    }
}