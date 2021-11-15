package com.gperre.jopit.modalmanager.modals

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.gperre.jopit.modalmanager.databinding.ActivityModalThreeBinding
import com.gperre.jopit.modalmanager.manager.ModalResult
import com.gperre.jopit.modalmanager.manager.ModalView
import kotlinx.coroutines.CancellableContinuation
import kotlin.coroutines.resume

class ModalThree(
    override val continuation: CancellableContinuation<ModalResult>
) : DialogFragment(), ModalView {

    private lateinit var binding: ActivityModalThreeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ActivityModalThreeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cancelListener()
    }

    private fun cancelListener() {
        binding.cancelButton.setOnClickListener {
            dismissModal()
        }
    }

    override fun show(fragmentManager: FragmentManager) {
        show(fragmentManager, "")
    }

    override fun dismissModal() {
        dismiss()
        continuation.resume(ModalResult.Dismissed)
    }
}
