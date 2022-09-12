package com.example.admin.fragments.fragmentAdapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.admin.R
import com.example.admin.utils.DBCategory

class userCategoryAdpater(
    val activity: FragmentActivity?,
    val categoryList: ArrayList<DBCategory>
) : RecyclerView.Adapter<userCategoryAdpater.ViewData>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewData {
       var view = LayoutInflater.from(parent.context).inflate(R.layout.categoty_item,parent,false)
        return ViewData(view)
    }

    override fun onBindViewHolder(holder: ViewData, position: Int) {
        holder.categoryTxt.setText(categoryList[position].cat)

    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    class ViewData(itemView: View) : RecyclerView.ViewHolder(itemView){

        var categoryTxt = itemView.findViewById<TextView>(R.id.categoryTxt)

    }
}