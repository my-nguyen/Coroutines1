package com.nguyen.coroutines1

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.nguyen.coroutines1.databinding.ActivityMainBinding
import java.net.URL
import kotlin.concurrent.thread

private const val IMAGE_URL = "https://rkpandey.com/images/rkpDavidson.jpg"
private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // create a thread to do the image download, then send bitmap to the main thread
        val handler = Handler(Looper.getMainLooper())
        thread(start=true) {
            Log.i(TAG, "Current thread ${Thread.currentThread().name}")
            val bitmap = downloadBitmap(IMAGE_URL)
            handler.post {
                Log.i(TAG, "Current thread in Handler: ${Thread.currentThread().name}")
                binding.image.setImageBitmap(bitmap)
            }
        }
    }

    private fun downloadBitmap(url: String): Bitmap? {
        return try {
            val connection = URL(url).openConnection()
            connection.connect()
            val inputStream = connection.getInputStream()
            val bitmap = BitmapFactory.decodeStream(inputStream)
            inputStream.close()
            bitmap
        } catch (e: Exception) {
            Log.e(TAG, "Exception $e")
            null
        }
    }
}