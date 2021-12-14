package com.example.spacex_viewer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TableLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun btnClick(view: android.view.View) {
        var txt: TextView = findViewById<TextView>(R.id.textStorage) as TextView
        var rcV: RecyclerView = findViewById<RecyclerView>(R.id.recyclerView) as RecyclerView

        var launchManager = LaunchManager(this, txt, rcV)

        launchManager.requestLaunchesData("2019")
        launchManager.requestLaunchesData("2018")
        launchManager.requestLaunchesData("2017")
        launchManager.requestLaunchesData("2016")
        launchManager.requestLaunchesData("2015")
    }
}