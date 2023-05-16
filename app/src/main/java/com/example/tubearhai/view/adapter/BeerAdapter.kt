package com.example.tubearhai.view.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tubearhai.databinding.ItemBeerBinding
import com.example.tubearhai.model.BeerData
import com.example.tubearhai.utils.BottomSheetDialog

class BeerAdapter(private val context: Context, private val bottomSheet: (String, String, List<String>) -> Unit):RecyclerView.Adapter<BeerAdapter.BeerViewHolder>() {

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
                .load(beer.image_url)
                .into(ivBeerImage)

            beerName.text=beer.name
            beerTagline.text="'${beer.tagline}'"
            ibShare.setOnClickListener {
                shareBeer(beer.image_url)
            }
            root.setOnLongClickListener {
                bottomSheet(beer.first_brewed,beer.description,beer.food_pairing)
                true
            }

        }
    }

    override fun getItemCount(): Int {
       return beers.size
    }

    private fun shareBeer(beerImage:String){

        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "text/plain"

        // Set the URL of the image as the content to be shared
        shareIntent.putExtra(Intent.EXTRA_SUBJECT,"Checkout This Cool Beer")

        shareIntent.putExtra(Intent.EXTRA_TEXT, beerImage)

        // Start the activity for sharing
        context.startActivity(Intent.createChooser(shareIntent, "Share Image URL"))

    }

}