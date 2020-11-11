package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

// Learning Android with Kotlin from https://www.youtube.com/watch?v=BBWyXo-3JGQ
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}