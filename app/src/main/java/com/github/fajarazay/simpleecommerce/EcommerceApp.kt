package com.github.fajarazay.simpleecommerce

import android.app.Application
import com.github.fajarazay.simpleecommerce.core.di.databaseModule
import com.github.fajarazay.simpleecommerce.core.di.networkModule
import com.github.fajarazay.simpleecommerce.core.di.repositoryModule
import com.github.fajarazay.simpleecommerce.di.useCaseModule
import com.github.fajarazay.simpleecommerce.di.viewModelModule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

/**
 * Created by fajarseptian on 1/31/21.
 *
 * @Author Fajar Septian
 * @Email fajarajay10@gmail.com
 * @Github https://github.com/fajarazay
 */
@FlowPreview
@ExperimentalCoroutinesApi
class EcommerceApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@EcommerceApp)
            modules(
                listOf(
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule,
                    databaseModule
                )
            )
        }
    }
}