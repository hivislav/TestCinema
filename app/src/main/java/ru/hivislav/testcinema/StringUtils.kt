package ru.hivislav.testcinema

import ru.hivislav.testcinema.model.entities.MovieDTO

fun formattingDirectorsName(fullName: String): String {
    val directorsName = fullName.split(" ").toMutableList()
    directorsName[0] = directorsName[0].replaceAfter(directorsName[0].toCharArray()[0], ".")
    directorsName[1] = directorsName[1].replaceAfter(directorsName[1].toCharArray()[0], ".")
    return directorsName[2] + " " + directorsName[0] + directorsName[1]
}

fun getActorsName(movie: MovieDTO): String {
    val actorNames = StringBuilder()
    for (i in movie.actors.indices) {
        actorNames.append(movie.actors.elementAt(i).actorName)
        if (i != movie.actors.size - 1) {
            actorNames.append(", ")
        }
    }
    return actorNames.toString()
}