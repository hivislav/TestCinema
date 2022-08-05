package ru.hivislav.testcinema.model.repository

import retrofit2.Callback
import ru.hivislav.testcinema.model.entities.MovieListDTO

interface Repository {
    fun getMovies(callback: Callback<MovieListDTO>)
}