package com.dag.odev2fmss

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.dag.odev2fmss.databinding.ActivityPasswordResetBinding

class PasswordResetActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPasswordResetBinding
    private lateinit var emailText : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPasswordResetBinding.inflate(layoutInflater)
        setContentView(binding.root)

        resetPasswordButtonClicked()
    }

    private fun resetPasswordButtonClicked(){
        binding.renewPasswordButton.setOnClickListener {
            emailText = binding.usernameEditText.text.toString()
            Toast.makeText(this, "$emailText : Mail Adresine Şifre Gönderilmiştir!", Toast.LENGTH_SHORT).show()
        }
    }
}