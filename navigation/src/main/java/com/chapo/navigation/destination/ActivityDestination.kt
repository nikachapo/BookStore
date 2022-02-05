package com.chapo.navigation.destination

import android.content.Context
import android.content.Intent

abstract class ActivityDestination(
    private val context: Context,
) : Destination {

    abstract fun generateIntent(): Intent

    override fun moveToDestination() {
        context.startActivity(generateIntent())
    }

}