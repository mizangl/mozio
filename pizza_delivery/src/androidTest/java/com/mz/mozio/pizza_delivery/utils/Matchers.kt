package com.mz.mozio.pizza_delivery.utils

import android.view.View
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withParent
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsNot

internal class IndexMatcher(
    private val recycler: Matcher<View>,
    private val index: Int = 0,
) : TypeSafeMatcher<View>() {
    override fun describeTo(description: Description?) {
        description?.appendText("view at $index of ")?.appendDescriptionOf(recycler)
    }

    override fun matchesSafely(item: View): Boolean {
        return if (recycler.matches(item.parent) && item.parent is RecyclerView) {
            val viewHolder = (recycler as RecyclerView).findViewHolderForAdapterPosition(index)
            return viewHolder?.itemView == item
        } else false
    }
}

internal class ViewAdapterSizeMatcher(
    private val recycler: Matcher<View>,
    private val size: Int = 0,
) : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {
    override fun describeTo(description: Description?) {
        description?.appendText("size of $size of ")?.appendDescriptionOf(recycler)
    }

    override fun matchesSafely(item: RecyclerView): Boolean = item.adapter?.itemCount == size
}

internal fun matchView(matcher: Matcher<View>): ViewInteraction = Espresso.onView(matcher)

internal fun @receiver:IdRes Int.withId() = ViewMatchers.withId(this)
internal fun @receiver:IdRes Int.matchView(): ViewInteraction = matchView(this.withId())

internal fun @receiver:IdRes Int.viewIsDisplayed(): ViewInteraction =
    matchView(this.withId()).check(ViewAssertions.matches(isDisplayed()))

internal fun @receiver:IdRes Int.viewIsNotDisplayed(): ViewInteraction =
    matchView(this.withId()).check(
        ViewAssertions.matches(IsNot.not(isDisplayed()))
    )

internal fun @receiver:IdRes Int.viewWith(text: String): ViewInteraction =
    matchView(this.withId()).check(
        ViewAssertions.matches(ViewMatchers.withText(text))
    )

internal fun @receiver:IdRes Int.clickOn(): ViewInteraction =
    matchView().perform(ViewActions.click())

internal fun @receiver:IdRes Int.isDisplayedInParent(@IdRes id: Int): ViewInteraction =
    matchView(allOf(this.withId(), withParent(id.withId()))).check(ViewAssertions.matches(isDisplayed()))

internal fun @receiver:IdRes Int.viewInParentWith(
    @IdRes id: Int,
    text: String
): ViewInteraction = matchView(allOf(this.withId(), withParent(id.withId()))).check(
        ViewAssertions.matches(ViewMatchers.withText(text))
    )