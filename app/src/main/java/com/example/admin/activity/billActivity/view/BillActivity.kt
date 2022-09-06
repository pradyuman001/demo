package com.example.admin.activity.billActivity.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.admin.R
import com.example.admin.databinding.ActivityBillBinding

class billActivity : AppCompatActivity() {

    lateinit var binding: ActivityBillBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBillBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}