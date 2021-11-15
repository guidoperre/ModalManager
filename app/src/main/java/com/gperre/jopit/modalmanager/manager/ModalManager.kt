package com.gperre.jopit.modalmanager.manager

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.*
import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.util.*

class ModalManager(
    owner: LifecycleOwner,
    private val fragmentManager: FragmentManager
) {
    private val _state = MutableLiveData(ModalManagerState.UNAVAILABLE)
    private val state: LiveData<ModalManagerState>
        get() = _state

    // Mutual exclusion flag
    private val mutex = Mutex()

    // Priority queue that give priority based on ModalPriority
    private val compareByPriority: Comparator<Modal> = compareBy { -it.priority.value }
    private val queue = PriorityQueue(INITIAL_CAPACITY, compareByPriority)

    init {
        stateObserver(owner)
    }

    fun registerModal(clazz: Class<out ModalView>, priority: ModalPriority) {
        // Add the modal into the queue
        queue.add(Modal(clazz, priority))
        // Launch a next modal request
        CoroutineScope(Dispatchers.IO).launch { showNext() }
    }

    private suspend fun showNext(): ModalResult = mutex.withLock {
        // Return a ModalResult from coroutine cancellation
        return suspendCancellableCoroutine { continuation ->
            queue.peek()?.let {
                // Show modal
                it.clazz.getConstructor(
                    CancellableContinuation::class.java
                ).newInstance(continuation)?.show(fragmentManager)
                // Remove modal from the queue
                removeModal(it)
            }
        }
    }

    private fun removeModal(modal: Modal) {
        queue.remove(modal)
    }

    fun updateState(state: ModalManagerState) {
        _state.value = state
    }

    // Observe the modal manager state
    private fun stateObserver(owner: LifecycleOwner) {
        state.observe(owner, {
            if (it == ModalManagerState.AVAILABLE) {
                mutex.unlock()
            } else if (it == ModalManagerState.UNAVAILABLE) {
                CoroutineScope(Dispatchers.IO).launch {
                    mutex.lock()
                }
            }
        })
    }

    companion object {
        private const val INITIAL_CAPACITY = 100
    }
}
