package com.teamproject.wounddetection.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.teamproject.wounddetection.data.api.UserApi
import com.teamproject.wounddetection.data.model.User
import com.teamproject.wounddetection.utils.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException



class AuthViewModel(
    private val api: UserApi,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO, application: Application
) : AndroidViewModel(application) {

    companion object  {
        private const val TAG = "AuthViewModel"
    }

    private val _auth = MutableLiveData<Resource<String>>()
    val auth: LiveData<Resource<String>> = _auth

    fun makeSignInRequest(login: String, password: String) {
        viewModelScope.launch(dispatcher) {
            try {
                _auth.postValue(Resource.loading(null))
                val authResponse = api.login(User(login, password, password))

                val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
                val preferences = EncryptedSharedPreferences.create(
                    "AUTH",
                    masterKeyAlias,
                    getApplication(),
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
                )
                val editor = preferences.edit()
                editor.putString("token", authResponse.token)
                editor.apply()

                _auth.postValue(Resource.success(null))
            } catch (e: Throwable) {
                if (e is HttpException) {
                    Log.d(TAG, "makeSignInRequest: " + (e.response()?.body() ?: e.response()))
                    _auth.postValue(Resource.error(e.message(), null))
                } else {
                    Log.d(TAG, "makeSignInRequest: " + e.message)
                    _auth.postValue(Resource.error("An error occurred while logging in", null))
                }
            }
        }
    }
}
