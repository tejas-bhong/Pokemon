package com.tejasbhong.pokemon.common.data.remote.api.di

import com.tejasbhong.pokemon.common.data.remote.api.PokemonApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Provides
    @Singleton
    fun providePokemonApi(): PokemonApi {
        val okhttpClient = OkHttpClient.Builder()
            .connectTimeout(10_000, TimeUnit.MILLISECONDS)
            .readTimeout(10_000, TimeUnit.MILLISECONDS)
            .writeTimeout(10_000, TimeUnit.MILLISECONDS)
            .callTimeout(10_000, TimeUnit.MILLISECONDS)
            .build()
        return Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okhttpClient)
            .build()
            .create(PokemonApi::class.java)
    }
}
