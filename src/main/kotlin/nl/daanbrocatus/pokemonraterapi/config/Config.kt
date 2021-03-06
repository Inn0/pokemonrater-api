package nl.daanbrocatus.pokemonraterapi.config

import nl.daanbrocatus.pokemonraterapi.parsers.AbilityParser
import nl.daanbrocatus.pokemonraterapi.parsers.DexParser
import nl.daanbrocatus.pokemonraterapi.parsers.PokeParser
import nl.daanbrocatus.pokemonraterapi.raters.PokeRater
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate

@Configuration
class Config {
    @Bean
    fun restTemplate(): RestTemplate {
        return RestTemplate()
    }

    @Bean
    fun pokeParser(): PokeParser {
        return PokeParser(restTemplate(), pokeRater())
    }

    @Bean
    fun dexParser(): DexParser {
        return DexParser()
    }

    @Bean
    fun abilityParser(): AbilityParser {
        return AbilityParser()
    }

    @Bean
    fun pokeRater(): PokeRater {
        return PokeRater()
    }
}