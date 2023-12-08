package com.yanyan.movieshowclone.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yanyan.movieshowclone.data.remote.MovieRepository
import com.yanyan.movieshowclone.model.MovieResponse
import com.yanyan.movieshowclone.util.NetworkHelper

class MainViewModel(private val networkHelper: NetworkHelper,private val movieRepository: MovieRepository):ViewModel() {
    companion object{
        private const val API_KEY = "487035a582a1e82376b42010973387a6"
    }

    private val _movieResponse = MutableLiveData<MovieResponse>()
    val movieResponse : LiveData<MovieResponse> get() =_movieResponse

    private  val _errorMessage = MutableLiveData<String>()
    val errorMessage : LiveData<String> get()=_errorMessage

     fun onCreate() {
        if(networkHelper.isNetworkConnect()){
            movieRepository.getMovies(API_KEY,{movieResponse->
                _movieResponse.postValue(movieResponse)
            },{error->
                _errorMessage.postValue(error)
            })
        }
    }

}