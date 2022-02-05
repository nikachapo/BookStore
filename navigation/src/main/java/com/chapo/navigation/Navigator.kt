package com.chapo.navigation

import androidx.activity.addCallback
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity

abstract class Navigator(protected val activity: AppCompatActivity) {

    private var destinationChangedListener: DestinationChangedListener? = null

    private var currentMenuItemId: Int = -1

    init {
        activity.onBackPressedDispatcher.addCallback {
            navigateBack()
        }
    }

    fun setOnDestinationChangedListener(destinationChangedListener: DestinationChangedListener) {
        this.destinationChangedListener = destinationChangedListener
    }

    protected var currentDestination: Destination = NoDestination
        set(value) {
            field = value
            destinationChangedListener?.onDestinationChanged(currentDestination)
        }

    abstract fun navigateBack()

    abstract fun navigateToStartingDestination()

    protected fun onSelectMenuItem(@IdRes itemId: Int): Boolean {
        return if (itemId == currentMenuItemId) {
            false
        } else {
            currentMenuItemId = itemId
            true
        }
    }

    fun navigateTo(destination: Destination) {
        if (destination != currentDestination) {
            currentDestination = destination
            destination.moveToDestination()
        }
    }

    open fun selectMenuItem(itemId: Int): Boolean = false
}