package com.example.admin.activity.cartActivity.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.admin.activity.cartActivity.controller.cartAdapter
import com.example.admin.databinding.ActivityCartBinding
import com.example.admin.utils.DBCartProduct
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.example.admin.R


class cartActivity : AppCompatActivity() {

    lateinit var binding: ActivityCartBinding
    val cartList = arrayListOf<DBCartProduct>()
    var total : Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        readProductData()

        buyProduct()
    }

    private fun buyProduct() {
        binding.buyButton.setOnClickListener {


            bottomNavigation()

//            var intent = Intent(this,billActivity::class.java)
//            startActivity(intent)

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
                total=0;

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


                    total = (pprice!!.toInt()*qua.toInt()) + total

                    cartList.add(productData)
                }


                Toast.makeText(this@cartActivity, "$total", Toast.LENGTH_SHORT).show()


                setProductData(cartList)
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })

    }

    fun setProductData(productList: ArrayList<DBCartProduct>) {

        var adpater = cartAdapter(this, productList)
        var layoutManager = LinearLayoutManager(this)
        binding.cartRecyclerView.adapter = adpater
        binding.cartRecyclerView.layoutManager = layoutManager

    }

    private fun bottomNavigation(){

       var dialog  = BottomSheetDialog(this)
        dialog.setContentView(R.layout.dialog_bottomsheet)

        dialog.show()


    }

}