package com.github.fajarazay.simpleecommerce.core.data.source.remote.network

import com.github.fajarazay.simpleecommerce.core.data.source.remote.response.ProductResponse
import retrofit2.http.GET

/**
 * Created by fajarseptian on 1/31/21.
 *
 * @Author Fajar Septian
 * @Email fajarajay10@gmail.com
 * @Github https://github.com/fajarazay
 */
interface ApiService {

    @GET("home")
    suspend fun getListProduct(): ProductResponse

}