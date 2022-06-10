package com.ridwanproduction.androidusingdaggerhilt.persentation.todo_list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ridwanproduction.androidusingdaggerhilt.persentation.todo_list.adapter.MainAdapter
import com.ridwanproduction.androidusingdaggerhilt.R
import com.ridwanproduction.androidusingdaggerhilt.data.dto.TodoDto
import com.ridwanproduction.androidusingdaggerhilt.databinding.ActivityMainBinding
import com.ridwanproduction.androidusingdaggerhilt.domain.model.Todo
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var adapter: MainAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
        setupObserver()

        binding.btnError.setOnClickListener {
            mainViewModel.getTodoList()
        }
    }

    private fun setupUI() {
        binding.rvMain.layoutManager = LinearLayoutManager(this)
        adapter = MainAdapter(arrayListOf())
        binding.rvMain.addItemDecoration(
            DividerItemDecoration(
                binding.rvMain.context,
                (binding.rvMain.layoutManager as LinearLayoutManager).orientation
            )
        )
        binding.rvMain.adapter = adapter
    }

    private fun setupObserver() {
        mainViewModel.state.observe(this, Observer {
            when {
                it.isLoading -> {
                    binding.pbLoading.visibility = View.VISIBLE
                    binding.rvMain.visibility = View.GONE
                    binding.lnError.visibility = View.GONE
                    binding.tvNoData.visibility = View.GONE
                }
                it.error.isNotBlank() -> {
                    binding.pbLoading.visibility = View.GONE
                    binding.rvMain.visibility = View.GONE
                    binding.lnError.visibility = View.VISIBLE
                    binding.tvNoData.visibility = View.GONE

                    binding.tvError.text = it.error
                }
                it.todoList.isEmpty() -> {
                    binding.pbLoading.visibility = View.GONE
                    binding.rvMain.visibility = View.GONE
                    binding.lnError.visibility = View.GONE
                    binding.tvNoData.visibility = View.VISIBLE
                }
                else -> {
                    binding.pbLoading.visibility = View.GONE
                    binding.rvMain.visibility = View.VISIBLE
                    binding.lnError.visibility = View.GONE
                    binding.tvNoData.visibility = View.GONE
                    renderList(it.todoList)
                }
            }
        })
//        mainViewModel.users.observe(this, Observer {
//            when (it.status) {
//                Status.SUCCESS -> {
//                    progressBar.visibility = View.GONE
//                    it.data?.let { users -> renderList(users) }
//                    recyclerView.visibility = View.VISIBLE
//                }
//                Status.LOADING -> {
//                    progressBar.visibility = View.VISIBLE
//                    recyclerView.visibility = View.GONE
//                }
//                Status.ERROR -> {
//                    progressBar.visibility = View.GONE
//                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
//                }
//            }
//        })
    }

    private fun renderList(list: List<Todo>) {
        adapter.addData(list)
        adapter.notifyDataSetChanged()
    }
}