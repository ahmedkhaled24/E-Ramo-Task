package com.example.e_ramotask.di

import android.app.Application
import android.content.Context
import com.example.e_ramotask.data.remote.ApiInterface
import com.example.e_ramotask.data.repository.PostDetailsApiRepoImpl
import com.example.e_ramotask.data.repository.PostsApiRepoImpl
import com.example.e_ramotask.domain.repository.PostDetailsApIRepo
import com.example.e_ramotask.domain.repository.PostsApIRepo
import com.example.e_ramotask.utils.Constants.BASE_URL
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
object AppModule {
    @Provides
    @Singleton
    fun provideApiInterface(): ApiInterface {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)
    }

    @Provides
    @Singleton
    fun provideApplicationContext(application: Application): Context {
        return application.applicationContext
    }
}


@Module
@InstallIn(SingletonComponent::class)
abstract class PostsPort {
    @Binds
    @Singleton
    abstract fun bindApiRepo(impl: PostsApiRepoImpl): PostsApIRepo
}

@Module
@InstallIn(SingletonComponent::class)
abstract class PostDetailsPort {
    @Binds
    @Singleton
    abstract fun bindApiRepo(impl: PostDetailsApiRepoImpl): PostDetailsApIRepo
}