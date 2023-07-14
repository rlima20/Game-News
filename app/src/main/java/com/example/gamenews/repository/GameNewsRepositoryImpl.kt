package com.example.gamenews.repository

import android.util.Log
import com.example.gamenews.model.GameNewsDTO
import com.example.gamenews.model.GameNewsState
import com.example.gamenews.provider.local.listOfNews
import com.example.gamenews.provider.remote.GameNewsService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GameNewsRepositoryImpl(
    private val gameNewsService: GameNewsService,
) : GameNewsRepository {
    override fun getAllGameNews(): Flow<List<GameNewsDTO>?> = flow {
        with(gameNewsService.getAllGameNews()) {
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

    override fun getAllGameNewsLocal(): List<GameNewsState> {
        return listOfNews
    }
}
