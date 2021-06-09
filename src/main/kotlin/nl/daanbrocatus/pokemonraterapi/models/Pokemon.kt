package nl.daanbrocatus.pokemonraterapi.models

data class Pokemon(
    val id: Int,
    var name: String,
    val types: List<Type>,
    val abilities: List<Ability>,
    val stats: Stats,
    val sprites: Sprite,
    val defenses: List<Defense>
)