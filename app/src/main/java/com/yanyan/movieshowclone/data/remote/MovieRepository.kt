package com.yanyan.movieshowclone.data.remote

import com.yanyan.movieshowclone.model.MovieResponse
import java.lang.Error

interface MovieRepository {
    fun getMovies(apiKey :String, onSuccess:(MovieResponse)->Unit,onError: (String)->Unit)
}