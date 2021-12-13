package com.example.spacex_viewer

import android.content.Context
import android.widget.TableLayout
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class LaunchManager constructor(context: Context, txt: TextView, table: TableLayout) {
    private var context: Context = context
    private var txt: TextView = txt
    private var table: TableLayout = table

    fun requestLaunchesData(launchYear: String) {
        var queue: RequestQueue = Volley.newRequestQueue(context)
        var url: String = "https://api.spacexdata.com/v3/launches?launch_year=$launchYear" // 2015-2019

        // mission_name, launch_date_utc, details, links -> mission_patch_small

        getJSONData(url, queue)
    }

    private fun getJSONData(url: String, queue: RequestQueue) {
        var jsonRequest = JsonArrayRequest(
            Request.Method.GET, url, null,
            { response ->
                var lastElem: Int = response.length() - 1 // TODO: find more elegant way to encount an array

                var launch: JSONObject
                for (launch_counter in 0..lastElem) {
                    launch = response[launch_counter] as JSONObject

                    var missionName: String = launch.getString("mission_name")
                    var launchDateUtc: String = launch.getString("launch_date_utc")
                    var details: String = launch.getString("details")
                    var missionPatchSmall: String = launch.getJSONObject("links").getString("mission_patch_small")

                    // TODO: set these values in table
                }
            },
            { error ->
                Toast.makeText(context, "Missing internet connection!", Toast.LENGTH_LONG).show()
            })
        queue.add(jsonRequest)
    }
}