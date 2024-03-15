package com.teamproject.wounddetection.viewmodel


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teamproject.wounddetection.data.api.PatientApi
import com.teamproject.wounddetection.data.model.Patient
import com.teamproject.wounddetection.utils.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException

class PatientDataViewModel(
    private val api: PatientApi,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    companion object {
        private const val TAG = "PatientDataViewModel"
    }

    private var _patient = MutableLiveData<Resource<Patient>?>()
    val patient: LiveData<Resource<Patient>?> = _patient

    fun checkPatient(code: String) {
        viewModelScope.launch(dispatcher) {
            try {
                _patient.postValue(Resource.loading(null))
                val patient = api.getPatient(code)
                _patient.postValue(Resource.success(patient))
            } catch (e: Throwable) {
                Log.d(TAG, "checkPatient: " + e.message)
                if (e is HttpException) {
                    if (e.code() == 404) {
                        _patient.postValue(Resource.error("Patient does not exist", null))
                    } else {
                        _patient.postValue(
                            Resource.error(
                                "An error occurred while getting patient data",
                                null
                            )
                        )
                    }
                } else {
                    _patient.postValue(
                        Resource.error(
                            "An error occurred while getting patient data",
                            null
                        )
                    )
                }
            }
        }
    }

    fun resetPatient() {
        _patient.postValue(null)
    }

}