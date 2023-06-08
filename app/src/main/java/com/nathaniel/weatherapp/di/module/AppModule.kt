package com.nathaniel.weatherapp.di.module

import android.app.Application
import com.google.gson.Gson
import com.nathaniel.weatherapp.data.repo.RepositoryImpl
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.nathaniel.weatherapp.data.repo.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideGson(): Gson {
        return Gson()

    }

    @Provides
    fun provideOkhttp(): OkHttpClient {

        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        gson: Gson,
        client: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(com.nathaniel.weatherapp.data.remote.api.ApiDetails.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
    }


    @Provides
    fun provideAPI(
        retrofit: Retrofit
    ): com.nathaniel.weatherapp.data.remote.api.ApiRequest {
        return retrofit.create(com.nathaniel.weatherapp.data.remote.api.ApiRequest::class.java)
    }

    @Provides
    fun provideRepository(apiRequest: com.nathaniel.weatherapp.data.remote.api.ApiRequest): Repository {
        return RepositoryImpl(apiRequest)
    }

    @Provides
    @Singleton
    fun provideFusedLocationProviderClient(app: Application): FusedLocationProviderClient {
        return LocationServices.getFusedLocationProviderClient(app)
    }


}