package com.teamproject.wounddetection

import com.teamproject.wounddetection.data.api.PatientApi
import com.teamproject.wounddetection.data.api.UserApi
import com.teamproject.wounddetection.data.model.Auth
import com.teamproject.wounddetection.data.model.Case
import com.teamproject.wounddetection.data.model.Doctor
import com.teamproject.wounddetection.data.model.Patient
import com.teamproject.wounddetection.data.model.User
import com.teamproject.wounddetection.data.model.WoundReport
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import retrofit2.Retrofit

class ApplicationClassTest : App() {
    override lateinit var retrofit: Retrofit

    override fun onCreate() {
        super.onCreate()
        val user = User("", "", "")

        val patient = Patient(
            id = 1, name = "Name", mail = "qwe@zxc.ru",
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
                            "11",
                            "11",
                            "11",
                            "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse1.mm.bing.net%2Fth%3Fid%3DOIP.X3mfeI7lW-x1NvHx8AZwAAHaHa%26pid%3DApi&f=1&ipt=0833a27a63ba4366a527918ae257dda887c9f96bb956a09cca056a979860368f&ipo=images"
                        ),
                        WoundReport(
                            2,
                            "not deep",
                            "class2",
                            "type2",
                            "12",
                            "12",
                            "12",
                            "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fwww.petbarn.com.au%2Fpetspot%2Fapp%2Fuploads%2F2019%2F01%2Fkitten-000017380158_Smaller.jpg&f=1&nofb=1&ipt=ed37c9a91356b71a8866925647ee12920ed16f623499c6597c8f7f42eb3529c9&ipo=images"
                        ),
                    ),
                    "21.03.2024"
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
                            "11",
                            "11",
                            "11",
                            "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse1.mm.bing.net%2Fth%3Fid%3DOIP.X3mfeI7lW-x1NvHx8AZwAAHaHa%26pid%3DApi&f=1&ipt=0833a27a63ba4366a527918ae257dda887c9f96bb956a09cca056a979860368f&ipo=images"
                        ),
                        WoundReport(
                            2,
                            "not deep",
                            "class2",
                            "type2",
                            "12",
                            "12",
                            "12",
                            "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fwww.petbarn.com.au%2Fpetspot%2Fapp%2Fuploads%2F2019%2F01%2Fkitten-000017380158_Smaller.jpg&f=1&nofb=1&ipt=ed37c9a91356b71a8866925647ee12920ed16f623499c6597c8f7f42eb3529c9&ipo=images"
                        ),
                    ),
                    "11-01-2024"
                )
            )
        )

        val client = mockk<Retrofit>()
        val userApi = mockk<UserApi>()
        val patientApi = mockk<PatientApi>()

        every { client.create(UserApi::class.java) } returns userApi
        every { client.create(PatientApi::class.java) } returns patientApi
        coEvery { patientApi.getPatient("1") } returns patient
        coEvery { userApi.login(user) } returns Auth("", "")
        retrofit = client
    }

//    by lazy {
//        val user = User("", "", "")
//
//        val patient = Patient(
//            id = 1, name = "Name", mail = "qwe@zxc.ru",
//            listOf(
//                Case(
//                    1,
//                    Doctor(1, "doctor@email.ru", "Doctor name 1", "Surgeon"),
//                    listOf(
//                        WoundReport(
//                            1,
//                            "deep",
//                            "class1",
//                            "type1",
//                            "11",
//                            "11",
//                            "11",
//                            "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse1.mm.bing.net%2Fth%3Fid%3DOIP.X3mfeI7lW-x1NvHx8AZwAAHaHa%26pid%3DApi&f=1&ipt=0833a27a63ba4366a527918ae257dda887c9f96bb956a09cca056a979860368f&ipo=images"
//                        ),
//                        WoundReport(
//                            2,
//                            "not deep",
//                            "class2",
//                            "type2",
//                            "12",
//                            "12",
//                            "12",
//                            "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fwww.petbarn.com.au%2Fpetspot%2Fapp%2Fuploads%2F2019%2F01%2Fkitten-000017380158_Smaller.jpg&f=1&nofb=1&ipt=ed37c9a91356b71a8866925647ee12920ed16f623499c6597c8f7f42eb3529c9&ipo=images"
//                        ),
//                    ),
//                    "21.03.2024"
//                ),
//                Case(
//                    2,
//                    Doctor(2, "doctor2@email.ru", "Doctor name 2", "Surgeon"),
//                    listOf(
//                        WoundReport(
//                            1,
//                            "deep",
//                            "class1",
//                            "type1",
//                            "11",
//                            "11",
//                            "11",
//                            "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse1.mm.bing.net%2Fth%3Fid%3DOIP.X3mfeI7lW-x1NvHx8AZwAAHaHa%26pid%3DApi&f=1&ipt=0833a27a63ba4366a527918ae257dda887c9f96bb956a09cca056a979860368f&ipo=images"
//                        ),
//                        WoundReport(
//                            2,
//                            "not deep",
//                            "class2",
//                            "type2",
//                            "12",
//                            "12",
//                            "12",
//                            "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fwww.petbarn.com.au%2Fpetspot%2Fapp%2Fuploads%2F2019%2F01%2Fkitten-000017380158_Smaller.jpg&f=1&nofb=1&ipt=ed37c9a91356b71a8866925647ee12920ed16f623499c6597c8f7f42eb3529c9&ipo=images"
//                        ),
//                    ),
//                    "11-01-2024"
//                )
//            )
//        )
//
//        val client = mockk<Retrofit>()
//        val userApi = mockk<UserApi>()
//        val patientApi = mockk<PatientApi>()
//
//        every { client.create(UserApi::class.java) } returns userApi
//        every { client.create(PatientApi::class.java) } returns patientApi
//        coEvery { patientApi.getPatient("1") } returns patient
//        coEvery { userApi.login(user) } returns Auth("", "")
//
//        return@lazy client
//    }
}