package com.lentatykalentarunewapp.di

import com.lentatykalentarunewapp.presentation.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, ViewModelModule::class])
interface AppComponent {

    fun inject(activity: MainActivity)

}