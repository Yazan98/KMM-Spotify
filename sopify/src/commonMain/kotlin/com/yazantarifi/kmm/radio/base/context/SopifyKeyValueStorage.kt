package com.yazantarifi.kmm.radio.base.context

expect fun SopifyContext.putInt(key: String, value: Int)

expect fun SopifyContext.getInt(key: String, default: Int): Int

expect fun SopifyContext.putString(key: String, value: String)

expect fun SopifyContext.getString(key: String) : String?

expect fun SopifyContext.putBool(key: String, value: Boolean)

expect fun SopifyContext.getBool(key: String, default: Boolean): Boolean