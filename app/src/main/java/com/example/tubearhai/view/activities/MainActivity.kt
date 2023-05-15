package com.example.tubearhai.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.tubearhai.R
import com.example.tubearhai.databinding.ActivityMainBinding
import com.example.tubearhai.view.adapter.BeerAdapter
import com.example.tubearhai.view.network.RetrofitInstance
import retrofit2.HttpException
import java.io.IOException

class MainActivity : AppCompatActivity() {
    companion object{
        const val TAG :String ="Exception"
    }

    private lateinit var binding: ActivityMainBinding

    private lateinit var beerAdapter: BeerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()

        lifecycleScope.launchWhenCreated {
            binding.pbMain.isVisible = true
            val response = try {
                RetrofitInstance.api.getBeers(1)
            } catch(e: IOException) {
                Log.e(TAG, "IOException, you might not have internet connection")
                binding.pbMain.isVisible = false
                return@launchWhenCreated
            } catch (e: HttpException) {
                Log.e(TAG, "HttpException, unexpected response")
                binding.pbMain.isVisible = false
                return@launchWhenCreated
            }
            if(response.isSuccessful && response.body() != null) {
                beerAdapter.beers = response.body()!!
            } else {
                Log.e(TAG, "Response not successful")
            }
            binding.pbMain.isVisible = false
        }
    }

    private fun setupRecyclerView() = binding.rvMain.apply {
        beerAdapter = BeerAdapter(this@MainActivity)
        adapter = beerAdapter
        layoutManager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.VERTICAL,false)
    }
}