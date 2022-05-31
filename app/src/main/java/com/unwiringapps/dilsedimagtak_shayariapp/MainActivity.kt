package com.unwiringapps.dilsedimagtak_shayariapp

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.unwiringapps.dilsedimagtak_shayariapp.Model.CatModel
import com.unwiringapps.dilsedimagtak_shayariapp.adapter.CategoryAdapter
import com.unwiringapps.dilsedimagtak_shayariapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.decorView.apply {
            // Hide both the navigation bar and the status bar.
            // SYSTEM_UI_FLAG_FULLSCREEN is only available on Android 4.1 and higher, but as
            // a general rule, you should design your app to hide the status bar whenever you
            // hide the navigation bar.
            systemUiVisibility =
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN
        }


//        val list = arrayListOf<String>("Tanmay","Agarwal","Ritik", "Unwiring Tech", "Unwiring Apps", "PucoReads")





        db = FirebaseFirestore.getInstance()

        db.collection("tanmayshayari").addSnapshotListener { value, error ->

            val listrr  = arrayListOf<CatModel>()
            val datarr = value?.toObjects(CatModel::class.java)
            listrr.addAll(datarr!!) 


            binding.rcvCategory.layoutManager = LinearLayoutManager(this)
            binding.rcvCategory.adapter = CategoryAdapter(this, listrr)
        }

        binding.btnMenu.setOnClickListener {
            if (binding.drawerLayout1.isDrawerOpen(Gravity.LEFT)) {
                binding.drawerLayout1.closeDrawer(Gravity.LEFT)

            } else {
                binding.drawerLayout1.openDrawer(Gravity.LEFT)
            }
        }

        binding.navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.share -> {

                    try {
                        val shareIntent = Intent(Intent.ACTION_SEND)
                        shareIntent.type = "text/plain"
                        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name")
                        var shareMessage = "\nThis is an awesome Shayari App, have a look\n\n"
                        shareMessage =
                            """
                           ${shareMessage}https://play.google.com/store/apps/details?id=${BuildConfig.APPLICATION_ID}
                           
                           
                           """.trimIndent()
                        shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
                        startActivity(Intent.createChooser(shareIntent, "choose one"))
                    } catch (e: Exception) {
                        //e.toString();
                    }

                    true
                }

                R.id.rate -> {

                    val uri = Uri.parse("market://details?id=$packageName")
                    val myAppLinkToMarket = Intent(Intent.ACTION_VIEW, uri)
                    try {
                        startActivity(myAppLinkToMarket)
                    } catch (e: ActivityNotFoundException) {
                        Toast.makeText(this, "Impossible to find an application for the market", Toast.LENGTH_LONG).show()
                    }

                    true
                }

                R.id.more -> {

                    try {
                        startActivity(
                            Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("market://details?id=$packageName")
                            )
                        )
                    } catch (e: ActivityNotFoundException) {
                        startActivity(
                            Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("https://play.google.com/store/apps/details?id=$packageName")
                            )
                        )
                    }

                    true
                } else -> false
            }
        }

    }

    override fun onBackPressed() {


        if (binding.drawerLayout1.isDrawerOpen(Gravity.LEFT)) {
            binding.drawerLayout1.closeDrawer(Gravity.LEFT)

        } else {
            super.onBackPressed()
        }

    }

    override fun onStart() {
        super.onStart()
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY


    }


}