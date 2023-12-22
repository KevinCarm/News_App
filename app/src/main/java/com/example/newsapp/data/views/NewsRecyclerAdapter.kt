package com.example.newsapp.data.views

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.data.Article

class NewsRecyclerAdapter(
    private var list: List<Article>,
    val onClickCardEvent: (Article) -> Unit
) : RecyclerView.Adapter<NewsRecyclerAdapter.ViewHolder>() {


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView
        val author: TextView
        val content: TextView

        init {
            title = view.findViewById(R.id.title)
            author = view.findViewById(R.id.author)
            content = view.findViewById(R.id.content)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.new_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(list[position]) {
            holder.title.text = title
            holder.author.text = author
            holder.content.text = description
        }
        holder.itemView.setOnClickListener {
           onClickCardEvent(list[position])
        }
    }

    fun setList(newList: List<Article>) {
        this.list = newList
        notifyDataSetChanged()
    }
}