package com.dag.odev2fmss

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dag.odev2fmss.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding : ActivityHomeBinding
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var sharedNameValue : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("LOGIN", Context.MODE_PRIVATE)
        logOutButtonPreferencesDelete()
        sharedNameValue = sharedPreferences.getString("USERNAME", "DEFAULT").toString()
        binding.usernameHomeScreenText.text = sharedNameValue

    }

    private fun logOutButtonPreferencesDelete(){
        binding.logOutButton.setOnClickListener {
            sharedPreferences.edit().clear().apply()
          startActivity(Intent(this@HomeActivity, LoginScreenActivity::class.java))
        }
    }
}