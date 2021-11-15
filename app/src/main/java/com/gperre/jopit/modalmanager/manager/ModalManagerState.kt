package com.gperre.jopit.modalmanager.manager

import java.util.*

enum class ModalManagerState {
    AVAILABLE,
    UNAVAILABLE;

    companion object {
        fun fromString(value: String): ModalManagerState = valueOf(value.uppercase(Locale.getDefault()))
    }
}
