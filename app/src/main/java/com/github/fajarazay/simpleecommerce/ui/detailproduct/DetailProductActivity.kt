package com.github.fajarazay.simpleecommerce.ui.detailproduct

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.github.fajarazay.simpleecommerce.R
import com.github.fajarazay.simpleecommerce.core.domain.model.ProductPromo
import com.github.fajarazay.simpleecommerce.core.utils.hide
import com.github.fajarazay.simpleecommerce.core.utils.show
import com.github.fajarazay.simpleecommerce.core.utils.viewBinding
import com.github.fajarazay.simpleecommerce.databinding.ActivityDetailProductBinding
import com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener
import org.koin.android.viewmodel.ext.android.viewModel


class DetailProductActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityDetailProductBinding::inflate)
    private lateinit var dataDetail: ProductPromo
    private val viewModel: DetailProductViewModel by viewModel()

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        dataDetail = intent.getParcelableExtra<ProductPromo>(EXTRA_DATA) as ProductPromo

        binding.contentDetailProduct.tvTitleProduct.text = dataDetail.title
        binding.contentDetailProduct.tvDetailDescription.text = dataDetail.description
        binding.contentDetailProduct.tvDetailPrice.text = dataDetail.price

        var statusFavorite = dataDetail.loved

        setStatusFavorite(statusFavorite)

        Glide.with(this@DetailProductActivity)
            .load(dataDetail.imageUrl)
            .into(binding.ivProduct)


        binding.appBar.addOnOffsetChangedListener(OnOffsetChangedListener { _, verticalOffset ->
            if (verticalOffset == 0) {
                binding.collapsingToolbar.title = ""
                binding.contentDetailProduct.tvTitleProduct.show()
            } else {
                binding.collapsingToolbar.title = dataDetail.title
                binding.contentDetailProduct.tvTitleProduct.hide()
            }
        })

        binding.fabFav.setOnClickListener {
            statusFavorite = if (statusFavorite == 1) {
                0
            } else {
                1
            }
            setStatusFavorite(statusFavorite)

        }

        binding.contentDetailProduct.btnBuy.setOnClickListener {
            viewModel.buyProduct(dataDetail)
        }

    }

    private fun setStatusFavorite(statusFavorite: Int) {
        if (statusFavorite == 1) {
            binding.fabFav.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite))
        } else {
            binding.fabFav.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_favorite_border
                )
            )
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.detail_product_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_share -> {
                val intent = Intent()
                intent.action = Intent.ACTION_SEND
                intent.putExtra(
                    Intent.EXTRA_TEXT,
                    "${dataDetail.title}\nPrice: ${dataDetail.price}\n\n${dataDetail.description}"
                )
                intent.type = "text/plain"
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}