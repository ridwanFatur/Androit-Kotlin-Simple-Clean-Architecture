package com.ridwanproduction.androidusingdaggerhilt.data.repository

import com.ridwanproduction.androidusingdaggerhilt.data.dto.TodoDto
import com.ridwanproduction.androidusingdaggerhilt.data.remote.TodoApi
import com.ridwanproduction.androidusingdaggerhilt.domain.repository.TodoRepository
import javax.inject.Inject

class TodoRepositoryImpl @Inject constructor(
    private val api: TodoApi
) : TodoRepository {

    override suspend fun getTodoList(): List<TodoDto> {
        return api.getTodoList()
    }
}