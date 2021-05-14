package com.example.userinterface

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.userinterface.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var binding: ActivityMainBinding

    var eMailField: EditText? = null
    var isAllFieldsChecked = false
    var validEmail = "NO"



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        eMailField = binding.etEmail

        binding.buttonSave.setOnClickListener(this)
       // binding.buttonClear.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id) {
                R.id.buttonSave -> {
                    isAllFieldsChecked = confirmation()
                    return
                }
            }
        }
    }



    private fun confirmation(): Boolean {
        validateEmailAddress(eMailField)
        if (validEmail != "YES") {
            return false

        }


        if (binding.etFirstName.length() == 0) {
            binding.etFirstName.error = "This field is required"

        }

        if (binding.etLastName.length() == 0) {
            binding.etLastName.error = "This field is required"

        }

        if (binding.etAge.length() == 0) {
            binding.etAge.error = "This field is required"

        } else {
            Toast.makeText(this,
                "You have successfully saved the data",
                Toast.LENGTH_SHORT).show()

        }

        transferData()


        return true

    }



    private fun validateEmailAddress(eMailField: EditText?) {

        val emailToText = eMailField!!.text.toString()


        if (emailToText.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailToText).matches()) {
            validEmail = "YES"
            binding.etEmail.error = null
            //Toast.makeText(this, "Email Verified !", Toast.LENGTH_SHORT).show()
        } else {
            validEmail = "NO"
            binding.etEmail.error = "Enter valid Email address"
            //Toast.makeText(this, "Email is not valid!", Toast.LENGTH_SHORT).show()

        }
    }

    private fun transferData(){

        val intent = Intent(this,MainActivity2::class.java)
        intent.putExtra(Constants.EMAIL,binding.etEmail.text.toString())
        intent.putExtra(Constants.FIRST_NAME,binding.etFirstName.text.toString())
        intent.putExtra(Constants.LAST_NAME,binding.etLastName.text.toString())
        intent.putExtra(Constants.AGE,binding.etAge.text.toString())

        startActivity(intent)
        finish()

    }


}