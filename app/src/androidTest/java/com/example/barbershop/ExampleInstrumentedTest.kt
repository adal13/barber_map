package com.example.barbershop

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.DrawerActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.barbershop.ui.view.Home

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule
import java.util.function.Predicate.not

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @get:Rule
    var activityRule: ActivityScenarioRule<MainActivity> = ActivityScenarioRule(MainActivity::class.java)

    /*@get:Rule
    var activityTestRule = ActivityTestRule(MainActivity::class.java)*/

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.barbershop", appContext.packageName)
    }

    @Test
    fun testLoginAndNavigation() {
        // Simula la entrada de un nombre de usuario y una contraseña
        onView(withId(R.id.tx_username)).perform(typeText("123eeee"), closeSoftKeyboard())
        onView(withId(R.id.tx_password)).perform(typeText("admin"), closeSoftKeyboard())

        // Realiza clic en el botón de inicio de sesión
        onView(withId(R.id.btn_login)).perform(click())

        // Verifica que se ha iniciado sesión y se ha redirigido a la actividad principal
        onView(withId(R.id.drawer_layout)).check(matches(isDisplayed()))

        // Navega a otro fragmento o actividad
        onView(withId(R.id.barberia)).perform(click())

        // Verifica que se haya cargado el fragmento o actividad de destino
        onView(withId(R.id.barberia)).check(matches(isDisplayed()))

        onView(withId(R.id.drawer_layout)).perform(click())

        // Verifica que se haya cargado el fragmento o actividad de destino
        onView(withId(R.id.frame_layout)).check(matches(isDisplayed()))


        onView(withId(R.id.drawer_layout)).perform(click())

        // Verifica que se haya cargado el fragmento o actividad de destino
        onView(withId(R.id.drawer_layout)).check(matches(isDisplayed()))
    }


    @Test
    fun testNavigationDrawer() {
        // Abre la actividad principal de tu aplicación
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        // Simula la entrada de un nombre de usuario y una contraseña
        onView(withId(R.id.tx_username)).perform(typeText("123eeee"), closeSoftKeyboard())
        onView(withId(R.id.tx_password)).perform(typeText("admin"), closeSoftKeyboard())

        // Realiza clic en el botón de inicio de sesión
        onView(withId(R.id.btn_login)).perform(click())

        // Verifica que se ha iniciado sesión y se ha redirigido a la actividad principal
        onView(withId(R.id.drawer_layout)).check(matches(isDisplayed()))

        // Abre la barra de navegación lateral (puedes utilizar el botón de hamburguesa en el Toolbar)
        onView(withContentDescription(R.string.open_nav)).perform(click())

        // Asegúrate de que la barra de navegación está abierta
        onView(withId(R.id.nav_view)).check(matches(isDisplayed()))

        // Haz clic en un elemento del menú de navegación lateral (si es necesario)
        // onView(withText("Item del menú")).perform(click())

        // Cierra la barra de navegación (puedes utilizar el botón de "Atrás" o el botón de cerrar)
        //pressBack()
//        Thread.sleep(1000)
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.close())

        // Asegúrate de que la barra de navegación está cerrada
        //onView(withId(R.id.nav_view)).check(doesNotExist())

        // Cierra la actividad
        activityScenario.close()
    }


}