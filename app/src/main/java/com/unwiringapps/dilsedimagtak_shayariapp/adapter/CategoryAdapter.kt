package com.unwiringapps.dilsedimagtak_shayariapp.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.unwiringapps.dilsedimagtak_shayariapp.MainActivity
import com.unwiringapps.dilsedimagtak_shayariapp.Model.CatModel
import com.unwiringapps.dilsedimagtak_shayariapp.databinding.ItemCatBinding

class CategoryAdapter(val mainActivity: MainActivity, val listrr: ArrayList<CatModel>) :
    RecyclerView.Adapter<CategoryAdapter.CatViewHolder>() {

    val colorLister =
        arrayListOf<String>("#2193b0", "#cc2b5e", "#ee9ca7", "#42275a", "#de6262", "#06beb6", "#000000")
    val colorLister1 =
        arrayListOf<String>("#E2F516", "#FBF6D9", "#2B1B17", "#EE82EE", "#DCD0FF", "#FFF5EE", "#ee9ca7")

    class CatViewHolder(val binding: ItemCatBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        return CatViewHolder(
            ItemCatBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        holder.binding.itemTxt.text = listrr[position].name.toString()
//        holder.binding.rcvCard.setBackgroundColor(Color.parseColor(colorLister[position]))
        holder.binding.rcvCard.setCardBackgroundColor(Color.parseColor(colorLister[position]))
        holder.binding.itemTxt.setTextColor(Color.parseColor(colorLister1[position]))
    }

    override fun getItemCount() = listrr.size
}