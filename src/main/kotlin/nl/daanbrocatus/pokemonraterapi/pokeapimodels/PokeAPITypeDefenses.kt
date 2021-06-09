package nl.daanbrocatus.pokemonraterapi.pokeapimodels

data class PokeAPITypeDefenses(
    val double_damage_from: List<PokeAPIType>,
    val half_damage_from: List<PokeAPIType>,
    val no_damage_from: List<PokeAPIType>
)
