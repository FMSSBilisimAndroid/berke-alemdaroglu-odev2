package com.dag.odev2fmss

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dag.odev2fmss.databinding.ActivityGetStartedScreenBinding

class GetStartedScreenActivity : AppCompatActivity() {
    private lateinit var binding : ActivityGetStartedScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGetStartedScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.joinNowButton.setOnClickListener {
            val intent = Intent(this, LoginScreenActivity::class.java)
            startActivity(intent)
        }
    }
}