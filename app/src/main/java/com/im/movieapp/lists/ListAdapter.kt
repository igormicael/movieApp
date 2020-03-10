package com.im.movieapp.lists

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.im.movieapp.R
import com.im.movieapp.model.Movie
import com.squareup.picasso.Picasso

class ListAdapter(
    var data: List<Movie>,
    onListListener: OnListListener
) :
    RecyclerView.Adapter<ViewHolder>() {

    var escolhido = false

    var onListListener: OnListListener = onListListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.movie_list, parent, false)

        return ViewHolder(view, onListListener)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = data.get(position)

        holder.movieName.text = currentItem.name

        Picasso.get()
            .load(currentItem.imgSrc)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.mipmap.ic_launcher_round)
            .into(holder.imgSrc)

        holder.details.text = currentItem.details

        holder.movieName.setOnClickListener {
            if (!escolhido) {
                escolhido = true
                holder.onClick(it)
            }
        }
    }
}