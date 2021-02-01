package com.github.fajarazay.simpleecommerce.di

import com.github.fajarazay.simpleecommerce.core.domain.usecase.ProductInteractor
import com.github.fajarazay.simpleecommerce.core.domain.usecase.ProductUseCase
import com.github.fajarazay.simpleecommerce.ui.detailproduct.DetailProductViewModel
import com.github.fajarazay.simpleecommerce.ui.home.HomeViewModel
import com.github.fajarazay.simpleecommerce.ui.login.LoginViewModel
import com.github.fajarazay.simpleecommerce.ui.purchasehistory.PurchaseHistoryViewModel
import com.github.fajarazay.simpleecommerce.ui.search.SearchViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by fajarseptian on 1/31/21.
 *
 * @Author Fajar Septian
 * @Email fajarajay10@gmail.com
 * @Github https://github.com/fajarazay
 */
val useCaseModule = module {
    factory<ProductUseCase> { ProductInteractor(get()) }
}

@ExperimentalCoroutinesApi
@FlowPreview
val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailProductViewModel(get()) }
    viewModel { PurchaseHistoryViewModel(get()) }
    viewModel { SearchViewModel() }
    viewModel { LoginViewModel() }
}