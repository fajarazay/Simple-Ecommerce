package com.github.fajarazay.simpleecommerce.domain

import com.github.fajarazay.simpleecommerce.core.data.ProductRepository
import com.github.fajarazay.simpleecommerce.core.data.Resource
import com.github.fajarazay.simpleecommerce.core.domain.model.ProductItem
import com.github.fajarazay.simpleecommerce.core.domain.model.ProductPromo
import com.github.fajarazay.simpleecommerce.core.domain.usecase.ProductInteractor
import com.github.fajarazay.simpleecommerce.core.domain.usecase.ProductUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ProductUseCaseTest {

    @Mock
    private lateinit var repository: ProductRepository

    private lateinit var useCase: ProductUseCase

    @Before
    fun setUp() {
        useCase = ProductInteractor(repository)
        val dummyListProduct: Flow<Resource<List<ProductItem>>> = flow { Resource.Success(listOf(productPromo)) }
        val dummyListProductPurchase: Flow<List<ProductPromo>> = flow { Resource.Success(listOf(productPromo)) }

        Mockito.`when`(repository.getListProduct()).thenReturn(dummyListProduct)
        Mockito.`when`(repository.getListProductPurchase()).thenReturn(dummyListProductPurchase)
    }

    @Test
    fun `should get list product`() {
        useCase.getListProduct()
        Mockito.verify(repository).getListProduct()
        Mockito.verifyNoMoreInteractions(repository)
    }

    @Test
    fun `should get list product purchase`() {
        useCase.getListProductPurchase()
        Mockito.verify(repository).getListProductPurchase()
        Mockito.verifyNoMoreInteractions(repository)
    }

    companion object {
        val productPromo = ProductPromo(
                id = "6723",
                imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/7/76/Nintendo-Switch-Console-Docked-wJoyConRB.jpg/430px-Nintendo-Switch-Console-Docked-wJoyConRB.jpg",
                title = "Nitendo Switch",
                description = "The Nintendo Switch was released on March 3, 2017 and is Nintendo's second entry in the eighth generation of home video game consoles. The system was code-named Nintendo NX prior to its official announcement. It is a hybrid device that can be used as a home console inserted to the Nintendo Switch Dock attached to a television, stood up on a table with the kickstand, or as a tablet-like portable console. It features two detachable wireless controllers called Joy-Con, that can be used individually or attached to a grip to provide a more traditional game pad form. Both Joy-Con are built with motion sensors and HD Rumble, Nintendo's haptic vibration feedback system for improved gameplay experiences. However, only the right Joy-Con has an NFC reader on its analog joystick for Amiibo and an IR sensor on the back. The Nintendo Switch Pro Controller is a traditional style controller much like the one of the Gamecube.",
                price = "$340",
                loved = 0
        )
    }
}