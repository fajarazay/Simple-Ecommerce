package com.github.fajarazay.simpleecommerce.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.github.fajarazay.simpleecommerce.core.data.source.local.enitity.ProductEntity

/**
 * Created by FAJAR SEPTIAN on 21/10/20.
 *
 * @Author Fajar Septian
 * @Email fajarajay10@gmail.com
 * @Github https://github.com/fajarazay
 */

@Database(
    entities = [ProductEntity::class],
    version = 1,
    exportSchema = false
)
abstract class EcommerceDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao

}