package com.github.fajarazay.simpleecommerce.ui.purchasehistory

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.fajarazay.simpleecommerce.ui.adapter.ProductPurchaseAdapter
import com.github.fajarazay.simpleecommerce.core.utils.viewBinding
import com.github.fajarazay.simpleecommerce.databinding.ActivityPurchaseHistoryBinding
import com.github.fajarazay.simpleecommerce.ui.detailproduct.DetailProductActivity
import com.github.fajarazay.simpleecommerce.ui.detailproduct.DetailProductActivity.Companion.EXTRA_DATA
import org.koin.android.viewmodel.ext.android.viewModel


class PurchaseHistoryActivity : AppCompatActivity() {
    private val viewModel: PurchaseHistoryViewModel by viewModel()
    private val binding by viewBinding(ActivityPurchaseHistoryBinding::inflate)
    private lateinit var productPurchaseAdapter: ProductPurchaseAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Purchase History"

        productPurchaseAdapter = ProductPurchaseAdapter()
        productPurchaseAdapter.onItemClick = { selectedData ->
            val intent = Intent(this, DetailProductActivity::class.java)
            intent.putExtra(EXTRA_DATA, selectedData)
            startActivity(intent)
        }

        with(binding.rvProductPurchase) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = productPurchaseAdapter
        }

        viewModel.getListProductPurchase.observe(this, { data ->
            productPurchaseAdapter.setData(data)
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}