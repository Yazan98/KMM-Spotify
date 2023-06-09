package com.yazantarifi.radio.base.useCases.constraints

abstract class SopifyConstraint<RequestValue> constructor(
    requestValue: RequestValue
): SopifyType {

    fun execute(onError: (Throwable) -> Unit) {
        if (!isConstraintValid()) {
            onError(getInvalidConstraintException())
        }
    }

}

interface SopifyType {

    fun isConstraintValid(): Boolean

    fun getInvalidConstraintException(): Throwable

}
