package com.yazantarifi.radio.base.useCases

abstract class SopifyUseCaseAlias<T> {

    private var internalInstance: T? = null

    fun addInstance(instance: T) {
        this.internalInstance = instance
    }

    fun getInstance(): T? {
        return internalInstance
    }

}
