package com.example.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.newsapp.databinding.ActivityMainBinding
import com.example.newsapp.viewmodel.NewsViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: NewsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(this.binding.root)

        viewModel = ViewModelProvider(this)[NewsViewModel::class.java]
        //viewModel.getArticles("Apple", 20, getString(R.string.apiKey))
    }
}