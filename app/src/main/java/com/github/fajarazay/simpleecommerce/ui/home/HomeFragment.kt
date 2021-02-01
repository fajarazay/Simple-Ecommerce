package com.github.fajarazay.simpleecommerce.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.fajarazay.simpleecommerce.ui.adapter.CategoryAdapter
import com.github.fajarazay.simpleecommerce.ui.adapter.ProductAdapter
import com.github.fajarazay.simpleecommerce.core.data.Resource
import com.github.fajarazay.simpleecommerce.core.utils.autoCleared
import com.github.fajarazay.simpleecommerce.core.utils.hide
import com.github.fajarazay.simpleecommerce.core.utils.show
import com.github.fajarazay.simpleecommerce.core.utils.toast
import com.github.fajarazay.simpleecommerce.databinding.FragmentHomeBinding
import com.github.fajarazay.simpleecommerce.ui.detailproduct.DetailProductActivity
import com.github.fajarazay.simpleecommerce.ui.search.SearchActivity
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {
    private var binding: FragmentHomeBinding by autoCleared()
    private val viewModel: HomeViewModel by viewModel()
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var productAdapter: ProductAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            categoryAdapter = CategoryAdapter()
            productAdapter = ProductAdapter()

            binding.btnSearchArea.setOnClickListener {
                startActivity(Intent(context, SearchActivity::class.java))
            }

            productAdapter.onItemClick = { selectedData ->
                val intent = Intent(context, DetailProductActivity::class.java)
                intent.putExtra(DetailProductActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }

            with(
                binding.rvCategory
            ) {
                layoutManager = GridLayoutManager(context, 1, GridLayoutManager.HORIZONTAL, false)
                setHasFixedSize(true)
                isNestedScrollingEnabled = false
                adapter = categoryAdapter
            }


            with(
                binding.rvProduct
            ) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                isNestedScrollingEnabled = false
                adapter = productAdapter
            }

            loadListProduct()

        }
    }

    private fun loadListProduct() {
        viewModel.getListProduct().observe(viewLifecycleOwner, { data ->

            if (data != null) {
                Log.d("viewModel ", data.data.toString())

                when (data) {
                    is Resource.Loading -> {
                        binding.progressBar.show()
                    }
                    is Resource.Success -> {
                        binding.progressBar.hide()
                        data.data?.map {
                            categoryAdapter.setData(it.data.category)
                            productAdapter.setData(it.data.productPromo)
                        }
                    }
                    is Resource.Error -> {
                        binding.progressBar.hide()
                        data.message?.let { context?.toast(it) }
                    }
                }
            }
        })
    }
}