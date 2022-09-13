package com.example.admin.fragments.fragmentAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.admin.R
import com.example.admin.utils.DBReadProduct
import com.google.android.material.card.MaterialCardView

class categoryAdapter(val activity: FragmentActivity?,val categoryList: ArrayList<DBReadProduct>) : RecyclerView.Adapter<categoryAdapter.ViewData>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewData {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.category_filter_item,parent,false)
        return  ViewData(view)
    }

    override fun onBindViewHolder(holder: ViewData, position: Int) {
        holder.name.setText(categoryList.get(position).pname)
        holder.price.setText(categoryList.get(position).pprice)
        Glide.with(activity!!).load(categoryList.get(position).pimage).into(holder.img)
        holder.discount.setText(categoryList.get(position).pdis)
    }

    override fun getItemCount(): Int {

        return categoryList.size
    }

    class ViewData(itemView: View) : RecyclerView.ViewHolder(itemView){

        var name = itemView.findViewById<TextView>(R.id.productNameTxtU)
        var price = itemView.findViewById<TextView>(R.id.productPriceTxtU)
        var img = itemView.findViewById<ImageView>(R.id.productImgU)
        var discount = itemView.findViewById<TextView>(R.id.productDiscountTxtU)
        var cardView = itemView.findViewById<MaterialCardView>(R.id.cardView)

    }
}