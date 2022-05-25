package com.example.doggyapp.di

import android.content.Context
import androidx.room.Room
import com.example.doggyapp.DogApi
import com.example.doggyapp.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    @Named("provideAppDatabase")
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, AppDatabase::class.java, "db" ).build()

    @Singleton
    @Provides
    @Named("provideDogApi")
    fun provideDogApi(): DogApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://dog.ceo/api/")
            .build()
            .create(DogApi::class.java)
    }
}