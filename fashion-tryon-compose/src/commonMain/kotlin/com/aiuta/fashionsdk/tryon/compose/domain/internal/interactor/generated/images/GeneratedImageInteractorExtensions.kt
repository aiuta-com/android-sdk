package com.aiuta.fashionsdk.tryon.compose.domain.internal.interactor.generated.images

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

@OptIn(ExperimentalContracts::class)
internal fun GeneratedImageInteractor.cleanLoadingGenerations(cleanAction: () -> Unit): Boolean {
    contract {
        returns(true) implies (this@cleanLoadingGenerations is LocalGeneratedImageInteractor)
    }

    if (this is LocalGeneratedImageInteractor) {
        cleanAction()
    }

    return this is LocalGeneratedImageInteractor
}
