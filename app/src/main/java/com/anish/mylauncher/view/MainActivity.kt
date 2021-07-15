package com.anish.mylauncher.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.anish.mylauncher.R
import com.anish.mylauncher.databinding.ActivityMainBinding
import com.anish.mylauncher.viewmodel.MyViewModel

class MainActivity : AppCompatActivity() {
    val TAG = MainActivity::class.java.name
    lateinit var viewModel: MyViewModel
    lateinit var binding: ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setUpBinding(savedInstanceState)
        observeLiveData()
        setUpRV()
        viewModel.fetchAppDetails()

    }

    private fun observeLiveData() {

        viewModel.launchIntent_LiveData.observe(
            this,
            Observer {

                startActivity(it)
            }
        )
    }

    private fun setUpBinding(savedInstanceState: Bundle?) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        if(savedInstanceState == null) viewModel = ViewModelProvider(this).get(MyViewModel::class.java)
        binding.vm = viewModel
    }

    private fun setUpRV() {
        binding.rv.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL,false)

        binding.rv.adapter = viewModel.adapter

    }
}