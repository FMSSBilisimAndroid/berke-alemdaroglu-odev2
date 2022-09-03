package com.dag.odev2fmss

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.dag.odev2fmss.databinding.ActivityCreateAccountScreenBinding

class CreateAccountScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateAccountScreenBinding
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var createUserName: String
    private lateinit var createPassword: String
    private lateinit var createEmail: String
    private lateinit var editor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateAccountScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)


        sharedPreferences = getSharedPreferences("LOGIN", Context.MODE_PRIVATE)
        binding.createAccountButton.setOnClickListener {
            createUserName = binding.createAccountUsernameEditText.text.toString()
            createPassword = binding.createAccountPasswordEditText.text.toString()
            createEmail = binding.createAccountEmailEditText.text.toString()

            editor = sharedPreferences.edit()
            editor.putBoolean("CHECK", true)
            editor.putString("USERNAME", createUserName)
            editor.putString("PASSWORD", createPassword)
            editor.putString("EMAIL", createEmail)
            editor.apply()

            when {
                createUserName.isEmpty() -> Toast.makeText(this,
                    "Lütfen Kullanıcı Adınızı Girin!",
                    Toast.LENGTH_SHORT).show()
                createPassword.isEmpty() -> Toast.makeText(this,
                    "Lütfen Şifrenizi Girin!",
                    Toast.LENGTH_SHORT).show()
                createEmail.isEmpty() -> Toast.makeText(this,
                    "Lütfen Email Adresinizi Girin!",
                    Toast.LENGTH_SHORT).show()
                else -> {
                    Toast.makeText(this, "Kayıt Başarılı! Hoşgeldiniz : $createEmail", Toast.LENGTH_SHORT).show()

                    startActivity(Intent(this@CreateAccountScreenActivity,
                        LoginScreenActivity::class.java))
                    finish()
                }
            }

        }
    }
}