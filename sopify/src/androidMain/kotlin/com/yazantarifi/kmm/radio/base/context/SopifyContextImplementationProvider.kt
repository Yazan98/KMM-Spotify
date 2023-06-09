package com.yazantarifi.kmm.radio.base.context

import android.content.Context

actual fun SopifyContext.putInt(key: String, value: Int) {
    getSpEditor().putInt(key, value).apply()
}

actual fun SopifyContext.getInt(key: String, default: Int): Int {
    return  getSp().getInt(key, default )
}

actual fun SopifyContext.putString(key: String, value: String) {
    getSpEditor().putString(key, value).apply()
}

actual fun SopifyContext.getString(key: String): String? {
    return  getSp().getString(key, null)
}

actual fun SopifyContext.putBool(key: String, value: Boolean) {
    getSpEditor().putBoolean(key, value).apply()
}

actual fun SopifyContext.getBool(key: String, default: Boolean): Boolean {
    return getSp().getBoolean(key, default)
}

private fun SopifyContext.getSp() = getSharedPreferences(SopifyStorageKeys.STORAGE_MAIN_KEY, Context.MODE_PRIVATE)

private fun SopifyContext.getSpEditor() = getSp().edit()
