package com.example.udemy_flow_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.example.udemy_flow_1.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val mainFlow = flow<Int> {
            for(i in 1..100) {
                emit(i)
                delay(1_000L)

            }
        }

        CoroutineScope(Dispatchers.Main).launch {
            mainFlow.collect {
                binding.tvIndex.text = "$it"
            }
        }

    }
}