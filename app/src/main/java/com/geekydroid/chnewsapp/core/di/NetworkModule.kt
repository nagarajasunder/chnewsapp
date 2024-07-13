package com.geekydroid.chnewsapp.core.di

import com.geekydroid.chnewsapp.core.network.NewsApiService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun providesGson(): Gson = GsonBuilder()
        .setLenient()
        .create()

    @Singleton
    @Provides
    fun providesRetrofit(gson: Gson): Retrofit = Retrofit.Builder()
        .baseUrl("https://google.com/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Singleton
    @Provides
    fun providesNewsApiService(retrofit: Retrofit):NewsApiService {
       return retrofit.create(NewsApiService::class.java)
    }
}