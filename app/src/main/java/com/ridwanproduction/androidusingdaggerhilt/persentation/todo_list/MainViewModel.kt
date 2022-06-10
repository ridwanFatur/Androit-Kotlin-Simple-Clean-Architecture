package com.ridwanproduction.androidusingdaggerhilt.persentation.todo_list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ridwanproduction.androidusingdaggerhilt.common.Resource
import com.ridwanproduction.androidusingdaggerhilt.data.dto.TodoDto
import com.ridwanproduction.androidusingdaggerhilt.domain.model.Todo
import com.ridwanproduction.androidusingdaggerhilt.domain.use_case.GetTodosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getTodosUseCase: GetTodosUseCase,
) : ViewModel() {
    private val _state = MutableLiveData<MainState>()
    val state: LiveData<MainState>
        get() = _state

    init {
        Log.d("TAG","Init")
        getTodoList()
    }

    fun getTodoList() {
        Log.d("TAG","Get Todo List")
        getTodosUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    Log.d("TAG","Test Success")
                    _state.value = MainState(todoList = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    Log.d("TAG","Test Error")
                    _state.value = MainState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    Log.d("TAG","Test Loading")
                    _state.value = MainState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}