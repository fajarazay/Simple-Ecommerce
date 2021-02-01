package com.github.fajarazay.simpleecommerce.core.data

import com.github.fajarazay.simpleecommerce.core.data.source.remote.RemoteDataSource
import com.github.fajarazay.simpleecommerce.core.data.source.remote.network.ApiResponse
import com.github.fajarazay.simpleecommerce.core.domain.model.ProductItem
import com.github.fajarazay.simpleecommerce.core.domain.model.ProductPromo
import com.github.fajarazay.simpleecommerce.core.domain.repository.IProductRepository
import com.github.fajarazay.simpleecommerce.core.utils.AppExecutors
import com.github.fajarazay.simpleecommerce.core.utils.DataMapper
import com.github.fajarazay.simpleecommerce.core.data.source.local.LocalDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

/**
 * Created by fajarseptian on 1/31/21.
 *
 * @Author Fajar Septian
 * @Email fajarajay10@gmail.com
 * @Github https://github.com/fajarazay
 */
class ProductRepository(
    private val remoteDataSource: RemoteDataSource, private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IProductRepository {
    override fun getListProduct(): Flow<Resource<List<ProductItem>>> = flow {
        emit(Resource.Loading())

        when (val apiResponse = remoteDataSource.getListProduct().first()) {
            is ApiResponse.Success -> {
                val data = DataMapper.mapProductResponseToListProductEntity(apiResponse.data)
                emit(Resource.Success(data))
            }
            is ApiResponse.Empty -> {
                emit(Resource.Success<List<ProductItem>>(emptyList()))
            }
            is ApiResponse.Error -> {
                emit(Resource.Error<List<ProductItem>>(apiResponse.errorMessage))
            }

        }
    }

    override fun getListProductPurchase(): Flow<List<ProductPromo>> =
        localDataSource.getListProductPurchase().map {
            DataMapper.mapListProductEntityToProductDomain(it)
        }

    override fun buyProduct(product: ProductPromo) {
        val productEntity = DataMapper.mapProductEntityToProductDomain(product)
        appExecutors.diskIO().execute {
            localDataSource.insertProductPurchase(productEntity)
        }
    }

}