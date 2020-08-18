package com.nikhil.hiltwithdatabinding.ui.activities

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.nikhil.hiltwithdatabinding.R
import com.nikhil.hiltwithdatabinding.constants.Constants
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

    private lateinit var alertDialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.homeViewModel = homeViewModel

        alertDialog = Constants.getProgressDialog(this, getString(R.string.please_wait))

        observerServerResponse()
    }

    private fun observerServerResponse() {
        homeViewModel.observeResponse().observe(this,
            Observer<Any?> { response ->
                if (response is List<*>) {
                    @Suppress("UNCHECKED_CAST")
                    val listOfHeros = response as List<HeroResponse>
                    Log.d(TAG, "onChanged: ${listOfHeros.toString()}")
                    alertDialog.dismiss()
                    setUpAdapter(listOfHeros)
                } else if (response is String) {
                    if (response == Constants.NETWORK_HIT_INITIATED) {
                        alertDialog.show()
                    } else {
                        alertDialog.dismiss()
                        Toast.makeText(this, Constants.ERROR_MSG, Toast.LENGTH_SHORT).show()
                    }
                } else {
                    alertDialog.dismiss()
                    Toast.makeText(this, Constants.ERROR_MSG, Toast.LENGTH_SHORT).show()
                }
            })
    }

    private fun setUpAdapter(listOfHero: List<HeroResponse>) {
        if (listOfHero.isNotEmpty()) {
            val heroHomeAdapter = HerosHomeAdapter()
            binding.rvHomeRecyclerView.visibility = View.VISIBLE
            binding.rvHomeRecyclerView.layoutManager = LinearLayoutManager(this)
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