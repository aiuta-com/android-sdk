package com.aiuta.fashionsdk.tryon.compose.domain.internal.share.utls

import platform.UIKit.UIApplication
import platform.UIKit.UIDevice
import platform.UIKit.UISceneActivationStateForegroundActive
import platform.UIKit.UIUserInterfaceIdiomPad
import platform.UIKit.UIWindow
import platform.UIKit.UIWindowScene

internal val UIApplication.firstKeyWindow: UIWindow?
    get() = this.connectedScenes
        .filterIsInstance<UIWindowScene>()
        .firstOrNull { it.activationState == UISceneActivationStateForegroundActive }
        ?.keyWindow

internal fun isIpad(): Boolean {
    val device = UIDevice.currentDevice
    return device.userInterfaceIdiom == UIUserInterfaceIdiomPad
}
