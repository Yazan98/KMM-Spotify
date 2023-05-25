package com.yazantarifi.kmm.sopy.api

interface SopifyRequestListener<ResponseValue> {

    fun onSuccess(responseValue: ResponseValue)

    fun onError(error: Throwable)

}
