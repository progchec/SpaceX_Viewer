package com.example.spacex_viewer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CompoundButton
import android.widget.Switch
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var launchesRecyclerView: RecyclerView = findViewById<RecyclerView>(R.id.recyclerView) as RecyclerView
        var sortSwitch: Switch = findViewById<Switch>(R.id.sortedSwitch) as Switch
        var refreshButton: Button = findViewById<Button>(R.id.refreshButton) as Button
        fillList(sortSwitch, launchesRecyclerView)

        refreshButton.setOnClickListener(View.OnClickListener {
            fillList(sortSwitch, launchesRecyclerView)
        })
    }

    private fun fillList(sortSwitch: Switch, launchesRecyclerView: RecyclerView) {
        var launchManager: LaunchManager = LaunchManager(this, launchesRecyclerView, sortSwitch)
        launchManager.requestLaunchesData()

        sortSwitch.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener
        { compoundButton, isChecked ->
            launchManager.sortLaunches(isChecked)
            launchManager.notifyAdapter()
        })
    }
}