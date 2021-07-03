package nl.daanbrocatus.pokemonraterapi.raters

import nl.daanbrocatus.pokemonraterapi.models.Defense
import nl.daanbrocatus.pokemonraterapi.models.Pokemon
import nl.daanbrocatus.pokemonraterapi.models.Rating
import nl.daanbrocatus.pokemonraterapi.models.Stats

class PokeRater {
    fun ratePokemon(pokemon: Pokemon): Rating {
        val rating = Rating(
            getSetupRating(pokemon),
            getPhysicalAttackerRating(pokemon),
            getPhysicalTankRating(pokemon),
            getSpecialAttackerRating(pokemon),
            getSpecialTankRating(pokemon)
        )
        return rating
    }

    private fun getPhysicalAttackerRating(pokemon: Pokemon): Int {
        var rating = 0F

        // Rating based on stats
        rating += getStatTotal(pokemon.stats)
        rating += pokemon.stats.atk * 3F
        rating += pokemon.stats.spe * 3F

        // Rating based on defenses
        rating *= getDefensesMultiplier(pokemon.defenses)

        return rating.toInt()
    }

    private fun getPhysicalTankRating(pokemon: Pokemon): Int {
        return 0
    }

    private fun getSpecialAttackerRating(pokemon: Pokemon): Int {
        return 0
    }

    private fun getSpecialTankRating(pokemon: Pokemon): Int {
        return 0
    }

    private fun getSetupRating(pokemon: Pokemon): Int {
        return 0
    }

    private fun getStatTotal(stats: Stats): Int {
        return stats.hp + stats.atk + stats.def + stats.spa + stats.spd + stats.spe
    }

    private fun getDefensesMultiplier(defenses: List<Defense>): Float {
        var multiplier = 1F
        defenses.forEach {
            when(it.factor){
                0F -> multiplier += 0.1F
                0.25F -> multiplier += 0.05F
                0.5F -> multiplier += 0.025F
                2F -> multiplier -= 0.025F
                4F -> multiplier -= 0.05F
            }
        }
        return multiplier
    }
}