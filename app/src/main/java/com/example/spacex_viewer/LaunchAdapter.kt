package com.example.spacex_viewer

import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.RequestQueue
import com.android.volley.toolbox.ImageRequest

public class LaunchAdapter constructor(context: Context, launches: List<Launch>, queue: RequestQueue) : RecyclerView.Adapter<LaunchAdapter.ViewHolder>() {
    private val context: Context = context
    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)
    private val launches: List<Launch> = launches

    private val queue: RequestQueue = queue

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaunchAdapter.ViewHolder {
        var view: View = layoutInflater.inflate(R.layout.list_launch_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: LaunchAdapter.ViewHolder, position: Int) {
        var launch: Launch = launches.get(position)

        holder.missionNameView.setText(launch.getMissionName())
        holder.launchDateUTCView.setText(launch.getLaunchDateUTC())
        holder.detailsView.setText(launch.getDetails())

        // TODO: do a request here
        getImageBitmap(holder, launch)
    }

    fun getImageBitmap(holder: ViewHolder, launch: Launch) {
        var imageRequest: ImageRequest = ImageRequest(launch.getMissionPatchSmall(),
            { bitmap ->
                holder.missionPatchImage.setImageBitmap(bitmap)
            },
            0, 0, ImageView.ScaleType.CENTER_CROP,
            Bitmap.Config.ARGB_8888,
            { error ->
                val errorHandler: ErrorHandler = ErrorHandler(context, error.toString())
                errorHandler.showError()
            })

        queue.add(imageRequest)
    }

    override fun getItemCount(): Int {
        return launches.size
    }



    class ViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val missionNameView: TextView = itemView.findViewById(R.id.missionNameTxt)
        val launchDateUTCView: TextView = itemView.findViewById(R.id.launchDateTxt)
        val detailsView: TextView = itemView.findViewById(R.id.detailsTxt)

        val missionPatchImage: ImageView = itemView.findViewById(R.id.missionPatchImage)
    }
}