package com.magen.play02

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import com.arthenica.ffmpegkit.FFmpegKit

class MagenPlayApp : Application() {
    override fun onCreate() {
        super.onCreate()
        FFmpegKit.initialize(this)
    }
}