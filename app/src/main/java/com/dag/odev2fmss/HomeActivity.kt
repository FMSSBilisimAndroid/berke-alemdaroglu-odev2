package com.dag.odev2fmss

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dag.odev2fmss.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("LOGIN", Context.MODE_PRIVATE)

        binding.usernameHomeScreenText.text =
            sharedPreferences.getString("USERNAME", "Username : " +  "DEFAULT").toString()
        binding.paswordHomeScreenText.text =
            sharedPreferences.getString("PASSWORD", "Password : " + "DEFAULT PASS").toString()

        logOutButtonPreferencesDelete()

    }

    /**
     * logOutButton ile sharedPreference ile tutulan değerler .clear() ile siliniyor ve kullanıcı tekrar loginScreenActivity ekranına yönlendiriliyor.
     */
    private fun logOutButtonPreferencesDelete() {
        binding.logOutButton.setOnClickListener {
            sharedPreferences.edit().clear().apply()
            startActivity(Intent(this@HomeActivity, LoginScreenActivity::class.java))
        }
    }
}