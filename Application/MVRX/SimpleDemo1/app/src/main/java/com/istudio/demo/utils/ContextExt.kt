package com.istudio.demo.utils

import android.content.Context


/** ************************ Determine if the device is a tablet or a phone ************************ **/
private const val COMPACT_DEVICE_WIDTH_SIZE = 600
val Context.isTablet: Boolean get() = resources.configuration.smallestScreenWidthDp >= COMPACT_DEVICE_WIDTH_SIZE
/** ************************ Determine if the device is a tablet or a phone ************************ **/
