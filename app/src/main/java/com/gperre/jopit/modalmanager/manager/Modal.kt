package com.gperre.jopit.modalmanager.manager

import androidx.fragment.app.FragmentManager
import kotlinx.coroutines.CancellableContinuation

data class Modal(
    val clazz: Class<out ModalView>,
    val priority: ModalPriority
)

interface ModalView {
    val continuation: CancellableContinuation<ModalResult>

    fun show(fragmentManager: FragmentManager)

    fun dismissModal()
}
