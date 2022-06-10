package com.ridwanproduction.androidusingdaggerhilt.persentation.todo_list.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ridwanproduction.androidusingdaggerhilt.R
import com.ridwanproduction.androidusingdaggerhilt.data.dto.TodoDto
import com.ridwanproduction.androidusingdaggerhilt.domain.model.Todo

class MainAdapter(
    private val todoList: ArrayList<Todo>
) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_todo, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val todoDto = todoList[holder.adapterPosition]
        holder.tvName.text = todoDto.title
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    fun addData(list: List<Todo>) {
        todoList.addAll(list)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName = itemView.findViewById<TextView>(R.id.tvName)
    }
}