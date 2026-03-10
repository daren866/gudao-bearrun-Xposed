package com.ternloli.xposed

import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedBridge
import de.robv.android.xposed.XposedHelpers

object HookUtils {
    fun hookMethod(
        className: String,
        classLoader: ClassLoader,
        methodName: String,
        vararg paramTypes: Any,
        before: ((XC_MethodHook.MethodHookParam) -> Unit)? = null,
        after: ((XC_MethodHook.MethodHookParam) -> Unit)? = null
    ) {
        try {
            XposedHelpers.findAndHookMethod(
                className, classLoader, methodName, *paramTypes,
                object : XC_MethodHook() {
                    override fun beforeHookedMethod(param: MethodHookParam) {
                        before?.invoke(param)
                    }
                    override fun afterHookedMethod(param: MethodHookParam) {
                        after?.invoke(param)
                    }
                }
            )
        } catch (e: Throwable) {
            XposedBridge.log("HookUtils: Failed to hook $className.$methodName: ${e.message}")
        }
    }

    fun setResult(param: XC_MethodHook.MethodHookParam, result: Any?) {
        param.result = result
    }
}
