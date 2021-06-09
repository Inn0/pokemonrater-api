package nl.daanbrocatus.pokemonraterapi.pokeapimodels

data class PokeAPIPokemon(
    val id: Int,
    val name: String,
    val abilities: List<PokeAPIAbilities>,
    val stats: List<PokeAPIStats>,
    val types: List<PokeAPITypes>,
    val sprites: PokeAPISprites
)
