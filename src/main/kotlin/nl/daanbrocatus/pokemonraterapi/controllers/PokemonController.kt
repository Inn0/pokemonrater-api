package nl.daanbrocatus.pokemonraterapi.controllers

import nl.daanbrocatus.pokemonraterapi.models.DexPokemon
import nl.daanbrocatus.pokemonraterapi.models.Pokemon
import nl.daanbrocatus.pokemonraterapi.pokeapimodels.PokeAPIPokemon
import nl.daanbrocatus.pokemonraterapi.services.PokemonService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@CrossOrigin
@RestController
@RequestMapping("/pokemon")
class PokemonController(
    val pokemonService: PokemonService
) {
    @GetMapping("/name/{name}")
    fun getPokemonByName(@PathVariable name: String): ResponseEntity<Pokemon> {
        return ResponseEntity<Pokemon>(pokemonService.getPokemonByName(name), HttpStatus.OK)
    }

    @GetMapping("/nationaldex")
    fun getAllPokemon(): ResponseEntity<List<DexPokemon>> {
        return ResponseEntity<List<DexPokemon>>(pokemonService.getAllPokemon(), HttpStatus.OK)
    }
}