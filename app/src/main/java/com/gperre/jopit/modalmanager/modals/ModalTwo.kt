package com.gperre.jopit.modalmanager.modals

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gperre.jopit.modalmanager.databinding.ActivityModalTwoBinding
import com.gperre.jopit.modalmanager.manager.ModalFragment
import com.gperre.jopit.modalmanager.manager.ModalPriority
import com.gperre.jopit.modalmanager.manager.ModalResult
import kotlinx.coroutines.CancellableContinuation
import kotlin.coroutines.resume

class ModalTwo(
    private val continuation: CancellableContinuation<ModalResult>
) : ModalFragment(continuation) {

    private lateinit var binding: ActivityModalTwoBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ActivityModalTwoBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cancelListener()
    }

    private fun cancelListener() {
        binding.cancelButton.setOnClickListener {
            dismiss()
            continuation.resume(ModalResult.Dismissed)
        }
    }


}
