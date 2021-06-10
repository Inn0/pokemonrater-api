package nl.daanbrocatus.pokemonraterapi.pokeapimodels

data class PokeAPIFullAbility(
    val id: Int,
    val name: String,
    val effect_entries: List<PokeAPIAbilityEffect>
)