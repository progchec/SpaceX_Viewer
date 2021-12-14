package com.example.spacex_viewer

import android.content.Context
import android.widget.TableLayout
import android.widget.TextView
import android.widget.Toast
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

    private var launches: ArrayList<Launch> = ArrayList<Launch>()
    private var launchAdapter: LaunchAdapter = LaunchAdapter(context, launches)

    init {
        rcV.layoutManager = LinearLayoutManager(context)
        rcV.adapter = launchAdapter
    }

    fun requestLaunchesData(launchYear: String) {
        var url: String = "https://api.spacexdata.com/v3/launches?launch_year=$launchYear"
        var queue: RequestQueue = Volley.newRequestQueue(context)

        getJSONData(url, queue)
    }

    private fun getJSONData(url: String, queue: RequestQueue) {
        var jsonRequest = JsonArrayRequest(
            Request.Method.GET, url, null,
            { response ->
                    processJSONResponse(response)
            },
            { error ->
                Toast.makeText(context, "Missing internet connection!", Toast.LENGTH_LONG).show()
            })
        queue.add(jsonRequest)
    }

    private fun processJSONResponse(response: JSONArray) {
        var lastElem: Int = response.length() - 1 // TODO: find more elegant way to encount an array

        Toast.makeText(context, response.getJSONObject(0).getString("mission_name"), Toast.LENGTH_LONG).show()

        for (launch_counter in 0..lastElem) {
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