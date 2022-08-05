package ru.hivislav.testcinema.model

import retrofit2.Call
import retrofit2.http.GET
import ru.hivislav.testcinema.model.entities.MovieListDTO

interface FilmApi {
    @GET(FILMS_JSON_END_POINT)
    fun getMoviesFromServer(): Call<MovieListDTO>
}