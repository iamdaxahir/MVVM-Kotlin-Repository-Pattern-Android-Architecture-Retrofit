package com.dashrath.kotlinrepositorypattern.base

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import org.koin.core.KoinComponent
import org.koin.core.inject

/**
 * Used for define common class type of all view models.
 */
abstract class BaseVM : ViewModel(), KoinComponent {

    private val pre: SharedPreferences by inject()

}