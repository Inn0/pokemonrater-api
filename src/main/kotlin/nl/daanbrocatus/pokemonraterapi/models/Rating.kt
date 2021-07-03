package nl.daanbrocatus.pokemonraterapi.models

data class Rating(
    var setup: Int,
    var physicalAttacker: Int,
    var physicalTank: Int,
    var specialAttacker: Int,
    var specialTank: Int
)
