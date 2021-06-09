package nl.daanbrocatus.pokemonraterapi.parsers

import nl.daanbrocatus.pokemonraterapi.models.DexPokemon
import nl.daanbrocatus.pokemonraterapi.pokeapimodels.PokeAPIPokedex

class DexParser {
    fun parse(dex: PokeAPIPokedex): List<DexPokemon>{
        val dexList: MutableList<DexPokemon> = mutableListOf()

        dex.pokemon_entries.forEach {
            val entry = DexPokemon(
                id = extractId(it.pokemon_species.url, 42),
                name = it.pokemon_species.name
            )
            dexList.add(entry)
        }

        return dexList
    }

    fun extractId(url: String, index: Int): Int {
        var id = url.removeRange(0, index)
        id = id.removeSuffix("/")

        return id.toInt()
    }
}