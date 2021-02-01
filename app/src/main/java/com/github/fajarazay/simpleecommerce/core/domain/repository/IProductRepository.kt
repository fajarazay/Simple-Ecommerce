package com.github.fajarazay.simpleecommerce.core.domain.repository

import com.github.fajarazay.simpleecommerce.core.data.Resource
import com.github.fajarazay.simpleecommerce.core.domain.model.ProductItem
import com.github.fajarazay.simpleecommerce.core.domain.model.ProductPromo
import kotlinx.coroutines.flow.Flow

/**
 * Created by fajarseptian on 1/31/21.
 *
 * @Author Fajar Septian
 * @Email fajarajay10@gmail.com
 * @Github https://github.com/fajarazay
 */
interface IProductRepository {
    fun getListProduct(): Flow<Resource<List<ProductItem>>>
    fun getListProductPurchase(): Flow<List<ProductPromo>>
    fun buyProduct(product: ProductPromo)
}