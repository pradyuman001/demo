package com.example.admin.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.admin.databinding.FragmentCategoryBinding
import com.example.admin.fragments.fragmentAdapter.userCategoryAdpater
import com.example.admin.utils.DBCategory
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class userCategoryFragment : Fragment() {

    lateinit var binding: FragmentCategoryBinding
    var categoryList = arrayListOf<DBCategory>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCategoryBinding.inflate(layoutInflater)

        readProductData()

        return binding.root
    }


    private fun readProductData() {

        var firebaseDatabase = FirebaseDatabase.getInstance()
        var databaseReference = firebaseDatabase.reference

        databaseReference.child("Category").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                categoryList.clear()

                for (x in snapshot.children) {


                    var id = x.child("id").getValue().toString()
                    var cat = x.child("cat").getValue().toString()


                    var productData = DBCategory(id, cat)



                    categoryList.add(productData)
                }

                setupCategory(categoryList)
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })

    }

    private fun setupCategory(categoryList: ArrayList<DBCategory>) {

        var adapter = userCategoryAdpater(activity, categoryList)
        var layoutManager = LinearLayoutManager(activity)
        binding.categoryFragmentRecyclerView.adapter = adapter
        binding.categoryFragmentRecyclerView.layoutManager = layoutManager

    }
}