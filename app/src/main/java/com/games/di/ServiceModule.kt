package com.games.di

import com.core.domain.GamesRepository
import com.core.service.BuildConfig
import com.core.service.GamesApi
import com.core.service.Interceptor
import com.core.service.repository.GamesRepositoryImpl
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * ENGLISH
 *
 * Creation of dependency on our services ([provideGamesApi]) and repositories ([provideGamesRepository]).
 * We need to have an instance of Retrofit ([provideRetrofit]) and OkHttpClient ([provideOkHttpClient]) to communicate with our web service.
 *
 * .
 *
 * .
 *
 * SPANISH
 *
 * Creación de dependencia de nuestros servicios ([provideGamesApi]) y repositorios ([provideGamesRepository]).
 * Necesitamos tener una instancia de Retrofit ([provideRetrofit]) y OkHttpClient ([provideOkHttpClient]) para comunicarnos con nuestro servicio web.
 */

val serviceModule = module {
    factory { Interceptor() }
    factory { provideOkHttpClient(get()) }
    factory { provideGamesApi(get()) }
    factory { provideGamesRepository(get()) }

    single { provideRetrofit(get()) }
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
    Retrofit.Builder().baseUrl(BuildConfig.API_URL).client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()).build()

fun provideOkHttpClient(interceptor: Interceptor): OkHttpClient =
    OkHttpClient().newBuilder().addInterceptor(interceptor).build()

fun provideGamesApi(retrofit: Retrofit): GamesApi = retrofit.create(GamesApi::class.java)

fun provideGamesRepository(api: GamesApi): GamesRepository = GamesRepositoryImpl(api)
