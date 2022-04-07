package com.lentatykalentarunewapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.lentatykalentarunewapp.R
import com.lentatykalentarunewapp.common.State
import com.lentatykalentarunewapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    lateinit var newsAdapter: NewsAdapter
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setContentView(binding.root)
        setAdapter()
        setViewModel()
    }

    private fun setViewModel() {
            viewModel.state.onEach {state ->
                if(state is State.Success){
                    newsAdapter.submitList(state.result.articles)
                }
            }.launchIn(lifecycleScope)
    }

    private fun setAdapter() {
        newsAdapter = NewsAdapter()
        with(binding) {
            recycler.layoutManager = LinearLayoutManager(this@MainActivity)
            recycler.adapter = newsAdapter
            swipeLayout.apply {
                setProgressViewEndTarget(false, 0)
                setOnRefreshListener {
                    isRefreshing = false
                    viewModel.getNews()
                }
            }
        }

    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}