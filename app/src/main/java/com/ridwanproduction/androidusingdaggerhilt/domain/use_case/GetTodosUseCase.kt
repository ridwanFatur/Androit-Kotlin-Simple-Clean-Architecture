package com.ridwanproduction.androidusingdaggerhilt.domain.use_case

import com.ridwanproduction.androidusingdaggerhilt.domain.repository.TodoRepository
import com.ridwanproduction.androidusingdaggerhilt.common.Resource
import com.ridwanproduction.androidusingdaggerhilt.data.dto.TodoDto
import com.ridwanproduction.androidusingdaggerhilt.data.dto.toTodo
import com.ridwanproduction.androidusingdaggerhilt.domain.model.Todo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetTodosUseCase @Inject constructor(
    private val repository: TodoRepository
){
    operator fun invoke(): Flow<Resource<List<Todo>>> = flow {
        try {
            emit(Resource.Loading<List<Todo>>())
            val list = repository.getTodoList().map { it.toTodo() }
            emit(Resource.Success<List<Todo>>(list))
        } catch(e: HttpException) {
            emit(Resource.Error<List<Todo>>(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error<List<Todo>>("Couldn't reach server. Check your internet connection."))
        } catch (e: Exception){
            emit(Resource.Error<List<Todo>>("Unexpected Error"))
        }
    }

}