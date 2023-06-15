package com.nguyen.coroutines1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.nguyen.coroutines1.databinding.ActivityMainBinding

private const val IMAGE_URL = "https://rkpandey.com/images/rkpDavidson.jpg"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Glide.with(this).load(IMAGE_URL).into(binding.image)
    }
}