package com.im.movieapp.lists

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.im.movieapp.R

class ViewHolder(
    view: View,
    onListListener: OnListListener
) :
    RecyclerView.ViewHolder(view), View.OnClickListener {

    override fun onClick(v: View?) {
        onListListener.onClick(adapterPosition);
    }

    var onListListener: OnListListener = onListListener

    var movieName: TextView = view.findViewById(R.id.movie_name)
    var imgSrc: ImageView = view.findViewById(R.id.imgSrc)

    init {
        view.setOnClickListener(this)
    }
}