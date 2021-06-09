package nl.daanbrocatus.pokemonraterapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PokemonraterApiApplication

fun main(args: Array<String>) {
    runApplication<PokemonraterApiApplication>(*args)
}
