package com.yazantarifi.radio.useCases

import com.yazantarifi.radio.base.api.SopifyRequestListener
import com.yazantarifi.radio.RadioApplicationMessages
import com.yazantarifi.radio.api.SpotifyApiHeadersBuilder
import com.yazantarifi.radio.api.home.SpotifyGetCategoriesApiRequest
import com.yazantarifi.radio.base.useCases.useCasesTypes.SopifyUseCase
import com.yazantarifi.radio.composable.models.items.RadioCategoryItem
import com.yazantarifi.radio.models.SpotifyCategoriesResponse

class GetCategoriesUseCase constructor(): SopifyUseCase<String, List<RadioCategoryItem>>() {

    private val apiClient: SpotifyGetCategoriesApiRequest by lazy {
        SpotifyGetCategoriesApiRequest(true)
    }

    override fun isConstraintsSupported(): Boolean {
        return false
    }

    override fun clear() {
        super.clear()
        apiClient.clear()
    }

    override suspend fun build(requestValue: String) {
        if (apiClient.isRequestListenerAttachNeeded()) {
            apiClient.addHttpClient(getHttpClientInstance())
            apiClient.addRequestListener(object :
                SopifyRequestListener<SpotifyCategoriesResponse> {
                override fun onSuccess(responseValue: SpotifyCategoriesResponse) {
                    val categories = ArrayList<RadioCategoryItem>()
                    responseValue.categories?.items?.let {
                        categories.addAll(it.map {
                            var imageUrl: String = ""
                            it.icons?.get(0)?.let {
                                imageUrl = it.url ?: ""
                            }

                            RadioCategoryItem(
                                it.id ?: "",
                                it.name ?: "",
                                imageUrl,
                                RadioApplicationMessages.getMessage("loading_image")
                            )
                        })
                    }

                    onSubmitLoadingState(false)
                    onSubmitSuccessState(categories)
                }

                override fun onError(error: Throwable) {
                    // Will Not Show the Item
                }
            })
        }

        onSubmitLoadingState(true)
        apiClient.executeRequest(Unit, SpotifyApiHeadersBuilder.getApplicationBearerTokenHeaders(requestValue))
    }

}
