package com.yanyan.movieshowclone.ui

import com.yanyan.movieshowclone.model.Movie
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.RecyclerView
import com.yanyan.movieshowclone.R
import com.yanyan.movieshowclone.data.remote.MovieRepositoryImpl
import com.yanyan.movieshowclone.databinding.ActivityMainBinding
import com.yanyan.movieshowclone.retrofit.RetrofitBuilder
import com.yanyan.movieshowclone.ui.adapter.MoviesAdapter
import com.yanyan.movieshowclone.ui.util.MainViewModelFactory
import com.yanyan.movieshowclone.util.NetworkHelper

class MainActivity : AppCompatActivity() {


    private lateinit var _binding : ActivityMainBinding
    private  val binding get()= _binding

    private var _viewModel: MainViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding=ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        setUpViewModel()
        observeViewModel()
    }

    private fun observeViewModel(){
        _viewModel?.movieResponse?.observe(this) { movieResponse ->
            showMovie(movieResponse.results)
            hideProgress()
        }

        _viewModel?.errorMessage?.observe(this) { error ->
            showErrorMessage(error)
            hideProgress()
        }

    }

    private fun setUpViewModel(){
        showProgress()
        _viewModel = ViewModelProvider(
            this, MainViewModelFactory(
                NetworkHelper(this),
                MovieRepositoryImpl(
                    RetrofitBuilder.buildService()
                )
            )
        )[MainViewModel::class.java]
        _viewModel?.onCreate()
    }

    private fun showMovie(movieList: List<Movie>) {
        val recyclerView : RecyclerView = _binding.recyclerView
        val progressBar : ProgressBar = _binding.progressBar

        progressBar.visibility = View.GONE

        recyclerView.visibility = View.VISIBLE
        recyclerView.setHasFixedSize(true)
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = MoviesAdapter(movieList)

    }


    private  fun showProgress(){
        _binding.progressBar.visibility = View.VISIBLE

    }
    private  fun hideProgress(){
        _binding.progressBar.visibility = View.GONE

    }

    private  fun showErrorMessage(errorMessage:String){
        _binding.errorView.visibility = View.VISIBLE
        _binding.errorView.text = errorMessage

    }

}