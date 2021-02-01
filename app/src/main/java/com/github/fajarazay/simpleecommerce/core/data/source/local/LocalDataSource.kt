package com.github.fajarazay.simpleecommerce.core.data.source.local

import com.github.fajarazay.simpleecommerce.core.data.source.local.enitity.ProductEntity
import com.github.fajarazay.simpleecommerce.core.data.source.local.room.ProductDao

import kotlinx.coroutines.flow.Flow


/**
 * Created by FAJAR SEPTIAN on 21/10/20.
 *
 * @Author Fajar Septian
 * @Email fajarajay10@gmail.com
 * @Github https://github.com/fajarazay
 */

class LocalDataSource(
    private val productDao: ProductDao,
) {
    fun getListProductPurchase(): Flow<List<ProductEntity>> = productDao.getListProductPurchase()

    fun insertProductPurchase(product: ProductEntity) =
        productDao.insertProductPurchase(product)


}