package com.example.admin.activity.cartActivity.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.admin.activity.billActivity.view.billActivity
import com.example.admin.activity.cartActivity.controller.cartAdapter
import com.example.admin.databinding.ActivityCartBinding
import com.example.admin.utils.DBCartProduct
import com.example.admin.utils.DBTemp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class cartActivity : AppCompatActivity() {

    lateinit var binding: ActivityCartBinding
    val cartList = arrayListOf<DBCartProduct>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        readProductData()

        buyProduct()
    }

    private fun buyProduct() {
        binding.buyButton.setOnClickListener {

            var intent = Intent(this,billActivity::class.java)
            startActivity(intent)


        }
    }

    private fun readProductData() {

        var firebaseDatabase = FirebaseDatabase.getInstance()
        var databaseReference = firebaseDatabase.reference

        var firebaseAuth = FirebaseAuth.getInstance()
        var user = firebaseAuth.currentUser
        var uid = user?.uid

        databaseReference.child("Cart").child(uid.toString()).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                cartList.clear()

                for (x in snapshot.children) {


                    var id = x.child("pid").getValue().toString()
                    var pname = x.child("pname").getValue().toString()
                    var pprice = x.child("pprice").getValue().toString()
                    var pdes = x.child("pdes").getValue().toString()
                    var pcat = x.child("pcat").getValue().toString()
                    var pimage = x.child("pimage").getValue().toString()
                    var cid = x.child("cid").getValue().toString()
                    var qua = x.child("qua").getValue().toString()
                    var key = x.key.toString()

                    var productData = DBCartProduct(id, pname, pprice, pdes, pcat, pimage, key, cid,qua)
                    cartList.add(productData)
                }

                setProductData(cartList)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

    }

    fun setProductData(productList: ArrayList<DBCartProduct>) {

        var adpater = cartAdapter(this, productList)
        var layoutManager = LinearLayoutManager(this)
        binding.cartRecyclerView.adapter = adpater
        binding.cartRecyclerView.layoutManager = layoutManager

    }

}