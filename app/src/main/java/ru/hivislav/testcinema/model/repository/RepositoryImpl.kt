package ru.hivislav.testcinema.model.repository

import com.google.gson.GsonBuilder
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.hivislav.testcinema.model.FILM_API_BASE_URL
import ru.hivislav.testcinema.model.FilmApi
import ru.hivislav.testcinema.model.entities.MovieListDTO

class RepositoryImpl: Repository {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(FILM_API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .build()
            .create(FilmApi::class.java)
    }

    override fun getMovies(callback: Callback<MovieListDTO>) {
        retrofit.getMoviesFromServer().enqueue(callback)
    }
}