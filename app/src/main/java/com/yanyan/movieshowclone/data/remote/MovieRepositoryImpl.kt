package com.yanyan.movieshowclone.data.remote

import com.yanyan.movieshowclone.model.MovieResponse
import com.yanyan.movieshowclone.retrofit.MovieService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepositoryImpl(private val movieService: MovieService) :MovieRepository {
    override fun getMovies(
        apiKey: String,
        onSuccess: (MovieResponse) -> Unit,
        onError: (String) -> Unit
    ) {
        val call  =  movieService.getMovies(apiKey)

        call.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(
                call: Call<MovieResponse>,
                response: Response<MovieResponse>
            ) {

                if(response.isSuccessful && response.body() !=null){
                    val movieResponse = response.body()!!
                    onSuccess(movieResponse)
                } else{
                    onError(response.message())
                }

            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                onError(t.localizedMessage?:"Opps: Something went wrong :(")
            }

        })
    }
}