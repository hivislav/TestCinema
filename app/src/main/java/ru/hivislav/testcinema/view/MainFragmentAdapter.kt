package ru.hivislav.testcinema.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.hivislav.testcinema.R
import ru.hivislav.testcinema.databinding.ViewHolderMovieBinding
import ru.hivislav.testcinema.formattingDirectorsName
import ru.hivislav.testcinema.getActorsName
import ru.hivislav.testcinema.model.entities.MovieDTO

class MainFragmentAdapter: RecyclerView.Adapter<MainFragmentAdapter.MovieViewHolder>() {

    private var moviesData: List<MovieDTO> = listOf()

    fun setMoviesData(data: List<MovieDTO>) {
        moviesData = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding =
            ViewHolderMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(moviesData[position])
    }

    override fun getItemCount(): Int {
        return moviesData.size
    }

    inner class MovieViewHolder(private val binding: ViewHolderMovieBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieDTO) {
                itemView.apply {
                    with(binding) {
                        movieHolderTitle.text = movie.title
                        movieHolderYear.text = resources.getString(R.string.year_placeholder, movie.releaseYear.toString())
                        movieHolderDirector.text = formattingDirectorsName(movie.directorName)
                        movieHolderActors.text = getActorsName(movie)
                    }
                }
            }
        }
}