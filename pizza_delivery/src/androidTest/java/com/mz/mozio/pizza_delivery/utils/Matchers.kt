package com.mz.mozio.pizza_delivery.utils

import android.view.View
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withParent
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsNot

class RecyclerViewMatcher(@IdRes private val recyclerViewId: Int) {

    companion object {
        private const val INVALID_ID = -1

        fun recyclerViewWithId(@IdRes viewId: Int): RecyclerViewMatcher {
            return RecyclerViewMatcher(viewId)
        }
    }


    fun itemViewAtIndex(index: Int): Matcher<View> {
        return viewHolderViewAtPosition(index, INVALID_ID)
    }

    fun viewHolderViewAtPosition(index: Int, @IdRes targetViewId: Int): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            var itemView: View? = null

            override fun describeTo(description: Description) {
                description.appendText("RecyclerView view with id: $recyclerViewId not found.")
            }

            override fun matchesSafely(view: View): Boolean {
                if (itemView == null) {
                    val recyclerView: RecyclerView? =
                        view.rootView.findViewById(recyclerViewId) as? RecyclerView
                    if (recyclerView != null && recyclerView.id == recyclerViewId) {
                        itemView = recyclerView.findViewHolderForAdapterPosition(index)!!.itemView
                    } else {
                        return false
                    }
                }

                return if (targetViewId == INVALID_ID) {
                    view === itemView
                } else {
                    val targetView = itemView!!.findViewById<View>(targetViewId)
                    view === targetView
                }
            }
        }
    }
}

internal fun matchView(matcher: Matcher<View>): ViewInteraction = Espresso.onView(matcher)

internal fun @receiver:IdRes Int.withId() = ViewMatchers.withId(this)
internal fun @receiver:IdRes Int.matchView(): ViewInteraction = matchView(this.withId())

internal fun @receiver:IdRes Int.viewIsDisplayed(): ViewInteraction =
    matchView(this.withId()).check(matches(isDisplayed()))

internal fun @receiver:IdRes Int.viewIsNotDisplayed(): ViewInteraction =
    matchView(this.withId()).check(matches(IsNot.not(isDisplayed())))

internal fun @receiver:IdRes Int.viewWith(text: String): ViewInteraction =
    matchView(this.withId()).check(withText(text))

internal fun @receiver:IdRes Int.clickOn(): ViewInteraction =
    matchView().perform(ViewActions.click())

internal fun @receiver:IdRes Int.isDisplayedInParent(@IdRes id: Int): ViewInteraction =
    matchView(allOf(this.withId(), withParent(id.withId()))).check(matches(isDisplayed()))

internal fun @receiver:IdRes Int.viewInParentWith(
    @IdRes id: Int, text: String
): ViewInteraction = matchView(allOf(this.withId(), withParent(id.withId()))).check(withText(text))

internal fun <T : RecyclerView.ViewHolder> @receiver:IdRes Int.performClickOnRecyclerViewItemAt(
    index: Int
) = matchView().performActionOnRecyclerViewItemAt<T>(index, ViewActions.click())

internal fun @receiver:IdRes Int.asRecyclerView() = RecyclerViewMatcher.recyclerViewWithId(this)

internal fun @receiver:IdRes Int.onRecyclerView(parent: Int, index: Int) =
    matchView(parent.asRecyclerView().viewHolderViewAtPosition(index, this))

internal fun @receiver:IdRes Int.isDisplayedOn(parent: Int, index: Int) =
    this.onRecyclerView(parent, index).check(matches(isDisplayed()))

internal fun @receiver:IdRes Int.hasTextOn(parent: Int, index: Int, text: String) =
    this.onRecyclerView(parent, index).check(withText(text))

internal fun @receiver:IdRes Int.click(parent: Int, index: Int) =
    this.onRecyclerView(parent, index).perform(ViewActions.click())

fun <T : RecyclerView.ViewHolder> ViewInteraction.performActionOnRecyclerViewItemAt(
    index: Int, action: ViewAction
) = perform(RecyclerViewActions.actionOnItemAtPosition<T>(index, action))

private fun withText(text: String) = matches(ViewMatchers.withText(text))
