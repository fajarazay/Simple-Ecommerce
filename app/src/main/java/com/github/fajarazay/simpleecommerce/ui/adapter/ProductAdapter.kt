package com.github.fajarazay.simpleecommerce.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.fajarazay.simpleecommerce.R
import com.github.fajarazay.simpleecommerce.core.domain.model.ProductPromo
import com.github.fajarazay.simpleecommerce.databinding.ItemListProductBinding

class ProductAdapter : RecyclerView.Adapter<ProductAdapter.ListViewHolder>() {

    var onItemClick: ((ProductPromo) -> Unit)? = null
    private var listData = ArrayList<ProductPromo>()

    fun setData(newListData: List<ProductPromo>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding: ItemListProductBinding =
            ItemListProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(private val binding: ItemListProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ProductPromo) {
            binding.textViewName.text = data.title

            if (data.loved == 1) {
                binding.fabLove.setImageDrawable(
                    ContextCompat.getDrawable(
                        itemView.context,
                        R.drawable.ic_favorite
                    )
                )
            } else {
                binding.fabLove.setImageDrawable(
                    ContextCompat.getDrawable(
                        itemView.context,
                        R.drawable.ic_favorite_border
                    )
                )
            }

            Glide.with(itemView.context)
                .load(data.imageUrl)
                .into(binding.ivProduct)

            var isLove = data.loved

            binding.fabLove.setOnClickListener {
                isLove = if (isLove == 1) {
                    0
                } else {
                    1
                }
                setStatusFavorite(isLove)
            }
        }

        private fun setStatusFavorite(statusFavorite: Int) {
            if (statusFavorite == 1) {
                binding.fabLove.setImageDrawable(
                    ContextCompat.getDrawable(
                        itemView.context,
                        R.drawable.ic_favorite
                    )
                )
            } else {
                binding.fabLove.setImageDrawable(
                    ContextCompat.getDrawable(
                        itemView.context,
                        R.drawable.ic_favorite_border
                    )
                )
            }
        }

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }

}