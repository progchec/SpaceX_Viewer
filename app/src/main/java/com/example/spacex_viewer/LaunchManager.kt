package com.example.spacex_viewer

import android.content.Context
import android.widget.Switch
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject

class LaunchManager constructor(context: Context, txt: TextView, rcV: RecyclerView, swtc: Switch) {
    private var context: Context = context
    private var txt: TextView = txt
    private var rcV: RecyclerView = rcV
    private var swtc: Switch = swtc

    private var queue: RequestQueue = Volley.newRequestQueue(context)

    private var launches: ArrayList<Launch> = ArrayList<Launch>()
    private var launchAdapter: LaunchAdapter = LaunchAdapter(context, launches, queue)

    init {
        rcV.layoutManager = LinearLayoutManager(context)
        rcV.adapter = launchAdapter
    }

    fun requestLaunchesData() {
        launches.clear()

        for (year in 2015..2019) {
            var url: String = "https://api.spacexdata.com/v3/launches?launch_year=$year"
            getJSONData(url, queue)
        }
    }

    private fun getJSONData(url: String, queue: RequestQueue) {
        var jsonRequest = JsonArrayRequest(
            Request.Method.GET, url, null,
            { response ->
                    processJSONResponse(response)
            },
            { error ->
                var errorHandler: ErrorHandler = ErrorHandler(context, error.toString())
                errorHandler.showError()
            })
        queue.add(jsonRequest)
    }

    private fun processJSONResponse(response: JSONArray) {
        var lastElem: Int = response.length() - 1 // TODO: find more elegant way to encount an array

        for (launch_counter in 0..lastElem) { // TODO: if "sort" pressed, encount array from lastElem to 0
            sortLaunchElements(response, launch_counter)
        }

        sortLaunches(swtc.isChecked)
    }

    private fun sortLaunchElements(response: JSONArray, launch_counter: Int) {
        var launch: JSONObject = response[launch_counter] as JSONObject

        // mission_name, launch_date_utc, details, links -> mission_patch_small

        var missionName: String = launch.getString("mission_name")
        var launchDateUTC: String = launch.getString("launch_date_utc")
        var details: String = launch.getString("details")
        var missionPatchSmall: String = launch.getJSONObject("links").getString("mission_patch_small")

        launches.add(Launch(missionName, launchDateUTC, details, missionPatchSmall))
        launchAdapter.notifyItemInserted(launch_counter)

        txt.setText(missionPatchSmall)
    }

    fun sortLaunches(isSortedByIncrease: Boolean) {
        if (isSortedByIncrease) {
            launches.sortBy {
                    launch -> launch.getLaunchDateUTC()
            }
        }
        else {
            launches.sortByDescending {
                    launch -> launch.getLaunchDateUTC()
            }
        }
    }

    fun notifyAdapter() {
        launchAdapter.notifyDataSetChanged()
    }
}