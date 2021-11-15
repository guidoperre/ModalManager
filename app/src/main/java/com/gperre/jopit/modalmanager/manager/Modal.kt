package com.gperre.jopit.modalmanager.manager

import kotlinx.coroutines.CancellableContinuation

data class Modal(
    val clazz: Class<out ModalFragment>,
    val priority: ModalPriority
)

interface ModalView {
    val continuation: CancellableContinuation<ModalResult>

    fun dismissModal()
}
