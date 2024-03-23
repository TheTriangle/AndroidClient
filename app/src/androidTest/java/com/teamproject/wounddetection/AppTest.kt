package com.teamproject.wounddetection

import android.Manifest
import android.os.Build
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.rule.GrantPermissionRule
import com.teamproject.wounddetection.view.MainActivity
import org.junit.Rule
import org.junit.Test

class AppTest {

    var permissions = arrayOf(Manifest.permission.CAMERA)


    @JvmField
    @Rule
    var permissionWrite: GrantPermissionRule =
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) {
            GrantPermissionRule.grant(*permissions, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        } else {
            GrantPermissionRule.grant(*permissions)
        }

    @Test
    fun runApp() {
        ActivityScenario.launch(MainActivity::class.java)

        Espresso.onView(withId(R.id.button_sign_in)).perform(ViewActions.click())
        Thread.sleep(1000)

        Espresso.onView(withId(R.id.etPatientCode)).perform(ViewActions.typeText("1"), ViewActions.closeSoftKeyboard())
        Espresso.onView(withId(R.id.btnEnterPatientCode)).perform(ViewActions.click())

        Espresso.onView(withId(R.id.ibCaseList)).perform(ViewActions.click())

        Espresso.onView(withText("Doctor name 1")).perform(ViewActions.click())

        Espresso.onView(withId(R.id.tvCaseTitle)).check(ViewAssertions.matches(withText("Analyzed by Doctor name 1, doctor@email.ru at 21.03.2024")))
    }

}