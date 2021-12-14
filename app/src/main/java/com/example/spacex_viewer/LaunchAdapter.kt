package com.example.spacex_viewer

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

public class LaunchAdapter constructor(context: Context, launches: List<Launch>) : RecyclerView.Adapter<LaunchAdapter.ViewHolder>() {
    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)
    private val launches: List<Launch> = launches

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaunchAdapter.ViewHolder {
        var view: View = layoutInflater.inflate(R.layout.list_launch_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: LaunchAdapter.ViewHolder, position: Int) {
        var launch: Launch = launches.get(position)
        // write ViewHolder class first
        holder.missionNameView.setText(launch.getMissionName())
        holder.launchDateUTCView.setText(launch.getLaunchDateUTC())
        holder.detailsView.setText(launch.getDetails())
        holder.missionPatchSmallView.setText(launch.getMissionPatchSmall())
    }

    override fun getItemCount(): Int {
        return launches.size
    }

    class ViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val missionNameView: TextView = itemView.findViewById(R.id.missionNameTxt)
        val launchDateUTCView: TextView = itemView.findViewById(R.id.launchDateTxt)
        val detailsView: TextView = itemView.findViewById(R.id.detailsTxt)
        val missionPatchSmallView: TextView = itemView.findViewById(R.id.missionPatchSmall)
    }
}