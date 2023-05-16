package com.example.tubearhai.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tubearhai.databinding.ActivityMainBinding
import com.example.tubearhai.repository.Repository
import com.example.tubearhai.utils.BottomSheetDialog
import com.example.tubearhai.view.adapter.BeerAdapter
import com.example.tubearhai.view.network.RetrofitInstance
import com.example.tubearhai.viewmodel.MainViewModel
import com.example.tubearhai.viewmodel.ViewModelFactory

class MainActivity : AppCompatActivity() {
    companion object{
        const val TAG :String ="Exception"
    }

    private lateinit var binding: ActivityMainBinding
    private lateinit var beerAdapter: BeerAdapter
    private lateinit var mainViewModel: MainViewModel
    private lateinit var lm:LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()

        val beerApi= RetrofitInstance.api
        val repository=Repository(beerApi)

        mainViewModel=ViewModelProvider(this,ViewModelFactory(repository)).get(MainViewModel::class.java)
        mainViewModel.getNextPage()

        mainViewModel.beerLiveData.observe(this, Observer {
            beerAdapter.beers = it
        })


    }
    private fun showBottomSheetDialog(firstBrewed:String, description:String, foodPairing:List<String>){
        val bottomSheet= BottomSheetDialog(firstBrewed, description, foodPairing)
        bottomSheet.show(supportFragmentManager,"BottomSheetFragment")
    }

    private fun setupRecyclerView() = binding.rvMain.apply {
        beerAdapter = BeerAdapter(this@MainActivity,::showBottomSheetDialog)
        adapter = beerAdapter
        lm=LinearLayoutManager(this@MainActivity,LinearLayoutManager.VERTICAL,false)
        layoutManager = lm
        addOnScrollListener(object :RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy>0){
                    val childCount = beerAdapter.itemCount
                    val lastPosition=lm.findLastCompletelyVisibleItemPosition()
                   if (childCount-1 == lastPosition){
                       mainViewModel.getNextPage()
                   }

                }
            }


        })

    }
}