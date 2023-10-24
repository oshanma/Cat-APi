package com.example.cat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class CatAdapter(private val catList: MutableList<CatItem>): RecyclerView.Adapter<CatAdapter.CatViewHolder>() {

    data class CatItem(val imageUrl: String, val name: String, val description: String)

    class CatViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val catImage: ImageView
        val catName: TextView
        val catDescription: TextView

        init {
            catImage = view.findViewById(R.id.cat_image)
            catName = view.findViewById(R.id.cat_name)
            catDescription = view.findViewById(R.id.cat_description)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cat_item, parent, false)
        return CatViewHolder(view)
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        val catItem = catList[position]
        Glide.with(holder.itemView)
            .load(catItem.imageUrl)
            .centerCrop()
            .into(holder.catImage)
        holder.catName.text = catItem.name
        holder.catDescription.text = catItem.description
    }

    override fun getItemCount(): Int {
        return catList.size
    }
}

