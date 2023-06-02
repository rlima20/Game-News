package com.example.gamenews.mappers

import com.example.gamenews.model.GameNewsDTO
import com.example.gamenews.model.GameNewsState

fun toMap(listOfGameNewsDTO: List<GameNewsDTO>?): MutableList<GameNewsState>? {

    var listOfGameNewStates: MutableList<GameNewsState>? = mutableListOf()

    if (listOfGameNewsDTO == null) {
        listOfGameNewStates = null
    } else {
        listOfGameNewsDTO.let {
            it.forEach { gameNewsDTO ->
                listOfGameNewStates?.add(
                    GameNewsState(
                        title = gameNewsDTO.title,
                        date = formatDateToDateNews(gameNewsDTO.date),
                        description = gameNewsDTO.description,
                        image = gameNewsDTO.image,
                        link = gameNewsDTO.link
                    )
                )
            }
        }
    }
    return listOfGameNewStates
}

private fun formatDateToDateNews(str: String): String {
    return str.substring(startIndex = 0, endIndex = 16)
}
