package com.chapo.navigation.destination

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

abstract class FragmentDestination(
    @IdRes val containerId: Int,
    private val fragmentManager: FragmentManager,
) : Destination {

    override fun moveToDestination() {
        fragmentManager.beginTransaction().run {
            replace(containerId, createFragment())
            commit()
        }
    }

    abstract fun createFragment(): Fragment
}