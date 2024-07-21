package com.example.restaurants.di

import com.example.restaurants.data.remote.retrofit.DetailsApi
import com.example.restaurants.data.remote.retrofit.OrganizationsApi
import com.example.restaurants.helpers.BASE_URL
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
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providesOrganizationsService(retrofit: Retrofit): OrganizationsApi =
        retrofit.create(OrganizationsApi::class.java)

    @Singleton
    @Provides
    fun providesDetailsService(retrofit: Retrofit): DetailsApi =
        retrofit.create(DetailsApi::class.java)

}