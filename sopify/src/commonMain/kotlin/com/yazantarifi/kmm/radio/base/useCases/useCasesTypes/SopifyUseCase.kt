package com.yazantarifi.kmm.radio.base.useCases.useCasesTypes

import com.yazantarifi.kmm.radio.SopifyConstraintsException
import com.yazantarifi.kmm.radio.base.useCases.SopifyErrorState
import com.yazantarifi.kmm.radio.base.useCases.SopifyLoadingState
import com.yazantarifi.kmm.radio.base.useCases.SopifyScope
import com.yazantarifi.kmm.radio.base.useCases.SopifySuccessState
import com.yazantarifi.kmm.radio.base.useCases.SopifyUseCaseListener
import com.yazantarifi.kmm.radio.base.useCases.SopifyUseCaseType
import com.yazantarifi.kmm.radio.base.useCases.constraints.SopifyType
import io.ktor.client.HttpClient
import io.ktor.utils.io.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

abstract class SopifyUseCase<RequestValue, ResponseValue : Any>: CoroutineScope, SopifyUseCaseType {

    private var httpClient: HttpClient? = null
    private var listener: SopifyUseCaseListener? = null
    private val coroutineScope = SopifyScope().getCoroutineDispatcher()
    override val coroutineContext: CoroutineContext get() = currentJob + Dispatchers.Default
    private val currentJob: Job by lazy {
        SupervisorJob()
    }

    fun execute(requestValue: RequestValue, listener: SopifyUseCaseListener) {
        this.listener = listener
        if (!isConstraintsSupported()) {
            executeUseCase(requestValue)
            return
        }

        val constraintValidationErrors = arrayListOf<Throwable>()
        getSupportedConstraints(requestValue).forEach {
            if (!it.isConstraintValid()) {
                constraintValidationErrors.add(it.getInvalidConstraintException())
            }
        }

        if (constraintValidationErrors.isNotEmpty()) {
            onSubmitExceptionState(SopifyConstraintsException(constraintValidationErrors, "UseCases Constraints Not Match, Invalid"))
        } else {
            executeUseCase(requestValue)
        }
    }

    fun addHttpClient(httpClient: HttpClient) {
        this.httpClient = httpClient
    }

    private fun executeUseCase(requestValue: RequestValue) {
        launch(coroutineScope) {
            build(requestValue)
        }
    }

    open fun getSupportedConstraints(requestValue: RequestValue): ArrayList<SopifyType> {
        return arrayListOf()
    }

    abstract fun isConstraintsSupported(): Boolean

    abstract suspend fun build(requestValue: RequestValue)

    protected fun onSubmitLoadingState(isLoading: Boolean) {
        launch(coroutineScope) {
            listener?.onStateUpdated(SopifyLoadingState(isLoading))
        }
    }

    protected fun onSubmitExceptionState(throwable: Throwable) {
        launch(coroutineScope) {
            listener?.onStateUpdated(SopifyErrorState(throwable))
        }
    }

    protected fun onSubmitSuccessState(response: ResponseValue) {
        launch(coroutineScope) {
            listener?.onStateUpdated(SopifySuccessState(response))
        }
    }

    protected fun getHttpClientInstance(): HttpClient? {
        return this.httpClient
    }

    override fun clear() {
        this.cancel(CancellationException("This UseCase has Been Canceled"))
        this.listener = null
        this.httpClient = null
    }

    override fun clear(message: String) {
        this.cancel(CancellationException(message))
        this.listener = null
    }

}