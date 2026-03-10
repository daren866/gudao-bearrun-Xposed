package com.ternloli.xposed

import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedBridge
import de.robv.android.xposed.XposedHelpers
import de.robv.android.xposed.callbacks.XC_LoadPackage

class MainHook : IXposedHookLoadPackage {

    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam) {
        if (lpparam.packageName != TARGET_PACKAGE) return

        XposedBridge.log("[$MODULE_TAG] Loaded for package: ${lpparam.packageName}")

        hookTargetMethod(lpparam.classLoader)
    }

    private fun hookTargetMethod(classLoader: ClassLoader) {
        try {
            XposedHelpers.findAndHookMethod(
                "android.app.Activity",
                classLoader,
                "onCreate",
                android.os.Bundle::class.java,
                object : XC_MethodHook() {
                    override fun beforeHookedMethod(param: MethodHookParam) {
                        XposedBridge.log("[$MODULE_TAG] Activity.onCreate called")
                    }

                    override fun afterHookedMethod(param: MethodHookParam) {
                        // Post-hook logic here
                    }
                }
            )
        } catch (e: Exception) {
            XposedBridge.log("[$MODULE_TAG] Hook failed: ${e.message}")
        }
    }

    companion object {
        const val MODULE_TAG = "XposedTemplate"
        const val TARGET_PACKAGE = "com.example.target"
    }
}
