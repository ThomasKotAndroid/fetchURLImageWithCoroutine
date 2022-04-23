package com.android.example.coroutineusefullness

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import java.net.HttpURLConnection
import java.net.URL
import com.android.example.coroutineusefullness.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

private lateinit var binding: ActivityMainBinding
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view: View
        binding = ActivityMainBinding.inflate(layoutInflater)
        view = binding.root
        setContentView(view)
       // setContentView(R.layout.activity_main)
       GlobalScope.launch(Dispatchers.IO){
           val imageUrl = URL("https://www.nps.gov/yose/planyourvisit/images/20170618_155330.jpg")
           val httpConnection = imageUrl.openConnection() as HttpURLConnection
           httpConnection.doInput = true
           httpConnection.connect()

           val inputStream = httpConnection.inputStream
           val bitmapImage = BitmapFactory.decodeStream(inputStream)
           launch(Dispatchers.Main){
               Log.d("CoroutineExercise", "Name Of thread is  ${Thread.currentThread().name}")
           binding.imageView.setImageBitmap(bitmapImage)

           }
       }



    }
}