package com.unwiringapps.dilsedimagtak_shayariapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.unwiringapps.dilsedimagtak_shayariapp.databinding.ActivityAllShayariBinding

class AllShayariActivity : AppCompatActivity() {

    lateinit var binding: ActivityAllShayariBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAllShayariBinding.inflate(layoutInflater)

        setContentView(binding.root)

        window.decorView.apply {
            // Hide both the navigation bar and the status bar.
            // SYSTEM_UI_FLAG_FULLSCREEN is only available on Android 4.1 and higher, but as
            // a general rule, you should design your app to hide the status bar whenever you
            // hide the navigation bar.
            systemUiVisibility =
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN
        }

        val name = intent.getStringExtra("name")
        val id = intent.getStringExtra("id")


        binding.catText1.text = name.toString()





    }

    override fun onStart() {
        super.onStart()
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY


    }
}