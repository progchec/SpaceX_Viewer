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
        // var txt: TextView = findViewById<TextView>(R.id.textStorage) as TextView
        // var table: TableLayout = findViewById<TableLayout>(R.id.tableLayout) as TableLayout

        var queue: RequestQueue = Volley.newRequestQueue(context)
        var url: String = "https://api.spacexdata.com/v3/launches?launch_year=$launchYear" // 2015-2019

        // mission_name, launch_date_utc, details, links -> mission_patch_small

        getJSONData(url, queue)
    }

    private fun getJSONData(url: String, queue: RequestQueue) {
        var jsonRequest = JsonArrayRequest(
            Request.Method.GET, url, null,
            { response ->
                var launch: JSONObject // = response[0] as JSONObject
                // Toast.makeText(this, lnc.getString("flight_number"), Toast.LENGTH_LONG).show()
                // txt.setText(launch.getJSONObject("links").getString("mission_patch_small"))

                var lastElem: Int = response.length() - 1
                for (launch_counter in 0..lastElem) {
                    launch = response[launch_counter] as JSONObject

                    var missionName: String = launch.getString("mission_name")
                    var launchDateUtc: String = launch.getString("launch_date_utc")
                    var details: String = launch.getString("details")
                    var missionPatchSmall: String = launch.getJSONObject("links").getString("mission_patch_small")
                    Toast.makeText(context, missionPatchSmall, Toast.LENGTH_LONG).show()

                    // TODO: set these values in table
                }
            },
            { error ->
                Toast.makeText(context, "Missing internet connection!", Toast.LENGTH_LONG).show()
            })
        queue.add(jsonRequest)
    }
}