package nl.daanbrocatus.pokemonraterapi.models

data class Rating(
    val setup: Int,
    val physicalAttacker: Int,
    val physicalTank: Int,
    val specialAttacker: Int,
    val specialTank: Int
)
