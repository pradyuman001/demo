package com.example.admin.activity.signingActivity.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.admin.R
import com.example.admin.databinding.ActivitySigningBinding

class signingActivity : AppCompatActivity() {

    lateinit var binding: ActivitySigningBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySigningBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}