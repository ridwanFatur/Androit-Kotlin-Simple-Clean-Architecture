package com.ridwanproduction.androidusingdaggerhilt.persentation.todo_list

import com.ridwanproduction.androidusingdaggerhilt.domain.model.Todo

data class MainState(
    val isLoading: Boolean = false,
    val todoList: List<Todo> = emptyList(),
    val error: String = ""
)