package nl.daanbrocatus.pokemonraterapi.services

import nl.daanbrocatus.pokemonraterapi.models.DexPokemon
import nl.daanbrocatus.pokemonraterapi.models.Pokemon
import nl.daanbrocatus.pokemonraterapi.parsers.DexParser
import nl.daanbrocatus.pokemonraterapi.parsers.PokeParser
import nl.daanbrocatus.pokemonraterapi.pokeapimodels.PokeAPIPokedex
import nl.daanbrocatus.pokemonraterapi.pokeapimodels.PokeAPIPokemon
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.lang.Error

@Service
class PokemonService(
    val restTemplate: RestTemplate,
    val pokeParser: PokeParser,
    val dexParser: DexParser
) {
    fun getPokemonByName(name: String): Pokemon {
        val pokemon: PokeAPIPokemon =
            restTemplate.getForObject("https://pokeapi.co/api/v2/pokemon/$name", PokeAPIPokemon::class.java)
                ?: throw Exception("Pokemon not found!")

        return pokeParser.parse(pokemon)
    }

    fun getAllPokemon(): List<DexPokemon> {
        val pokedex: PokeAPIPokedex =
            restTemplate.getForObject("https://pokeapi.co/api/v2/pokedex/1", PokeAPIPokedex::class.java)
                ?: throw Exception("Pokemon not found!")
        return dexParser.parse(pokedex)
    }
}