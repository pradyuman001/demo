package com.example.admin.activity.searchActivity.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.admin.R
import com.example.admin.databinding.ActivitySearchBinding
import com.example.admin.utils.DBReadProduct
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class searchActivity : AppCompatActivity() {

    lateinit var binding : ActivitySearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun readProductData() {

        val productList = arrayListOf<DBReadProduct>()

        var firebaseDatabase = FirebaseDatabase.getInstance()
        var databaseReference = firebaseDatabase.reference

        databaseReference.child("Product").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                productList.clear()

                for (x in snapshot.children) {

                    var id = x.child("pid").getValue().toString()
                    var pname = x.child("pname").getValue().toString()
                    var pprice = x.child("pprice").getValue().toString()
                    var pdes = x.child("pdes").getValue().toString()
                    var pcat = x.child("pcat").getValue().toString()
                    var pimage = x.child("pimage").getValue().toString()
                    var cid = x.child("cid").getValue().toString()
                    var key = x.key.toString()
                    var pdis = x.child("pdis").getValue().toString()

                    var productData = DBReadProduct(id, pname, pprice, pdes, pcat, pimage, key, cid, pdis)

                    productList.add(productData)
                }
            }
            override fun onCancelled(error: DatabaseError) {
            }
        })
    }
}