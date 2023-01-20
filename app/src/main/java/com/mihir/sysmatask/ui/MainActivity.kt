package com.mihir.sysmatask.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.mihir.sysmatask.R
import com.mihir.sysmatask.databinding.ActivityMainBinding
import com.mihir.sysmatask.model.ClassItem
import com.mihir.sysmatask.ui.adapter.Adapter

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val viewModel by lazy { ViewModelProvider(this)[ViewModel::class.java] }

    private val adapter by lazy { Adapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        window.statusBarColor = ContextCompat.getColor(this,R.color.teal_200)
        setContentView(binding.root)

        binding.recycler.adapter = adapter

        setObservers()
    }

    private fun setObservers() {
        viewModel.readAllData.observe(this){list->
            adapter.setItems(list as ArrayList<ClassItem>)
        }

        viewModel.apiStatus.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        }
    }
}