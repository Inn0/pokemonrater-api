package nl.daanbrocatus.pokemonraterapi.models

import nl.daanbrocatus.pokemonraterapi.pokeapimodels.PokeAPISpecies
import nl.daanbrocatus.pokemonraterapi.pokeapimodels.PokeAPIVariety

data class Pokemon(
    val id: Int,
    var name: String,
    val types: List<Type>,
    val abilities: List<Ability>,
    val stats: Stats,
    val sprites: Sprite,
    val defenses: List<Defense>,
    var ratings: Rating = Rating(0,0,0,0,0),
    var alternateForms: List<DexPokemon> = listOf()
)