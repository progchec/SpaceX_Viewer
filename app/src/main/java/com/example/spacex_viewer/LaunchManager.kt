package com.example.spacex_viewer

import android.content.Context
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject

class LaunchManager constructor(context: Context, txt: TextView, rcV: RecyclerView) {
    private var context: Context = context
    private var txt: TextView = txt
    private var rcV: RecyclerView = rcV

    private var queue: RequestQueue = Volley.newRequestQueue(context)

    private var launches: ArrayList<Launch> = ArrayList<Launch>()
    private var launchAdapter: LaunchAdapter = LaunchAdapter(context, launches, queue)

    init {
        rcV.layoutManager = LinearLayoutManager(context)
        rcV.adapter = launchAdapter
    }

    fun requestLaunchesData(isSorted: Boolean) {
        launches.clear()
        launchAdapter.notifyDataSetChanged()

        var sequence = if (isSorted)
            2015..2019
        else
            2019 downTo 2015

        for (year in sequence) { // TODO: if "sort" pressed, encount array from 2019 to 2015
            var url: String = "https://api.spacexdata.com/v3/launches?launch_year=$year"
            getJSONData(url, queue, isSorted)
        }
    }

    private fun getJSONData(url: String, queue: RequestQueue, isSorted: Boolean) {
        var jsonRequest = JsonArrayRequest(
            Request.Method.GET, url, null,
            { response ->
                    processJSONResponse(response, isSorted)
            },
            { error ->
                var errorHandler: ErrorHandler = ErrorHandler(context, error.toString())
                errorHandler.showError()
            })
        queue.add(jsonRequest)
    }

    private fun processJSONResponse(response: JSONArray, isSorted: Boolean) {
        var lastElem: Int = response.length() - 1 // TODO: find more elegant way to encount an array

        var sequence = if (isSorted)
            0..lastElem
        else
            lastElem downTo 0

        for (launch_counter in sequence) { // TODO: if "sort" pressed, encount array from lastElem to 0
            sortLaunchElements(response, launch_counter)
        }
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
}