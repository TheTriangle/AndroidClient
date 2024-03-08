package com.teamproject.wounddetection.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.teamproject.wounddetection.MainCoroutineScopeRule
import com.teamproject.wounddetection.data.api.PatientApi
import com.teamproject.wounddetection.data.model.Case
import com.teamproject.wounddetection.data.model.Doctor
import com.teamproject.wounddetection.data.model.Patient
import com.teamproject.wounddetection.data.model.WoundReport
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mockito

class PatientDataViewModelTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val coroutineScope =  MainCoroutineScopeRule()

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    lateinit var viewModel: PatientDataViewModel

    lateinit var api: PatientApi

    val patient = Patient(
        id = 1, name = "Name", email = "qwe@zxc.ru",
        listOf(
            Case(
                1,
                Doctor(1, "doctor@email.ru", "Doctor name 1", "Surgeon"),
                listOf(
                    WoundReport(
                        1,
                        "deep",
                        "class1",
                        "type1",
                        11.0,
                        11.0,
                        11.0,
                        "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse1.mm.bing.net%2Fth%3Fid%3DOIP.X3mfeI7lW-x1NvHx8AZwAAHaHa%26pid%3DApi&f=1&ipt=0833a27a63ba4366a527918ae257dda887c9f96bb956a09cca056a979860368f&ipo=images"
                    ),
                    WoundReport(
                        2,
                        "not deep",
                        "class2",
                        "type2",
                        12.0,
                        12.0,
                        12.0,
                        "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fwww.petbarn.com.au%2Fpetspot%2Fapp%2Fuploads%2F2019%2F01%2Fkitten-000017380158_Smaller.jpg&f=1&nofb=1&ipt=ed37c9a91356b71a8866925647ee12920ed16f623499c6597c8f7f42eb3529c9&ipo=images"
                    ),
                ),
                "11-01-2024"
            ),
            Case(
                2,
                Doctor(2, "doctor2@email.ru", "Doctor name 2", "Surgeon"),
                listOf(
                    WoundReport(
                        1,
                        "deep",
                        "class1",
                        "type1",
                        11.0,
                        11.0,
                        11.0,
                        "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse1.mm.bing.net%2Fth%3Fid%3DOIP.X3mfeI7lW-x1NvHx8AZwAAHaHa%26pid%3DApi&f=1&ipt=0833a27a63ba4366a527918ae257dda887c9f96bb956a09cca056a979860368f&ipo=images"
                    ),
                    WoundReport(
                        2,
                        "not deep",
                        "class2",
                        "type2",
                        12.0,
                        12.0,
                        12.0,
                        "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fwww.petbarn.com.au%2Fpetspot%2Fapp%2Fuploads%2F2019%2F01%2Fkitten-000017380158_Smaller.jpg&f=1&nofb=1&ipt=ed37c9a91356b71a8866925647ee12920ed16f623499c6597c8f7f42eb3529c9&ipo=images"
                    ),
                ),
                "11-01-2024"
            )
        )
    )

    @Before
    fun setup() {
        api = Mockito.mock(PatientApi::class.java)
        viewModel = PatientDataViewModel(api, Dispatchers.Main)
    }

    @Test
    fun checkPatientSuccess() = runTest {
        Mockito.`when`(api.getPatient("1")).thenReturn(patient)
        viewModel.checkPatient("1")
        val expected = Patient(
            id = 1, name = "Name", email = "qwe@zxc.ru",
            listOf(
                Case(
                    1,
                    Doctor(1, "doctor@email.ru", "Doctor name 1", "Surgeon"),
                    listOf(
                        WoundReport(
                            1,
                            "deep",
                            "class1",
                            "type1",
                            11.0,
                            11.0,
                            11.0,
                            "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse1.mm.bing.net%2Fth%3Fid%3DOIP.X3mfeI7lW-x1NvHx8AZwAAHaHa%26pid%3DApi&f=1&ipt=0833a27a63ba4366a527918ae257dda887c9f96bb956a09cca056a979860368f&ipo=images"
                        ),
                        WoundReport(
                            2,
                            "not deep",
                            "class2",
                            "type2",
                            12.0,
                            12.0,
                            12.0,
                            "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fwww.petbarn.com.au%2Fpetspot%2Fapp%2Fuploads%2F2019%2F01%2Fkitten-000017380158_Smaller.jpg&f=1&nofb=1&ipt=ed37c9a91356b71a8866925647ee12920ed16f623499c6597c8f7f42eb3529c9&ipo=images"
                        ),
                    ),
                    "11-01-2024"
                ),
                Case(
                    2,
                    Doctor(2, "doctor2@email.ru", "Doctor name 2", "Surgeon"),
                    listOf(
                        WoundReport(
                            1,
                            "deep",
                            "class1",
                            "type1",
                            11.0,
                            11.0,
                            11.0,
                            "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse1.mm.bing.net%2Fth%3Fid%3DOIP.X3mfeI7lW-x1NvHx8AZwAAHaHa%26pid%3DApi&f=1&ipt=0833a27a63ba4366a527918ae257dda887c9f96bb956a09cca056a979860368f&ipo=images"
                        ),
                        WoundReport(
                            2,
                            "not deep",
                            "class2",
                            "type2",
                            12.0,
                            12.0,
                            12.0,
                            "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fwww.petbarn.com.au%2Fpetspot%2Fapp%2Fuploads%2F2019%2F01%2Fkitten-000017380158_Smaller.jpg&f=1&nofb=1&ipt=ed37c9a91356b71a8866925647ee12920ed16f623499c6597c8f7f42eb3529c9&ipo=images"
                        ),
                    ),
                    "11-01-2024"
                )
            )
        )
        assertEquals(expected, viewModel.patient.value?.data)
    }

    @Test
    fun checkPatientError() = runTest {
        Mockito.`when`(api.getPatient("1")).thenThrow(RuntimeException("My exception"))
        viewModel.checkPatient("1")
        println(viewModel.patient.value)
        assertEquals("An error occurred while getting patient data", viewModel.patient.value?.message)
    }
}