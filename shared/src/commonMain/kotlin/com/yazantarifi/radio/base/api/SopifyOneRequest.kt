package com.yazantarifi.radio.base.api

abstract class SopifyOneRequest<RequestBody, ResponseValue>: SopifyRequestManager() {

    protected var requestListener: SopifyRequestListener<ResponseValue>? = null

    fun addRequestListener(requestListener: SopifyRequestListener<ResponseValue>) {
        this.requestListener = requestListener
    }

    fun isRequestListenerAttachNeeded(): Boolean {
        return this.requestListener == null
    }

    override fun clear() {
        super.clear()
        requestListener = null
    }

    protected abstract fun getRequestUrl(): String

    abstract suspend fun executeRequest(requestBody: RequestBody, headers: List<Pair<String, String>>)

}
