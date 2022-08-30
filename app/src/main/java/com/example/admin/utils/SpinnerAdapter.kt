package com.example.admin.utils

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.admin.R
import com.example.admin.activity.insertActivity.view.insertActivity

class SpinnerAdapter(val activity: insertActivity, val list: Array<String>) : BaseAdapter() {
    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(p0: Int): Any {

        return 0
    }

    override fun getItemId(p0: Int): Long {

        return 0
    }

    @SuppressLint("ViewHolder")
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var view = LayoutInflater.from(activity).inflate(R.layout.spinner_item,p2,false)

        var text = view.findViewById<TextView>(R.id.categoryNameTxt)

        text.setText(list[p0])

        return view
    }

}