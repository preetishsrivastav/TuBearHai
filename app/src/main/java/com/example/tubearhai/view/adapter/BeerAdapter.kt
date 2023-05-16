package com.example.tubearhai.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tubearhai.databinding.ItemBeerBinding
import com.example.tubearhai.model.BeerData

class BeerAdapter(private val context:Context):RecyclerView.Adapter<BeerAdapter.BeerViewHolder>() {

    inner class BeerViewHolder(val binding: ItemBeerBinding): RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<BeerData>() {
        override fun areItemsTheSame(oldItem:BeerData, newItem: BeerData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: BeerData, newItem: BeerData): Boolean {
            return oldItem == newItem
        }
    }
    private val differ = AsyncListDiffer(this, diffCallback)
    var beers: List<BeerData>
        get() = differ.currentList
        set(value) { differ.submitList(value) }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeerAdapter.BeerViewHolder {
        return BeerViewHolder(ItemBeerBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: BeerAdapter.BeerViewHolder, position: Int) {
        holder.binding.apply {
            val beer = beers[position]
            Glide.with(context)
                .load(beer.imageUrl)
                .into(ivBeerImage)

            beerName.text=beer.name
            beerTagline.text=beer.tagline


        }
    }

    override fun getItemCount(): Int {
       return beers.size
    }


}