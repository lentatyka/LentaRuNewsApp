package com.lentatykalentarunewapp.presentation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.lentatykalentarunewapp.R
import com.lentatykalentarunewapp.common.State
import com.lentatykalentarunewapp.databinding.ActivityMainBinding
import com.lentatykalentarunewapp.di.DaggerAppComponent
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    lateinit var newsAdapter: NewsAdapter

    @Inject
    lateinit var vmFactory: MainViewModelFactory

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerAppComponent.create().inject(this)
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setContentView(binding.root)
        setAdapter()
        setViewModel()
    }

    private fun setViewModel() {
        viewModel = ViewModelProvider(this, vmFactory)[MainViewModel::class.java]
            viewModel.state.onEach {state ->
                when(state){
                    is State.Loading ->{
                        binding.swipeLayout.isRefreshing = true
                    }
                    is State.Error ->{
                        binding.swipeLayout.isRefreshing = false
                        showError(state.message)
                    }
                    is State.Success ->{
                        binding.swipeLayout.isRefreshing = false
                        newsAdapter.submitList(state.result.articles)
                    }
                }
            }.launchIn(lifecycleScope)
    }

    private fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun setAdapter() {
        newsAdapter = NewsAdapter{url ->
            Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse(url)
                startActivity(this)
            }
        }
        with(binding) {
            recycler.layoutManager = LinearLayoutManager(this@MainActivity)
            recycler.adapter = newsAdapter
            swipeLayout.apply {
                setOnRefreshListener {
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