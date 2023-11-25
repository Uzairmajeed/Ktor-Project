package com.facebook.ktor_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import coil.load
import com.facebook.ktor_project.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)
        val viewModel = ViewModelProvider(this).get(MyViewModel::class.java)
        lifecycleScope.launch(Dispatchers.IO) {
            viewModel.fetchFromModel()
        }
        viewModel.postLiveData.observe(this) { post ->
            if (post != null) {
                binding.titleTextView.text=post.title+"."
                binding.descriptionTextView.text=post.description
                // Use the utility function to extract the year
                binding.yearTextView.text = DateUtils.extractYearFromDate(post.date)+" ."
                binding.languageTextView.text=post.content_language.toUpperCase()+" ."
                binding.comedyTextView.text=post.content_genre+" ."
                binding.DramedyTextView.text=post.roku_genre+"."
                binding.ratingTextView.text="U/A 13+"+"."

                // Using Coil to load the image into the ImageView
                binding.imageView.load(post.xhd_image) {
                    crossfade(true) // Optional: Enable crossfade for a smooth transition
                }
            } else {
                Log.e("Network", "Not_Getting_Data")
            }
        }
    }
}