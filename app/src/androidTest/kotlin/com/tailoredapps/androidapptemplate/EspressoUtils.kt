/* Copyright 2017 Tailored Media GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. */

package com.tailoredapps.androidapptemplate

import android.content.res.Resources
import android.view.View
import junit.framework.AssertionFailedError
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isDisplayingAtLeast

object EspressoUtils {

    fun matchFirstDisplayedWithId(id: Int): Matcher<View> = object : TypeSafeMatcher<View>() {
        private var alreadyMatched = false
        private var resources: Resources? = null

        override fun describeTo(description: Description) {
            var idDescription = id.toString()
            if (resources != null) {
                idDescription = try {
                    resources!!.getResourceName(id)
                } catch (e: Resources.NotFoundException) {
                    // No big deal, will just use the int value.
                    String.format("%s (resource name not found)", id)
                }
            }
            description.appendText("with id: $idDescription")
        }

        public override fun matchesSafely(view: View): Boolean {
            return if (alreadyMatched) {
                false
            } else {
                resources = view.resources
                alreadyMatched = isDisplayed().matches(view) && id == view.id
                alreadyMatched
            }
        }
    }

    fun clickChildViewWithId(id: Int): ViewAction = object : ViewAction {
        override fun getConstraints(): Matcher<View> = isDisplayingAtLeast(90)

        override fun getDescription(): String = "Click on a child view with id $id."

        override fun perform(uiController: UiController, view: View) {
            val v = view.findViewById<View>(id)
            v?.performClick()
        }
    }

    fun recyclerViewItemCount(count: Int): ViewAssertion = ViewAssertion { view, _ ->
        if (view is RecyclerView) {
            val adapter = view.adapter
            if (adapter != null && adapter.itemCount != count) {
                throw AssertionFailedError(
                    "RecyclerView with id=" + view.id + " has " +
                            adapter.itemCount + " items, expected " + count + " items"
                )
            }
        } else {
            throw AssertionFailedError("View is not a RecyclerView")
        }
    }

}
