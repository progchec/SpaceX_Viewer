package com.example.spacex_viewer

import android.app.Instrumentation
import android.content.Context
import org.junit.Test
import org.junit.Assert.*
import org.junit.runner.manipulation.Ordering

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun launchResultsAreCorrect() {
        var launch: Launch = Launch("test_name", "test_launch_date",
        "test_details", "test_mission_patch")
        assertEquals("test_name", launch.getMissionName())
        assertEquals("test_launch_date", launch.getLaunchDateUTC())
        assertEquals("test_details", launch.getDetails())
        assertEquals("test_mission_patch", launch.getMissionPatchSmall())
    }
}