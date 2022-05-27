package com.example.usersapplication_classwork.domain

import com.example.usersapplication_classwork.data.user.*
import com.example.usersapplication_classwork.network.BASE_URL
import com.example.usersapplication_classwork.ui.login.LoginViewModel
import com.example.usersapplication_classwork.ui.registration.RegistrationViewModel


import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


val appModule = module {
    single {
        UserRepository(get(), get())
    }
    single {
        UserLocalDataSource()
    }

    single {
        UserRemoteDataSource(get())
    }

    single {
        val retrofit = get() as Retrofit
        val loginApiService = retrofit.create(RegistrationService::class.java)
        loginApiService
    }

    single {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }
        val client = OkHttpClient.Builder()
            .addInterceptor(logger)
            .build()


        val retrofit = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(BASE_URL)
            .client(client)
            .build()
        retrofit
    }
    viewModel { LoginViewModel(get()) }
    viewModel { RegistrationViewModel(get()) }
}
