package com.yazantarifi.radio

class SopifyUnknownException constructor(cause: Throwable): Throwable(cause)
open class SopifyException constructor(override val message: String?): Throwable(message)

class SopifyNoInternetException: SopifyException("No Internet Connection")

class SopifyEmptyStringException constructor(
    private val key: String
): SopifyException("String Empty : $key")

class SopifyConstraintsException constructor(
    val exceptions: ArrayList<Throwable>,
    override val message: String
): SopifyException(message)
