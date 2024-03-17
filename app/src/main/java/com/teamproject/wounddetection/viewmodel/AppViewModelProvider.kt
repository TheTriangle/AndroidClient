package com.teamproject.wounddetection.viewmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.teamproject.wounddetection.App
import com.teamproject.wounddetection.data.api.PatientApi
import com.teamproject.wounddetection.data.api.UserApi

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            AuthViewModel(
                app().retrofit.create(UserApi::class.java), application = app()
            )
        }

        initializer {
            PatientDataViewModel(
                app().retrofit.create(PatientApi::class.java)
            )
        }
        initializer {
            CaseSelectionPopupViewModel(
                app().retrofit.create(PatientApi::class.java)
            )
        }
    }
}

fun CreationExtras.app(): App =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as App)
