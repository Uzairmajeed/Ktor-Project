package com.facebook.ktor_project

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
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
        // Set an OnScrollChangeListener to detect scroll events
       binding.nestedscrollview.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            // Check if the NestedScrollView is scrolled
            if (scrollY > 2) {
                // If scrolled, make the FloatingActionButton visible
                binding.fab.show()
            } else {
                // If not scrolled, make the FloatingActionButton invisible
                binding.fab.hide()
            }
        })
        // Set an OnClickListener to the FloatingActionButton
       binding.fab.setOnClickListener(View.OnClickListener { // Smoothly scroll to the top of the NestedScrollView
            binding.nestedscrollview.smoothScrollTo(0, 0)
        })
    }
}