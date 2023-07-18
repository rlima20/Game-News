package com.example.gamenews.repository

import android.util.Log
import com.example.gamenews.model.GameNewsDTO
import com.example.gamenews.model.GameNewsState
import com.example.gamenews.provider.local.listOfNews
import com.example.gamenews.provider.remote.GameNewsService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import retrofit2.Response

class GameNewsRepositoryImpl(
    private val gameNewsService: GameNewsService,
) : GameNewsRepository {
    override fun getAllGameNews(): Flow<List<GameNewsDTO>?> = flow {
        validateRequest(gameNewsService.getAllGameNews())
    }

    override fun getAllGameNewsLocal(): List<GameNewsState> {
        return listOfNews
    }

    override fun geAllGameNewsByQuery(query: String, page: Int) = flow {
        validateRequest(gameNewsService.getAllGameNewsByQuery(query, page))
    }

    override fun geAllGameNewsByQueryLocal(query: String, page: Int): Flow<List<GameNewsDTO>?> =
        flow {
            emit(gameNewsService.getAllGameNewsByQueryLocal())
        }

    private suspend fun FlowCollector<List<GameNewsDTO>?>.validateRequest(
        request:
        Response<List<GameNewsDTO>>?,
    ) {
        with(request) {
            Log.i("REQUEST", "Status code = ${this?.code()}")

            with(this) {
                val code = this?.code()
                if (code == 404 ||
                    code == 403 ||
                    code == 501 ||
                    code == 500 ||
                    code == 502
                ) {
                    emit(null)
                } else if (this?.isSuccessful == true) {
                    this.body()?.let { emit(it) }
                } else {
                    emit(
                        emptyList(),
                    )
                }
            }
        }
    }
}
