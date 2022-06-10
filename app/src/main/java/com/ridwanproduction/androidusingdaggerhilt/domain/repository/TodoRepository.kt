package com.ridwanproduction.androidusingdaggerhilt.domain.repository

import com.ridwanproduction.androidusingdaggerhilt.data.dto.TodoDto

interface  TodoRepository {
    suspend fun getTodoList() : List<TodoDto>
}