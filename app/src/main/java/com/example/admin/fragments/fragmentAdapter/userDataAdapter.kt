package com.example.admin.fragments.fragmentAdapter

import android.content.Intent
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
import com.example.admin.activity.cartActivity.view.cartActivity
import com.example.admin.utils.DBInsertProduct
import com.example.admin.utils.DBReadProduct
import com.example.admin.utils.DBTemp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class userDataAdapter(val activity: FragmentActivity?, val productList: ArrayList<DBReadProduct>) :

    RecyclerView.Adapter<ViewData>() {

    var quaList  = arrayListOf<DBTemp>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewData {
        var view = LayoutInflater.from(activity).inflate(R.layout.product_item, parent, false)
        return ViewData(view)
    }

    override fun onBindViewHolder(holder: ViewData, position: Int) {
        holder.productNameTxt.setText(productList[position].pname)
        holder.productPriceTxt.setText(productList[position].pprice)
        holder.productDescriptionTxt.setText(productList[position].pdes)
        holder.productCategoryTxt.setText(productList[position].pcat)
        Glide.with(activity!!).load(productList[position].pimage).into(holder.productImg)

        holder.cartCardView.setOnClickListener {

            //addToCart(position)

            add(position)

        }
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    private fun addToCart(position: Int) {

        var firebaseDatabase = FirebaseDatabase.getInstance()
        var databaseReference = firebaseDatabase.reference

        var firebaseAuth = FirebaseAuth.getInstance()
        var user = firebaseAuth.currentUser
        var uid = user?.uid

        var cartData = DBInsertProduct(
            productList[position].pname,
            productList[position].pprice,
            productList[position].pdes,
            productList[position].pcat,
            productList[position].cid.toInt(),
            productList[position].pimage,

        )

        databaseReference.child("Cart").child(uid.toString()).push().setValue(cartData)

        var intent = Intent(activity,cartActivity::class.java)
        activity?.startActivity(intent)

    }

    private fun add(position: Int) {

        var firebaseDatabase = FirebaseDatabase.getInstance()
        var databaseReference = firebaseDatabase.reference

        var firebaseAuth = FirebaseAuth.getInstance()
        var user = firebaseAuth.currentUser
        var uid = user?.uid

        var cartData = DBTemp(
            productList[position].pname,
            productList[position].pprice,
            productList[position].pdes,
            productList[position].pcat,
            productList[position].cid.toInt(),
            productList[position].pimage,
            1

            )

        databaseReference.child("Cart").child(uid.toString()).push().setValue(cartData)

        var intent = Intent(activity,cartActivity::class.java)
        activity?.startActivity(intent)

    }



}

class ViewData(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var productNameTxt = itemView.findViewById<TextView>(R.id.productNameTxt)
    var productPriceTxt = itemView.findViewById<TextView>(R.id.productPriceTxt)
    var productDescriptionTxt = itemView.findViewById<TextView>(R.id.productDescriptionTxt)
    var productCategoryTxt = itemView.findViewById<TextView>(R.id.productCategoryTxt)
    var productImg = itemView.findViewById<ImageView>(R.id.productImg)
    var deleteCardView = itemView.findViewById<CardView>(R.id.deleteCardView)
    var editCardView = itemView.findViewById<CardView>(R.id.editCardView)
    var cartCardView = itemView.findViewById<CardView>(R.id.cartCardView)

}

