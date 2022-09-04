package com.dag.odev2fmss

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dag.odev2fmss.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("LOGIN", Context.MODE_PRIVATE)

        binding.apply {
            usernameHomeScreenText.text = sharedPreferences.getString("USERNAME", "Default")
            paswordHomeScreenText.text = sharedPreferences.getString("PASSWORD", "DEFAULT PASS")
        }

        logOutButtonPreferencesDelete()
    }

    /**
     * logOutButton ile sharedPreference ile tutulan değerler .clear() ile siliniyor ve kullanıcı tekrar loginScreenActivity ekranına yönlendiriliyor.
     */
    private fun logOutButtonPreferencesDelete() {
        binding.logOutButton.setOnClickListener {
            sharedPreferences.edit().putBoolean("CHECK", false).apply()
            Util.backStack(this, LoginScreenActivity())
        }
    }

}