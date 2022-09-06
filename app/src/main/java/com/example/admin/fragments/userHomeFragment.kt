package com.example.admin.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.admin.R
import com.example.admin.databinding.FragmentUserHomeBinding
import com.example.admin.fragments.fragmentAdapter.userDataAdapter
import com.example.admin.utils.DBReadProduct
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem

class userHomeFragment : Fragment() {

    lateinit var binding : FragmentUserHomeBinding
    val productList = arrayListOf<DBReadProduct>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserHomeBinding.inflate(layoutInflater)


        readProductData()

        return binding.root
    }

    private fun readProductData() {

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

                    var productData = DBReadProduct(id, pname, pprice, pdes, pcat, pimage, key, cid,pdis)

                    productList.add(productData)
                }

                setProductData(productList)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

    }

    fun setProductData(productList: ArrayList<DBReadProduct>) {

        var adpater = userDataAdapter(activity, productList)
        var layoutManager = LinearLayoutManager(activity)
        binding.recyclerView.adapter = adpater
        binding.recyclerView.layoutManager = layoutManager

    }




}