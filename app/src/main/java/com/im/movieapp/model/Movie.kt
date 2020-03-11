package com.im.movieapp.model

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("id") var id: Int,
    @SerializedName("title") var title: String,
    @SerializedName("poster_path") var poster_path: String,
    @SerializedName("overview") var overview: String,
    @SerializedName("fav") var fav: Boolean
){
    var imgSrc: String = "https://image.tmdb.org/t/p/w185_and_h278_bestv2/";

}