package com.ridwanproduction.androidusingdaggerhilt.data.dto

import com.ridwanproduction.androidusingdaggerhilt.domain.model.Todo

data class TodoDto(
    val completed: Boolean,
    val id: Int,
    val title: String,
    val userId: Int
)

fun TodoDto.toTodo(): Todo {
    return Todo(
        id = id,
        completed = completed,
        title = title,
        userId = userId
    )
}