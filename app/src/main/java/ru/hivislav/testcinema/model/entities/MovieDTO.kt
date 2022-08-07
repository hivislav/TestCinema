package ru.hivislav.testcinema.model.entities


data class MovieDTO(
    val title: String,
    val directorName: String,
    val releaseYear: Int,
    val actors: Set<ActorDTO>
)
