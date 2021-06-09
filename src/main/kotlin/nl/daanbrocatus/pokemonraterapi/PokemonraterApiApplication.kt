package nl.daanbrocatus.pokemonraterapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.client.RestTemplate

@SpringBootApplication
class PokemonraterApiApplication

fun main(args: Array<String>) {
    runApplication<PokemonraterApiApplication>(*args)
}
