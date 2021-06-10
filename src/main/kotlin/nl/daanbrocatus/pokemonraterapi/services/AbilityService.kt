package nl.daanbrocatus.pokemonraterapi.services

import nl.daanbrocatus.pokemonraterapi.models.FullAbility
import nl.daanbrocatus.pokemonraterapi.parsers.AbilityParser
import nl.daanbrocatus.pokemonraterapi.pokeapimodels.PokeAPIFullAbility
import nl.daanbrocatus.pokemonraterapi.pokeapimodels.PokeAPIPokedex
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class AbilityService(
    val restTemplate: RestTemplate,
    val abilityParser: AbilityParser
) {
    fun getAbilityById(id: Int): FullAbility {
        val ability: PokeAPIFullAbility =
            restTemplate.getForObject("https://pokeapi.co/api/v2/ability/$id", PokeAPIFullAbility::class.java)
                ?: throw Exception("Ability not found!")
        return abilityParser.parse(ability)
    }
}