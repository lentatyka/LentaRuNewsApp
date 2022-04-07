package com.lentatykalentarunewapp.di

import com.lentatykalentarunewapp.common.Constants
import com.lentatykalentarunewapp.data.NewsRepositoryImpl
import com.lentatykalentarunewapp.data.network.NewsApi
import com.lentatykalentarunewapp.domain.NewsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkModule {

    @Binds
    abstract fun some(repo: NewsRepositoryImpl):NewsRepository

    companion object{
        @Provides
        @Singleton
        fun provideNewsApi():NewsApi =
            Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(NewsApi::class.java)
    }
}