package com.ridwanproduction.androidusingdaggerhilt.data.remote

import com.ridwanproduction.androidusingdaggerhilt.data.dto.TodoDto
import retrofit2.http.GET

interface TodoApi {
    @GET("/todos")
    suspend fun getTodoList(): List<TodoDto>
}