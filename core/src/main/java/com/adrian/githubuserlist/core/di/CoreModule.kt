package com.adrian.githubuserlist.core.di

import androidx.room.Room
import com.adrian.githubuserlist.core.data.UserRepository
import com.adrian.githubuserlist.core.data.source.local.LocalDataSource
import com.adrian.githubuserlist.core.data.source.local.room.UserDatabase
import com.adrian.githubuserlist.core.data.source.remote.RemoteDataSource
import com.adrian.githubuserlist.core.data.source.remote.network.ApiService
import com.adrian.githubuserlist.core.domain.repository.IUserRepository
import com.adrian.githubuserlist.core.utils.AppExecutors
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single {
        val authInterceptor = Interceptor { chain ->
            val request = chain.request()
            val requestHeaders = request.newBuilder()
                .addHeader("Authorization", "token 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")
                .build()
            chain.proceed(requestHeaders)
        }
        OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val databaseModule = module {
    factory { get<UserDatabase>().userDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            UserDatabase::class.java, "users.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IUserRepository> {
        UserRepository(
            get(),
            get(),
            get()
        )
    }
}