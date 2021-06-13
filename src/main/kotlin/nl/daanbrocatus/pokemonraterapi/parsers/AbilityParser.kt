package nl.daanbrocatus.pokemonraterapi.parsers

import nl.daanbrocatus.pokemonraterapi.models.FullAbility
import nl.daanbrocatus.pokemonraterapi.pokeapimodels.PokeAPIAbilityEffect
import nl.daanbrocatus.pokemonraterapi.pokeapimodels.PokeAPIFullAbility

class AbilityParser {
    fun parse(apiAbility: PokeAPIFullAbility): FullAbility {
        val ability = FullAbility(
            id = apiAbility.id,
            name = apiAbility.name,
            effect = parseEffect(apiAbility.effect_entries),
            shortEffect = parseShortEffect(apiAbility.effect_entries)
        )
        return ability
    }

    fun parseEffect(effectList: List<PokeAPIAbilityEffect>): String {
        return effectList.find {it.language.name == "en"}!!.effect
    }

    fun parseShortEffect(effectList: List<PokeAPIAbilityEffect>): String {
        return effectList.find {it.language.name == "en"}!!.short_effect
    }
}