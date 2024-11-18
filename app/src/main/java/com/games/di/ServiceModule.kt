package com.games.di

import android.app.Application
import android.arch.persistence.room.Room
import com.core.commons.Constants
import com.core.domain.GamesRepository
import com.core.service.local.AppDataBase
import com.core.service.BuildConfig
import com.core.service.remote.GamesApi
import com.core.service.local.GamesDao
import com.core.service.remote.Interceptor
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
    single { provideGameDataBase(get()) }
    single { provideGamesDao(get()) }
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
    Retrofit.Builder().baseUrl(BuildConfig.API_URL).client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()).build()

fun provideOkHttpClient(interceptor: Interceptor): OkHttpClient =
    OkHttpClient().newBuilder().addInterceptor(interceptor).build()

fun provideGamesApi(retrofit: Retrofit): GamesApi = retrofit.create(GamesApi::class.java)

fun provideGamesRepository(api: GamesApi): GamesRepository = GamesRepositoryImpl(api)

fun provideGameDataBase(application: Application): AppDataBase =
    Room.databaseBuilder(
        application,
        AppDataBase::class.java,
        Constants.TABLE_GAMES
    ).fallbackToDestructiveMigration().build()

fun provideGamesDao(appDataBase: AppDataBase): GamesDao = appDataBase.gamesDao()

