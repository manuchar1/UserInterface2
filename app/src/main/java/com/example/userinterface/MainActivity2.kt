package com.example.userinterface

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.userinterface.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val email =  intent.getStringExtra(Constants.EMAIL)
        binding.tvEmail.text = email

        userInputs()

        binding.buttonClear.setOnClickListener {
            clearData()

        }


    }

    private fun userInputs(){

        val email =  intent.getStringExtra(Constants.EMAIL)
        val firstNAme =  intent.getStringExtra(Constants.FIRST_NAME)
        val lastName =  intent.getStringExtra(Constants.LAST_NAME)
        val age =  intent.getStringExtra(Constants.AGE)

        binding.tvEmail.text = email
        binding.tvFirstNAme.text = firstNAme
        binding.tvLastName.text = lastName
        binding.tvAge.text = age

    }

    private fun clearData() {
        binding.tvEmail.text = null
        binding.tvFirstNAme.text = null
        binding.tvLastName.text = null
        binding.tvAge.text = null

    }
}