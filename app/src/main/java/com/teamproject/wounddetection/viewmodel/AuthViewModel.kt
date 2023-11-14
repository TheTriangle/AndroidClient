package com.teamproject.wounddetection.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.teamproject.wounddetection.App
import com.teamproject.wounddetection.data.api.UserApi
import com.teamproject.wounddetection.data.model.User
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException

const val TAG = "AuthViewModel"

class AuthViewModel(
    private val api: UserApi,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {
    companion object  {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                val application = checkNotNull(extras[APPLICATION_KEY])
                return AuthViewModel(
                    (application as App).retrofit.create(UserApi::class.java)
                ) as T
            }
        }
    }

    fun makeSignInRequest(login: String, password: String) {
        viewModelScope.launch(dispatcher) {
            try {
                api.login(User(login, password))
            } catch (e: Throwable) {
                if (e is HttpException) {
                    Log.d(TAG, "makeSignInRequest: " + (e.response()?.body() ?: e.response()))
                } else {
                    Log.d(TAG, "makeSignInRequest: " + e.message)
                }
            }
        }
    }
}
