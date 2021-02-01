package com.github.fajarazay.simpleecommerce.ui.detailproduct

import androidx.lifecycle.ViewModel
import com.github.fajarazay.simpleecommerce.core.domain.model.ProductPromo
import com.github.fajarazay.simpleecommerce.core.domain.usecase.ProductUseCase

/**
 * Created by fajarseptian on 1/31/21.
 *
 * @Author Fajar Septian
 * @Email fajarajay10@gmail.com
 * @Github https://github.com/fajarazay
 */
class DetailProductViewModel(private val useCase: ProductUseCase) : ViewModel() {
    fun buyProduct(product: ProductPromo) =
        useCase.buyProduct(product)
}