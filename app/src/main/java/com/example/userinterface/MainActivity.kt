package com.example.userinterface

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //intentInfo()
        userInputs()
        eMailField = binding.etEmail

        binding.buttonSave.setOnClickListener(this)
        binding.buttonBack.setOnClickListener(this)
        // binding.buttonClear.setOnClickListener(this)

    }



    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id) {
                R.id.buttonSave -> {
                    isAllFieldsChecked = confirmation()

                    return
                }
                R.id.buttonBack -> {
                    onBackPressed()
                }
            }
        }
    }

    private fun confirmation(): Boolean {
        validateEmailAddress(eMailField)

        binding.apply {

            val email = etEmail.text.toString().trim { it <= ' ' }
            val firsName = etFirstName.text.toString().trim { it <= ' ' }
            val lastName = etLastName.text.toString().trim { it <= ' ' }
            val age = etAge.text.toString().trim { it <= ' ' }

            when {
                TextUtils.isEmpty(email) || !validEmail -> {
                    etEmail.error = getString(R.string.err_msg_invalid_email)
                }

                TextUtils.isEmpty(firsName) -> {
                    etFirstName.error = getString(R.string.err_msg_required_fields)
                }

                TextUtils.isEmpty(lastName) -> {
                    etLastName.error = getString(R.string.err_msg_required_fields)
                }

                TextUtils.isEmpty(age) -> {
                    etAge.error = getString(R.string.err_msg_required_fields)
                }
                else -> {
                    transferData()
                }


            }

        }
        return true
    }


    private fun validateEmailAddress(eMailField: EditText?) {
        val emailToText = eMailField!!.text.toString()


        if (emailToText.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailToText).matches()) {
            validEmail = true
            binding.etEmail.error = null
            //Toast.makeText(this, "Email Verified !", Toast.LENGTH_SHORT).show()
        } else {
            validEmail = false
            binding.etEmail.error = getString(R.string.err_msg_invalid_email)
            //Toast.makeText(this, "Email is not valid!", Toast.LENGTH_SHORT).show()

        }
    }

    private fun transferData() {
        Toast.makeText(this@MainActivity,
            getString(R.string.msg_success), Toast.LENGTH_SHORT).show()

        val intent = Intent(this, MainActivity2::class.java)
        binding.apply {
            intent.putExtra(Constants.EMAIL, etEmail.text.toString())
            intent.putExtra(Constants.FIRST_NAME, etFirstName.text.toString())
            intent.putExtra(Constants.LAST_NAME, etLastName.text.toString())
            intent.putExtra(Constants.AGE, etAge.text.toString())

            startActivity(intent)
            //finish()

        }


    }

    private fun userInputs(){
        val email = intent.getStringExtra(Constants.UPDATE_USER_INFO)
        val firstNAme =  intent.getStringExtra(Constants.FIRST_NAME)
        val lastName =  intent.getStringExtra(Constants.LAST_NAME)
        val age =  intent.getStringExtra(Constants.AGE)


        binding.etEmail.setText(email)
        binding.etFirstName.setText(firstNAme)
        binding.etLastName.setText(lastName)
        binding.etAge.setText(age)

        if (intent.hasExtra(Constants.UPDATE_USER_INFO)) {
            binding.mainTitle.text = "Update User"
            binding.buttonSave.text = "Update "

            binding.buttonBack.visibility = View.VISIBLE

        }

    }


    companion object {
        var validEmail = false
    }

}