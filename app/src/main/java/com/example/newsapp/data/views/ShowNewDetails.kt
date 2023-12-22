package com.example.newsapp.data.views

import android.graphics.BitmapFactory
import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.newsapp.databinding.ActivityShowNewDetailsBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.FileNotFoundException
import java.net.MalformedURLException
import java.net.URL

class ShowNewDetails : AppCompatActivity() {
    private var title: String = ""
    private var author: String = ""
    private var content: String = ""
    private var url: String = ""
    private var origin: String = ""
    private lateinit var binding: ActivityShowNewDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityShowNewDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras
        this.title = bundle?.getString("title")!!
        this.author = bundle.getString("author")!!
        this.content = bundle.getString("content")!!
        this.url = if(bundle.getString("url") == null) "" else bundle.getString("url")!!

        setup()
    }

    private fun setup() {
        this.binding.buttonBack.setOnClickListener { finish() }
        this.binding.title.text = this.title
        this.binding.author.text = this.author
        this.binding.content.text = this.content
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val imageUrl = URL(this@ShowNewDetails.url)
                val bitmap = BitmapFactory.decodeStream(imageUrl.openConnection().getInputStream())
                withContext(Dispatchers.Main) {
                    this@ShowNewDetails.binding.imageNew.setImageBitmap(bitmap)
                }
            }catch (e: FileNotFoundException) {
                Log.e("error", e.message!!)
            } catch (e: MalformedURLException) {
                Log.e("error", e.message!!)
            }
        }
    }
}