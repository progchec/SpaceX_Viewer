package com.example.spacex_viewer

class Launch constructor(missionName: String, launchDateUTC: String,
                         details: String, missionPatchSmall: String) {
    private var missionName: String = missionName
    private var launchDateUTC: String = launchDateUTC
    private var details: String = details
    private var missionPatchSmall: String = missionPatchSmall

    fun getMissionName(): String {
        return missionName
    }

    fun getLaunchDateUTC(): String {
        return launchDateUTC
    }

    fun getDetails(): String {
        return details;
    }

    fun getMissionPatchSmall(): String {
        return missionPatchSmall
    }

    fun setMissionName(missionName: String) {
        this.missionName = missionName
    }

    fun setLaunchDateUTC(launchDateUTC: String) {
        this.launchDateUTC = launchDateUTC
    }

    fun setDetails(details: String) {
        this.details = details
    }

    fun setMissionPatchSmall(missionName: String) {
        this.missionPatchSmall = missionPatchSmall
    }

    // TODO: write setters and getDetails() getter
}