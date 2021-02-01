package com.github.fajarazay.simpleecommerce.core.data.source.remote

import com.github.fajarazay.simpleecommerce.core.data.source.remote.network.ApiResponse
import com.github.fajarazay.simpleecommerce.core.data.source.remote.network.ApiService
import com.github.fajarazay.simpleecommerce.core.data.source.remote.response.ProductItemResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

/**
 * Created by fajarseptian on 1/31/21.
 *
 * @Author Fajar Septian
 * @Email fajarajay10@gmail.com
 * @Github https://github.com/fajarazay
 */
class RemoteDataSource(private val apiService: ApiService) {
    suspend fun getListProduct(): Flow<ApiResponse<List<ProductItemResponse>>> {

        return flow {
            try {
                val response = apiService.getListProduct()
                if (response.isNotEmpty()) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.localizedMessage))
            }
        }.flowOn(Dispatchers.IO)
    }
}