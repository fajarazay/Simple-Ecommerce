package com.github.fajarazay.simpleecommerce.ui.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.fajarazay.simpleecommerce.core.domain.model.ProductPromo
import com.github.fajarazay.simpleecommerce.core.utils.Dummy
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*

/**
 * Created by fajarseptian on 1/31/21.
 *
 * @Author Fajar Septian
 * @Email fajarajay10@gmail.com
 * @Github https://github.com/fajarazay
 */
class SearchViewModel : ViewModel() {
    private val _gson = Gson()

    private val _dummyProduct: Type = object : TypeToken<ArrayList<ProductPromo?>?>() {}.type

    private val listDummyProduct: List<ProductPromo> =
        _gson.fromJson(Dummy.stringJson, _dummyProduct)

    private val _products = MutableLiveData<List<ProductPromo>>().apply { value = emptyList() }
    val resultSearchProducts: LiveData<List<ProductPromo>> = _products

    fun filterData(keyword: String) {
        Log.d("keyword: ", keyword)
        if (keyword.isNotEmpty()) {
            _products.postValue(listDummyProduct.filter { it.title.contains(keyword, true) })
        } else {
            _products.postValue(emptyList())
        }
    }

}