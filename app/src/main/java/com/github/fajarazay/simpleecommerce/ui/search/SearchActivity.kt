package com.github.fajarazay.simpleecommerce.ui.search

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.fajarazay.simpleecommerce.ui.adapter.ProductPurchaseAdapter
import com.github.fajarazay.simpleecommerce.core.utils.viewBinding
import com.github.fajarazay.simpleecommerce.databinding.ActivitySearchBinding
import com.github.fajarazay.simpleecommerce.ui.detailproduct.DetailProductActivity
import org.koin.android.viewmodel.ext.android.viewModel

class SearchActivity : AppCompatActivity() {
    private val binding by viewBinding(ActivitySearchBinding::inflate)
    private val viewModel: SearchViewModel by viewModel()
    private lateinit var searchAdapter: ProductPurchaseAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.edtSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                viewModel.filterData(p0.toString())
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })

        binding.btnBack.setOnClickListener {
            finish()
        }

        searchAdapter = ProductPurchaseAdapter()
        searchAdapter.onItemClick = { selectedData ->
            val intent = Intent(this, DetailProductActivity::class.java)
            intent.putExtra(DetailProductActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }

        with(binding.rvSearch) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = searchAdapter
        }

        viewModel.resultSearchProducts.observe(this, { data ->
            Log.d("dataSearch: ", data.toString())
            searchAdapter.setData(data)
        })
    }
}