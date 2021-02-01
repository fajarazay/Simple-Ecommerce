package com.github.fajarazay.simpleecommerce.core.data.source.local.room

import androidx.room.*
import com.github.fajarazay.simpleecommerce.core.data.source.local.enitity.ProductEntity
import kotlinx.coroutines.flow.Flow

/**
 * Created by FAJAR SEPTIAN on 21/10/20.
 *
 * @Author Fajar Septian
 * @Email fajarajay10@gmail.com
 * @Github https://github.com/fajarazay
 */

@Dao
interface ProductDao {
    @Transaction
    @Query("SELECT * FROM product")
    fun getListProductPurchase(): Flow<List<ProductEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProductPurchase(product: ProductEntity)

}