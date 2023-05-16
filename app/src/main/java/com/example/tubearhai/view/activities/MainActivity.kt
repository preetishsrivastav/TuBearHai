package com.example.tubearhai.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.tubearhai.R
import com.example.tubearhai.databinding.ActivityMainBinding
import com.example.tubearhai.repository.Repository
import com.example.tubearhai.view.adapter.BeerAdapter
import com.example.tubearhai.view.network.RetrofitInstance
import com.example.tubearhai.viewmodel.MainViewModel
import com.example.tubearhai.viewmodel.ViewModelFactory
import retrofit2.HttpException
import java.io.IOException

class MainActivity : AppCompatActivity() {
    companion object{
        const val TAG :String ="Exception"
    }

    private lateinit var binding: ActivityMainBinding
    private lateinit var beerAdapter: BeerAdapter
    private lateinit var mainViewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()

        val beerApi= RetrofitInstance.api
        val repository=Repository(beerApi)

        mainViewModel=ViewModelProvider(this,ViewModelFactory(repository)).get(MainViewModel::class.java)

        mainViewModel.beerLiveData.observe(this, Observer {
            beerAdapter.beers = it
        })

//        lifecycleScope.launchWhenCreated {
//            binding.pbMain.isVisible = true
//            val response = try {
//                RetrofitInstance.api.getBeers(1)
//            } catch(e: IOException) {
//                Log.e(TAG, "IOException, you might not have internet connection")
//                binding.pbMain.isVisible = false
//                return@launchWhenCreated
//            } catch (e: HttpException) {
//                Log.e(TAG, "HttpException, unexpected response")
//                binding.pbMain.isVisible = false
//                return@launchWhenCreated
//            }
//            if(response.isSuccessful && response.body() != null) {
//                beerAdapter.beers = response.body()!!
//            } else {
//                Log.e(TAG, "Response not successful")
//            }
//            binding.pbMain.isVisible = false
//        }
    }

    private fun setupRecyclerView() = binding.rvMain.apply {
        beerAdapter = BeerAdapter(this@MainActivity)
        adapter = beerAdapter
        layoutManager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.VERTICAL,false)
    }
}