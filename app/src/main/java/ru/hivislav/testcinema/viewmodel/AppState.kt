package ru.hivislav.testcinema.viewmodel

import ru.hivislav.testcinema.model.entities.MovieListDTO

sealed class AppState {
    data class Success(val moviesListResponse: MovieListDTO): AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}