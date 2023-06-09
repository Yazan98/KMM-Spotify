package com.yazantarifi.radio.base.api

interface SopifyRequestListener<ResponseValue> {

    fun onSuccess(responseValue: ResponseValue)

    fun onError(error: Throwable)

}
