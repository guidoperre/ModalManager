package com.gperre.jopit.modalmanager.manager

import java.util.*

enum class ModalPriority {
    HIGH,
    MEDIUM,
    LOW;

    companion object {
        fun fromString(value: String): ModalPriority = valueOf(value.uppercase(Locale.getDefault()))
    }

    internal val value get() = getModalPriority()

    private fun getModalPriority(): Int {
        return when (this) {
            HIGH -> 75
            MEDIUM -> 50
            LOW -> 25
        }
    }
}
