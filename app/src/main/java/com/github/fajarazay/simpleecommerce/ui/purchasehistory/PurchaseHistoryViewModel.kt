package com.github.fajarazay.simpleecommerce.ui.purchasehistory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.github.fajarazay.simpleecommerce.core.domain.usecase.ProductUseCase

/**
 * Created by fajarseptian on 1/31/21.
 *
 * @Author Fajar Septian
 * @Email fajarajay10@gmail.com
 * @Github https://github.com/fajarazay
 */
class PurchaseHistoryViewModel(useCase: ProductUseCase) : ViewModel() {

    val getListProductPurchase = useCase.getListProductPurchase().asLiveData()

}