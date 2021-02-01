package com.github.fajarazay.simpleecommerce.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.github.fajarazay.simpleecommerce.core.domain.usecase.ProductUseCase

class HomeViewModel(private val useCase: ProductUseCase) : ViewModel() {

    fun getListProduct() = useCase.getListProduct().asLiveData()
}