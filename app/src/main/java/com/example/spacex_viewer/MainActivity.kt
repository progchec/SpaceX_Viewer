package com.example.spacex_viewer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CompoundButton
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.widget.SwitchCompat
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var txt: TextView = findViewById<TextView>(R.id.textStorage) as TextView
        var rcV: RecyclerView = findViewById<RecyclerView>(R.id.recyclerView) as RecyclerView
        var swtc: Switch = findViewById<Switch>(R.id.sortedSwitch) as Switch

        fillList(swtc, txt, rcV)
    }

    fun fillList(swtc: Switch, txt: TextView, rcV: RecyclerView) {
        var isSorted: Boolean = swtc.isChecked

        var launchManager = LaunchManager(this, txt, rcV)
        launchManager.requestLaunchesData(isSorted)

        swtc.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener
        { compoundButton, isChecked ->
            isSorted = isChecked

            // var launchManager = LaunchManager(this, txt, rcV)
            launchManager.requestLaunchesData(isSorted)
        })
    }

    fun btnClick(view: android.view.View) {

    }
}