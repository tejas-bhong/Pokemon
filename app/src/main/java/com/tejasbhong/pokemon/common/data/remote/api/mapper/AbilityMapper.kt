package com.tejasbhong.pokemon.common.data.remote.api.mapper

import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import com.tejasbhong.pokemon.common.data.remote.api.model.PokemonDetailsResponse.AbilityResponse
import com.tejasbhong.pokemon.feature.pokemon.domain.model.Ability

object AbilityMapper {
    fun toDomain(abilityResponse: AbilityResponse): Ability {
        return Ability(
            name = abilityResponse.ability.name.capitalize(Locale.current),
            url = abilityResponse.ability.url,
        )
    }
}
