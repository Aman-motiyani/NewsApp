package com.example.kaltak

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestListener
import kotlinx.android.synthetic.main.item_news.view.*

class NewsAdapter(private val listener: NewsItemClicked):RecyclerView.Adapter<NewsAdapter.NewsViewHolder>()
{
    private val items = ArrayList<News>()
    inner class NewsViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)
    {
        val titleView:TextView = itemView.findViewById<TextView>(R.id.title)
        val imageView:ImageView = itemView.findViewById<ImageView>(R.id.image)
        val author:TextView = itemView.findViewById<TextView>(R.id.author)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        val viewHolder = NewsViewHolder(view)
        view.setOnClickListener {
            listener.onItemClicked(items[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentItem = items[position]
        holder.titleView.text = currentItem.title
        holder.author.text = currentItem.author
        Glide.with(holder.itemView.context).load(currentItem.imageurl).into(holder.imageView)
    }

    fun updatenews(updatedNews: ArrayList<News>)
    {
        items.clear()
        items.addAll(updatedNews)

        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
       return items.size
    }

}

interface NewsItemClicked{
    fun onItemClicked(item:News)
}
