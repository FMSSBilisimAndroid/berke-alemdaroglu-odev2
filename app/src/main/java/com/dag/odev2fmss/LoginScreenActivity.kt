package com.dag.odev2fmss

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import com.dag.odev2fmss.databinding.ActivityLoginScreenBinding

class LoginScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginScreenBinding
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var loginUserName: String
    private lateinit var loginPassword: String
    private var sharedUsernameValue: String = ""
    private var sharedPasswordValue: String = ""
    private lateinit var userDataClass: UserDataClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPreferences = getSharedPreferences("LOGIN", Context.MODE_PRIVATE)
        sharedUsernameValue = sharedPreferences.getString("USERNAME", sharedUsernameValue).toString()
        sharedPasswordValue = sharedPreferences.getString("PASSWORD", sharedPasswordValue).toString()

        orCreateAccountButtonIntent()
        forgottenPasswordButtonIntent()
        loginButtonIntent()
    }

    private fun loginButtonIntent() {
        userDataClass = UserDataClass("demo@gmail.com", "demo", "demoPass")
        binding.loginButton.setOnClickListener {

            loginUserName = binding.usernameEditText.text.toString()
            loginPassword = binding.passwordEditText.text.toString()

            if (userDataClass.username == loginUserName && userDataClass.password == loginPassword) {
                sharedPreferences.edit {
                    putString("USERNAME", userDataClass.username)
                    putString("PASSWORD", userDataClass.password)
                }
                Toast.makeText(this, "Giriş Yapıldı!", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this@LoginScreenActivity, HomeActivity::class.java))
                finish()
            } else if (loginUserName.isEmpty() && loginPassword.isEmpty()) {
                Toast.makeText(this, "Gerekli Alanları Doldurun!", Toast.LENGTH_SHORT).show()
            } else if (sharedUsernameValue == loginPassword && sharedPasswordValue == loginPassword) {
                Toast.makeText(this, "Kayıt Sonrası Giriş Yapıldı!", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this@LoginScreenActivity, HomeActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Kullanıcı Bulunamadı!", Toast.LENGTH_SHORT).show()
            }
        }
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


}