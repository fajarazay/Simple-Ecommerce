package com.github.fajarazay.simpleecommerce.core.utils

import com.github.fajarazay.simpleecommerce.core.data.source.local.enitity.ProductEntity
import com.github.fajarazay.simpleecommerce.core.data.source.remote.response.ProductItemResponse
import com.github.fajarazay.simpleecommerce.core.domain.model.Category
import com.github.fajarazay.simpleecommerce.core.domain.model.Data
import com.github.fajarazay.simpleecommerce.core.domain.model.ProductItem
import com.github.fajarazay.simpleecommerce.core.domain.model.ProductPromo

/**
 * Created by fajarseptian on 1/31/21.
 *
 * @Author Fajar Septian
 * @Email fajarajay10@gmail.com
 * @Github https://github.com/fajarazay
 */
object DataMapper {
    fun mapProductResponseToListProductEntity(response: List<ProductItemResponse>): List<ProductItem> {
        return response.map {
            ProductItem(
                data = Data(
                    category = it.dataResponse.categoryResponse.map { category ->
                        Category(
                            id = category.id,
                            name = category.name,
                            imageUrl = category.imageUrl
                        )
                    },
                    productPromo = it.dataResponse.productPromoResponse.map { promo ->
                        ProductPromo(
                            id = promo.id,
                            description = promo.description,
                            imageUrl = promo.imageUrl,
                            loved = promo.loved,
                            title = promo.title,
                            price = promo.price
                        )
                    }
                )
            )
        }
    }

    fun mapProductEntityToProductDomain(productPromo: ProductPromo): ProductEntity {
        return ProductEntity(
            idProduct = productPromo.id.toInt(),
            title = productPromo.title,
            description = productPromo.description,
            imageUrl = productPromo.imageUrl,
            price = productPromo.price,
            loved = productPromo.loved
        )
    }

    fun mapListProductEntityToProductDomain(listProductEntity: List<ProductEntity>): List<ProductPromo> {
        return listProductEntity.map { productEntity ->
            ProductPromo(
                id = productEntity.id.toString(),
                title = productEntity.title,
                description = productEntity.description,
                imageUrl = productEntity.imageUrl,
                price = productEntity.price,
                loved = productEntity.loved
            )
        }
    }
}