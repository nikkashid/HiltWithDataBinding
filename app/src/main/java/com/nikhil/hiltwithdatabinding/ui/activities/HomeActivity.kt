package com.nikhil.hiltwithdatabinding.ui.activities

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.nikhil.hiltwithdatabinding.R
import com.nikhil.hiltwithdatabinding.databinding.ActivityMainBinding
import com.nikhil.hiltwithdatabinding.responses.HeroResponse
import com.nikhil.hiltwithdatabinding.ui.adapters.HerosHomeAdapter
import com.nikhil.hiltwithdatabinding.viewModels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    //Hilt ViewModel Injection
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.homeViewModel = homeViewModel

        observerServerResponse()
    }

    private fun observerServerResponse() {
        homeViewModel.observeResponse().observe(this,
            Observer<Any?> { response ->
                if (response is List<*>) {
                    val listOfHeros: List<HeroResponse> = response as List<HeroResponse>
                    Log.d(TAG, "onChanged: ${listOfHeros.toString()}")
                    setUpAdapter(listOfHeros)
                } else {
                    Log.d(TAG, "onCreate: response is string which is an error")
                }
            })
    }

    private fun setUpAdapter(listOfHero: List<HeroResponse>) {
        if (listOfHero.isNotEmpty()) {
            val heroHomeAdapter = HerosHomeAdapter()
            binding.rvHomeRecyclerView.visibility = View.VISIBLE
            binding.rvHomeRecyclerView.layoutManager = LinearLayoutManager(this);
            binding.rvHomeRecyclerView.hasFixedSize()
            binding.rvHomeRecyclerView.adapter = heroHomeAdapter
            heroHomeAdapter.submitList(listOfHero)
            binding.btnGetData.visibility = View.GONE
        }
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}