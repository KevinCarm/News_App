package com.example.newsapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.data.Article
import com.example.newsapp.data.views.NewsRecyclerAdapter
import com.example.newsapp.data.views.ShowNewDetails
import com.example.newsapp.databinding.ActivityMainBinding
import com.example.newsapp.viewmodel.NewsViewModel

class MainActivity : AppCompatActivity(), TextView.OnEditorActionListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: NewsViewModel
    private lateinit var newsAdapter: NewsRecyclerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(this.binding.root)

        viewModel = ViewModelProvider(this)[NewsViewModel::class.java]
        setup()
    }

    private fun setup() {
        binding.searchInput.setOnEditorActionListener(this)
        newsAdapter = NewsRecyclerAdapter(emptyList(), ::onClickCardEvent)
        val manager: RecyclerView.LayoutManager = LinearLayoutManager(applicationContext)
        binding.newList.layoutManager = manager
        binding.newList.adapter = newsAdapter
        viewModel.articles.observe(this) {
            if (it.articles.isNotEmpty()) {
                binding.progressIndicator.visibility = View.INVISIBLE
                newsAdapter.setList(it.articles)
            } else {
                binding.progressIndicator.visibility = View.INVISIBLE
            }
        }
    }

    override fun onEditorAction(textView: TextView?, action: Int, keyEvent: KeyEvent?): Boolean {
        if (action == EditorInfo.IME_ACTION_SEARCH) {
            binding.progressIndicator.visibility = View.VISIBLE
            with(binding.searchInput) {
                if (text.toString().isNotEmpty()) {
                    viewModel.getArticles(text.toString(), 50, getString(R.string.apiKey))
                    hideKeyBoard()
                    return true
                }
            }
        }
        return false
    }

    private fun hideKeyBoard() {
        val keyBoard = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        keyBoard.hideSoftInputFromWindow(binding.searchInput.windowToken, 0)
    }

    private fun onClickCardEvent(article: Article) {
        val intent = Intent(this, ShowNewDetails::class.java)
            .also {
                it.putExtra("title", article.title)
                it.putExtra("author", "Author: ${article.author}")
                it.putExtra("content", article.content.substring(0, 200))
                it.putExtra("url", article.urlToImage)
                it.putExtra("origin", article.url)
            }
        startActivity(intent)
    }
}