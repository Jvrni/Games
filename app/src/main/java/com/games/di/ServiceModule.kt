package com.games.di

import android.content.Context
import androidx.room.Room
import com.core.commons.base.Constants
import com.core.domain.GameRepository
import com.core.service.local.AppDataBase
import com.core.service.BuildConfig
import com.core.service.remote.GamesApi
import com.core.service.local.GamesDao
import com.core.service.remote.Interceptor
import com.core.service.repository.GameRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

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

@Module
@InstallIn(SingletonComponent::class)
class ServiceModule {
    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder().baseUrl(BuildConfig.API_URL).client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create()).build()

    @Singleton
    @Provides
    fun provideOkHttpClient(interceptor: Interceptor): OkHttpClient =
        OkHttpClient().newBuilder().addInterceptor(interceptor).build()

    @Singleton
    @Provides
    fun provideGameDataBase(@ApplicationContext context: Context): AppDataBase =
        Room.databaseBuilder(
            context,
            AppDataBase::class.java,
            Constants.TABLE_GAMES
        ).fallbackToDestructiveMigration().build()

    @Singleton
    @Provides
    fun provideGamesDao(appDataBase: AppDataBase): GamesDao = appDataBase.gamesDao()

    @Singleton
    @Provides
    fun provideGamesApi(retrofit: Retrofit): GamesApi = retrofit.create(GamesApi::class.java)

    @Singleton
    @Provides
    fun provideGamesRepository(api: GamesApi, dao: GamesDao): GameRepository =
        GameRepositoryImpl(api, dao)

}








