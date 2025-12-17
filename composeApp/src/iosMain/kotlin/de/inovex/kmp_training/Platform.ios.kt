package de.inovex.kmp_training

import platform.UIKit.UIDevice

class IOSPlatform {
    val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

fun getPlatform() = IOSPlatform()
