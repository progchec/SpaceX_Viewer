package com.example.spacex_viewer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CompoundButton
import android.widget.Switch
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var rcV: RecyclerView = findViewById<RecyclerView>(R.id.recyclerView) as RecyclerView
        var swtc: Switch = findViewById<Switch>(R.id.sortedSwitch) as Switch

        fillList(swtc, rcV)
    }

    fun fillList(swtc: Switch, rcV: RecyclerView) {
        var launchManager = LaunchManager(this, rcV, swtc)
        launchManager.requestLaunchesData() // TODO: sort data directly in collection

        swtc.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener
        { compoundButton, isChecked ->
            launchManager.sortLaunches(isChecked)
            launchManager.notifyAdapter()
        })
    }
}