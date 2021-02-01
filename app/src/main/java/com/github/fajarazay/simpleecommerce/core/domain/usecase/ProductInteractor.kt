package com.github.fajarazay.simpleecommerce.core.domain.usecase

import com.github.fajarazay.simpleecommerce.core.data.Resource
import com.github.fajarazay.simpleecommerce.core.domain.model.ProductItem
import com.github.fajarazay.simpleecommerce.core.domain.model.ProductPromo
import com.github.fajarazay.simpleecommerce.core.domain.repository.IProductRepository
import kotlinx.coroutines.flow.Flow

/**
 * Created by fajarseptian on 1/31/21.
 *
 * @Author Fajar Septian
 * @Email fajarajay10@gmail.com
 * @Github https://github.com/fajarazay
 */
class ProductInteractor(private val productRepository: IProductRepository) : ProductUseCase {
    override fun getListProduct(): Flow<Resource<List<ProductItem>>> {
        return productRepository.getListProduct()
    }

    override fun getListProductPurchase(): Flow<List<ProductPromo>> {
        return productRepository.getListProductPurchase()
    }

    override fun buyProduct(product: ProductPromo) {
        return productRepository.buyProduct(product)
    }

}