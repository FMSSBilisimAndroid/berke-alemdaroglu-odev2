package com.dag.odev2fmss

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.KeyEvent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dag.odev2fmss.databinding.ActivityLoginScreenBinding

class LoginScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginScreenBinding
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var loginUserName: String
    private lateinit var loginPassword: String
    private lateinit var editor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        orCreateAccountButtonIntent()
        forgottenPasswordButtonIntent()
        loginButtonIntent()

    }

    private fun orCreateAccountButtonIntent() {
        binding.createNewAccountButton.setOnClickListener {
            startActivity(Intent(this@LoginScreenActivity, CreateAccountScreenActivity::class.java))
        }
    }

    private fun forgottenPasswordButtonIntent() {
        binding.forgottenPassTextButton.setOnClickListener {
            startActivity(Intent(this@LoginScreenActivity, PasswordResetActivity::class.java))
        }
    }

    private fun loginButtonIntent() {
        sharedPreferences = getSharedPreferences("LOGIN", Context.MODE_PRIVATE)
        binding.loginButton.setOnClickListener {

            loginUserName = binding.usernameEditText.text.toString()
            loginPassword = binding.passwordEditText.text.toString()

            editor = sharedPreferences.edit()
            editor.putBoolean("CHECK", true)
            editor.putString("USERNAME", loginUserName)
            editor.putString("PASSWORD", loginPassword)
            editor.apply()

            if (sharedPreferences.getString(loginUserName,loginPassword).isNullOrEmpty()){
                Toast.makeText(this, "Lütfen Gerekli Alanları Doldurun!", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "Giriş Yapıldı!", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this@LoginScreenActivity, HomeActivity::class.java))
                finish()
            }

        }

    }

}