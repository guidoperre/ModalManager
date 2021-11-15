package com.gperre.jopit.modalmanager.manager

import androidx.fragment.app.DialogFragment
import kotlinx.coroutines.CancellableContinuation

open class ModalFragment(
    private val continuation: CancellableContinuation<ModalResult>
): DialogFragment()