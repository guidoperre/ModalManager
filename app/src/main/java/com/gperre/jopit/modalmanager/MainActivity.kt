package com.gperre.jopit.modalmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gperre.jopit.modalmanager.databinding.ActivityMainBinding
import com.gperre.jopit.modalmanager.manager.ModalManager
import com.gperre.jopit.modalmanager.manager.ModalManagerState
import com.gperre.jopit.modalmanager.manager.ModalPriority
import com.gperre.jopit.modalmanager.modals.ModalOne
import com.gperre.jopit.modalmanager.modals.ModalThree
import com.gperre.jopit.modalmanager.modals.ModalTwo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var modalManager: ModalManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setModalListener()
    }

    private fun setModalListener() {
        modalManager = ModalManager(this, supportFragmentManager)
        binding.apply {
            modal1.setOnClickListener {
                modalManager.registerModal(ModalOne::class.java, ModalPriority.LOW)
            }

            modal2.setOnClickListener {
                modalManager.registerModal(ModalOne::class.java, ModalPriority.LOW)
                modalManager.registerModal(ModalTwo::class.java, ModalPriority.MEDIUM)
            }

            modal3.setOnClickListener {
                modalManager.registerModal(ModalOne::class.java, ModalPriority.LOW)
                modalManager.registerModal(ModalTwo::class.java, ModalPriority.MEDIUM)
                modalManager.registerModal(ModalThree::class.java, ModalPriority.HIGH)
            }

            free.setOnClickListener {
                modalManager.updateState(ModalManagerState.AVAILABLE)
            }

            stop.setOnClickListener {
                modalManager.updateState(ModalManagerState.UNAVAILABLE)
            }
        }
    }
}
