package com.example.spacex_viewer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var txt: TextView = findViewById<TextView>(R.id.textStorage) as TextView
        var rcV: RecyclerView = findViewById<RecyclerView>(R.id.recyclerView) as RecyclerView

        var launchManager = LaunchManager(this, txt, rcV)
        launchManager.requestLaunchesData(true) // TODO: make list sorting
    }

    fun btnClick(view: android.view.View) {

    }
}