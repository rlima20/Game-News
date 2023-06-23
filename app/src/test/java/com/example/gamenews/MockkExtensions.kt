package com.example.gamenews

import io.mockk.ConstantAnswer
import io.mockk.MockKAnnotations
import io.mockk.MockKStubScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

fun Any.initMockKAnnotations() {
    MockKAnnotations.init(this, relaxUnitFun = true)
}

infix fun <T, B> MockKStubScope<Flow<T>, B>.emmits(emitValue: T) =
    answers(ConstantAnswer(flow { emit(emitValue) }))

infix fun <T, B> MockKStubScope<Flow<T>, B>.emmits(emitValues: List<T>) = answers(
    ConstantAnswer(
        flow {
            emitValues.forEach { value ->
                emit(value)
            }
        },
    ),
)
