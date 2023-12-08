package com.yanyan.movieshowclone.ui.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yanyan.movieshowclone.data.remote.MovieRepository
import com.yanyan.movieshowclone.ui.MainViewModel
import com.yanyan.movieshowclone.util.NetworkHelper

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(
    private val networkHelper: NetworkHelper,
    private val movieRepository: MovieRepository):ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        return MainViewModel(networkHelper,movieRepository) as T
    }

}