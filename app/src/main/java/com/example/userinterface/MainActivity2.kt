package com.example.userinterface

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.userinterface.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMain2Binding

    private var userDetails: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonUpdate.setOnClickListener(this)
        binding.buttonClear.setOnClickListener(this)

        val email =  intent.getStringExtra(Constants.EMAIL)
        binding.tvEmail.text = email

        userInputs()

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
        Toast.makeText(this, "User Removed", Toast.LENGTH_SHORT).show()
        binding.cvUserInfo.visibility = View.GONE
        binding.tvInfo.visibility = View.VISIBLE

    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.buttonUpdate -> {
                update()

            }
            R.id.buttonClear -> {
                clearData()
            }
        }
    }

    private fun update(){
        val intent = Intent(this,MainActivity::class.java)
        intent.putExtra(Constants.UPDATE_USER_INFO,binding.tvEmail.text.toString())
        intent.putExtra(Constants.FIRST_NAME,binding.tvFirstNAme.text.toString())
        intent.putExtra(Constants.LAST_NAME,binding.tvLastName.text.toString())
        intent.putExtra(Constants.AGE,binding.tvAge.text.toString())

        binding.tvInfo.visibility = View.GONE

        startActivity(intent)

    }
}

