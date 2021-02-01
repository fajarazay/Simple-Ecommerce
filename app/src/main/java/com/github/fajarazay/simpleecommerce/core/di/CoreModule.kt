package com.github.fajarazay.simpleecommerce.core.di

import androidx.room.Room
import com.github.fajarazay.simpleecommerce.core.data.ProductRepository
import com.github.fajarazay.simpleecommerce.core.data.source.remote.RemoteDataSource
import com.github.fajarazay.simpleecommerce.core.data.source.remote.network.ApiService
import com.github.fajarazay.simpleecommerce.core.domain.repository.IProductRepository
import com.github.fajarazay.simpleecommerce.core.utils.AppExecutors
import com.github.fajarazay.simpleecommerce.core.data.source.local.LocalDataSource
import com.github.fajarazay.simpleecommerce.core.data.source.local.room.EcommerceDatabase
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by fajarseptian on 1/31/21.
 *
 * @Author Fajar Septian
 * @Email fajarajay10@gmail.com
 * @Github https://github.com/fajarazay
 */

val databaseModule = module {
    factory {
        get<EcommerceDatabase>().productDao()
    }

    single {
        Room.databaseBuilder(
            androidContext(),
            EcommerceDatabase::class.java, "simple_ecommerce.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}
val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://private-4639ce-ecommerce56.apiary-mock.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IProductRepository> { ProductRepository(get(), get(), get()) }
}