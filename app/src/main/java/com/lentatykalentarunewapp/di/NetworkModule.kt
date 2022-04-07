package com.lentatykalentarunewapp.di

import com.lentatykalentarunewapp.common.Constants
import com.lentatykalentarunewapp.data.NewsRepositoryImpl
import com.lentatykalentarunewapp.data.network.NewsApi
import com.lentatykalentarunewapp.domain.NewsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
abstract class NetworkModule {

    @Binds
    abstract fun bindNewsRepository(repo: NewsRepositoryImpl):NewsRepository

    companion object{
        @Provides
        @Singleton
        fun provideNewsApi(): NewsApi =
            Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(NewsApi::class.java)
    }
}