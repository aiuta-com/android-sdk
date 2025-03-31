package com.aiuta.fashionsdk.tryon.compose.domain.internal.interactor.generated.operations

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

@OptIn(ExperimentalContracts::class)
internal fun GeneratedOperationInteractor.cleanLoadingUploads(cleanAction: () -> Unit): Boolean {
    contract {
        returns(true) implies (this@cleanLoadingUploads is LocalGeneratedOperationInteractor)
    }

    if (this is LocalGeneratedOperationInteractor) {
        cleanAction()
    }

    return this is LocalGeneratedOperationInteractor
}
