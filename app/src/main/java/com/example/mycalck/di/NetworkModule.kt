package com.example.mycalck.di

import com.example.mycalck.BuildConfig
import com.example.mycalck.data.RickAndMortyApiService
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single {
        provideRetrofit(get())
    }
    single {
        provideOkHttpClient(get())
    }
    single {
        provideRickAndMortyApiService(get())
    }
    factory {
        provideInterceptor()
    }
}

fun provideRetrofit(
    okHttpClient: OkHttpClient
): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .client(okHttpClient)
        .build()
}

fun provideOkHttpClient(
    interceptor: HttpLoggingInterceptor
): OkHttpClient {
    return OkHttpClient.Builder()
        .connectTimeout(20L, TimeUnit.SECONDS)
        .readTimeout(20L, TimeUnit.SECONDS)
        .writeTimeout(20L, TimeUnit.SECONDS)
        .addInterceptor(interceptor)
        .build()
}

fun provideInterceptor(): HttpLoggingInterceptor {
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY
    return interceptor
}

fun provideRickAndMortyApiService(retrofit: Retrofit): RickAndMortyApiService {
    return retrofit.create(RickAndMortyApiService::class.java)
}
