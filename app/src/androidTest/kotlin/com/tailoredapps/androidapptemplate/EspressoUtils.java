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

package com.tailoredapps.template;

import android.content.res.Resources;
import android.view.View;

import junit.framework.AssertionFailedError;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.ViewAssertion;

import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayingAtLeast;


public class EspressoUtils {

    public static Matcher<View> matchFirstDisplayedWithId(final int id) {
        return new TypeSafeMatcher<View>() {

            private boolean alreadyMatched = false;
            private Resources resources = null;

            @Override
            public void describeTo(Description description) {
                String idDescription = Integer.toString(id);
                if (resources != null) {
                    try {
                        idDescription = resources.getResourceName(id);
                    } catch (Resources.NotFoundException e) {
                        // No big deal, will just use the int value.
                        idDescription = String.format("%s (resource name not found)", id);
                    }
                }
                description.appendText("with id: " + idDescription);
            }

            @Override
            public boolean matchesSafely(View view) {
                if(alreadyMatched) {
                    return false;
                } else {
                    resources = view.getResources();
                    alreadyMatched = isDisplayed().matches(view) && id == view.getId();
                    return alreadyMatched;
                }
            }
        };
    }

    public static ViewAction clickChildViewWithId(final int id) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isDisplayingAtLeast(90);
            }

            @Override
            public String getDescription() {
                return "Click on a child view with id " + id + ".";
            }

            @Override
            public void perform(UiController uiController, View view) {
                View v = view.findViewById(id);
                if (v != null) { v.performClick(); }
            }
        };
    }

    public static ViewAssertion recyclerViewItemCount(final int count) {
        return (view, noViewFoundException) -> {
            if(view instanceof RecyclerView) {
                RecyclerView recyclerView = (RecyclerView) view;
                if(recyclerView.getAdapter().getItemCount() != count) {
                    throw new AssertionFailedError("RecyclerView with id=" + recyclerView.getId() + " has " + recyclerView.getAdapter().getItemCount() + " items, expected " + count + " items");
                }
            } else {
                throw new AssertionFailedError("View is not a RecyclerView");
            }
        };
    }

}
