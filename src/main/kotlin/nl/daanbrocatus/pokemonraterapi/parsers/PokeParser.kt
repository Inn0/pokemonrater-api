package nl.daanbrocatus.pokemonraterapi.parsers

import nl.daanbrocatus.pokemonraterapi.models.*
import nl.daanbrocatus.pokemonraterapi.pokeapimodels.*
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject

class PokeParser(
    val restTemplate: RestTemplate
) {
    fun parse(apiPokemon: PokeAPIPokemon): Pokemon{
        val pokemon = Pokemon(
            apiPokemon.id,
            apiPokemon.name,
            parseTypes(apiPokemon.types),
            parseAbilities(apiPokemon.abilities),
            parseStats(apiPokemon.stats),
            parseSprites(apiPokemon.sprites),
            parseDefenses(parseTypes(apiPokemon.types)),
            Rating(0,0,0,0,0),
        )

        if(apiPokemon.id < 1000){
            pokemon.alternateForms = parseAlts(apiPokemon.id)
        } else {
            pokemon.alternateForms = getOriginal(apiPokemon)
        }

        return pokemon
    }

    fun parseTypes(apiTypes: List<PokeAPITypes>): List<Type>{
        val typeList: MutableList<Type> = mutableListOf()
        apiTypes.forEach {
            val type = Type(
                name = it.type.name,
                id = extractId(it.type.url, 31),
            )
            typeList.add(type)
        }
        return typeList
    }

    fun parseAbilities(apiAbilities: List<PokeAPIAbilities>): List<Ability> {
        val abilityList: MutableList<Ability> = mutableListOf()
        apiAbilities.forEach{
            val ability = Ability(
                name = it.ability.name,
                id = extractId(it.ability.url, 34),
                hidden = it.is_hidden
            )
            abilityList.add(ability)
        }
        return abilityList
    }

    fun parseStats(apiStats: List<PokeAPIStats>): Stats {
        val stats: Stats = Stats(0,0,0,0,0,0)
        apiStats.forEach {
            if(it.stat.name == "hp"){
                stats.hp = it.base_stat
            } else if(it.stat.name == "attack"){
                stats.atk = it.base_stat
            } else if(it.stat.name == "defense"){
                stats.def = it.base_stat
            } else if(it.stat.name == "special-attack"){
                stats.spa = it.base_stat
            } else if(it.stat.name == "special-defense"){
                stats.spd = it.base_stat
            } else if(it.stat.name == "speed"){
                stats.spe = it.base_stat
            }
        }
        return stats
    }

    fun parseSprites(apiSprites: PokeAPISprites): Sprite {
        val sprite: Sprite = Sprite(
            apiSprites.front_default,
            apiSprites.front_shiny
        )
        return sprite
    }

    fun extractId(url: String, index: Int): Int {
        var id = url.removeRange(0, index)
        id = id.removeSuffix("/")

        return id.toInt()
    }

    fun parseDefenses(types: List<Type>): List<Defense> {
        val typeList: MutableList<PokeAPITypeEntity> = mutableListOf()
        types.forEach {
            restTemplate.getForObject("https://pokeapi.co/api/v2/type/${it.id}", PokeAPITypeEntity::class.java)
                ?.let { it1 -> typeList.add(it1) }
        }

        val defenses: MutableList<Defense> = mutableListOf()

        typeList.forEach {
            val noDamageList = parseType(it.damage_relations.no_damage_from)
            val halfDamageList = parseType(it.damage_relations.half_damage_from)
            val doubleDamageList = parseType(it.damage_relations.double_damage_from)

            noDamageList.forEach {
                val defense = Defense(it.id, it.name, 0.0F)
                if(defenses.find { it.id == defense.id } != null) {
                    defenses.find { it.id == defense.id }?.factor = defenses.find { it.id == defense.id }!!.factor * defense.factor
                } else {
                    defenses.add(defense)
                }
            }

            halfDamageList.forEach {
                val defense = Defense(it.id, it.name, 0.5F)
                if(defenses.find { it.id == defense.id } != null) {
                    defenses.find { it.id == defense.id }?.factor = defenses.find { it.id == defense.id }!!.factor * defense.factor
                } else {
                    defenses.add(defense)
                }
            }

            doubleDamageList.forEach {
                val defense = Defense(it.id, it.name, 2.0F)
                if(defenses.find { it.id == defense.id } != null) {
                    defenses.find { it.id == defense.id }?.factor = defenses.find { it.id == defense.id }!!.factor * defense.factor
                } else {
                    defenses.add(defense)
                }
            }
        }

        return defenses
    }

    fun parseType(types: List<PokeAPIType>): List<Type>{
        val list: MutableList<Type> = mutableListOf()
        types.forEach {
            val type = Type(
                id = extractId(it.url, 31),
                name = it.name
            )
            list.add(type)
        }
        return list
    }

    fun parseAlts(id: Int): List<DexPokemon> {
        val list: MutableList<DexPokemon> = mutableListOf()
        val species = restTemplate.getForObject("https://pokeapi.co/api/v2/pokemon-species/$id", PokeAPISpecies::class.java)
        species?.varieties?.forEach {
            val pokemon = DexPokemon(
                name = it.pokemon.name,
                id = extractId(it.pokemon.url, 34)
            )
            list.add(pokemon)
        }
        return list
    }

    fun getOriginal(pokemon: PokeAPIPokemon): List<DexPokemon> {
        val list: MutableList<DexPokemon> = mutableListOf()
        list.add(DexPokemon(extractId(pokemon.species.url, 42), pokemon.species.name))
        list.add(DexPokemon(pokemon.id, pokemon.name))
        return list
    }
}