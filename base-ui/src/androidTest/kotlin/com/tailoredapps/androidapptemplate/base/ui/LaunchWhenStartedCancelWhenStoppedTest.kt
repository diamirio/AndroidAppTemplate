package com.tailoredapps.androidapptemplate.base.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.testing.TestLifecycleOwner
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

internal class LaunchWhenStartedCancelWhenStoppedScopeTest {

    @Test
    fun extension_TestLifecycleOwner() {
        val lifecycleOwner = TestLifecycleOwner(initialState = Lifecycle.State.CREATED)

        var job: Job? = null
        var callCounter = 0
        lifecycleOwner.lifecycle.launchWhenStartedCancelWhenStopped {
            job = indefiniteFlow.launchIn(this)
            callCounter++
        }
        assertNull(job)
        assertEquals(0, callCounter)

        lifecycleOwner.currentState = Lifecycle.State.STARTED
        assertTrue(assertNotNull(job).isActive)
        assertFalse(assertNotNull(job).isCancelled)

        lifecycleOwner.currentState = Lifecycle.State.RESUMED
        assertTrue(assertNotNull(job).isActive)
        assertFalse(assertNotNull(job).isCancelled)

        lifecycleOwner.currentState = Lifecycle.State.STARTED
        assertTrue(assertNotNull(job).isActive)
        assertFalse(assertNotNull(job).isCancelled)
        assertEquals(1, callCounter)

        lifecycleOwner.currentState = Lifecycle.State.CREATED
        assertFalse(assertNotNull(job).isActive)
        assertTrue(assertNotNull(job).isCancelled)

        lifecycleOwner.currentState = Lifecycle.State.STARTED
        assertTrue(assertNotNull(job).isActive)
        assertFalse(assertNotNull(job).isCancelled)

        assertEquals(2, callCounter)
    }

    @Test
    fun extension_FragmentScenario() {
        val scenario = launchFragmentInContainer<TestFragment>()
        scenario.onFragment { fragment -> assertTrue(assertNotNull(fragment.job).isActive) }

        scenario.moveToState(Lifecycle.State.CREATED)
        scenario.onFragment { fragment -> assertFalse(assertNotNull(fragment.job).isActive) }

        scenario.moveToState(Lifecycle.State.STARTED)
        scenario.onFragment { fragment -> assertTrue(assertNotNull(fragment.job).isActive) }

        scenario.onFragment { fragment -> assertEquals(2, fragment.callCounter) }
    }

    class TestFragment : Fragment() {
        var job: Job? = null
        var callCounter = 0

        init {
            launchWhenViewStartedCancelWhenViewStopped {
                job = indefiniteFlow.launchIn(this)
                callCounter++
            }
        }

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? = FrameLayout(requireContext())
    }

    companion object {
        private val indefiniteFlow = flow {
            while (true) {
                delay(100)
                emit(Unit)
            }
        }
    }
}