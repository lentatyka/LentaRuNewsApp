package com.lentatykalentarunewapp.di

import com.lentatykalentarunewapp.data.FakeNewsRepositoryImpl
import com.lentatykalentarunewapp.domain.NewsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [NetworkModule::class]
)
abstract class FakeNetworkModule {

    @Binds
    abstract fun fakeNews(fakeRepo: FakeNewsRepositoryImpl):NewsRepository
}