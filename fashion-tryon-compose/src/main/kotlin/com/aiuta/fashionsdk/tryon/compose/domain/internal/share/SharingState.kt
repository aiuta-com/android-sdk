package com.aiuta.fashionsdk.tryon.compose.domain.internal.share

internal sealed interface SharingState {
    /**
     * Sharing is not started yet
     **/
    object Idle : SharingState

    /**
     * Image successfully downloaded and shared dialog has been showed
     **/
    object Success : SharingState

    /**
     * Image downloading in progress
     **/
    object Loading : SharingState

    /**
     * Link didn't downloaded in cause of error
     **/
    data class Error(val message: String?) : SharingState
}
