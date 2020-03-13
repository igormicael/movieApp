package com.im.movieapp.lists

import com.im.movieapp.model.Movie

interface OnListListener {
    fun onClick(position: Int)
}