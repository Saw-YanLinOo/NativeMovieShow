package com.yanyan.movieshowclone.ui.adapter

import com.yanyan.movieshowclone.model.Movie
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yanyan.movieshowclone.databinding.MovieItemViewBinding

class MoviesAdapter(private val movies:List<Movie>) :RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {

      val  binding = MovieItemViewBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return  MoviesViewHolder(binding)
    }

    override fun getItemCount(): Int = movies.count()


    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    class MoviesViewHolder(private val itemBinding:MovieItemViewBinding):RecyclerView.ViewHolder(itemBinding.root){

        companion object{
            private const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"
        }

        fun bind(movie: Movie){
            itemBinding.movieTitle.text = movie.title
            itemBinding.releaseDate.text = movie.releaseDate
            itemBinding.avgVoting.text = "${movie.voteAverage}"
            itemBinding.totalVote.text = "${movie.voteCount}"
            Glide.with(itemView.context).load( IMAGE_BASE_URL + movie.posterPath).into(itemBinding.moviePoster)

        }
    }


}