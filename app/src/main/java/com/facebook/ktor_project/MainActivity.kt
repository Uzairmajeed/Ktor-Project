package com.facebook.ktor_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.facebook.ktor_project.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MyViewModel
    private lateinit var postAdapter: PostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Inside onCreate method
        viewModel = ViewModelProvider(this).get(MyViewModel::class.java)
        postAdapter = PostAdapter(emptyList()) // Pass an empty list initially

// Call the fetch method in the ViewModel
        lifecycleScope.launch(Dispatchers.IO) {
            viewModel.fetchFromModel()
        }

// Observe the LiveData in ViewModel
        viewModel.postListLiveData.observe(this) { postList ->
            // Update the adapter with the new data
            postAdapter.updateData(postList)
            Log.d("MainActivity", "Observed data: ${postList.size}")
        }

        binding.recyclerview.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = postAdapter
        }

    }
}