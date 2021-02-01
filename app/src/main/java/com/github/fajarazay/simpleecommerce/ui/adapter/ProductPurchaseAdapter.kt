package com.github.fajarazay.simpleecommerce.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.fajarazay.simpleecommerce.core.domain.model.ProductPromo
import com.github.fajarazay.simpleecommerce.databinding.ItemListProductPurchaseBinding

class ProductPurchaseAdapter : RecyclerView.Adapter<ProductPurchaseAdapter.ListViewHolder>() {

    var onItemClick: ((ProductPromo) -> Unit)? = null

    private var listData = ArrayList<ProductPromo>()

    fun setData(newListData: List<ProductPromo>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding: ItemListProductPurchaseBinding =
            ItemListProductPurchaseBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ListViewHolder(binding)
    }

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(private val binding: ItemListProductPurchaseBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ProductPromo) {
            binding.tvItemName.text = data.title
            binding.tvItemPrice.text = data.price

            Glide.with(itemView.context)
                .load(data.imageUrl)
                .into(binding.ivProductPurchase)
        }

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }

}