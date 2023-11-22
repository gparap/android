/*
 * Copyright 2023 gparap
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package gparap.apps.e_commerce.ui;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.core.app.ActivityScenario;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Before;
import org.junit.Test;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

import gparap.apps.e_commerce.R;
import gparap.apps.e_commerce.data.CartItemModel;
import gparap.apps.e_commerce.data.CartRepository;

public class CartActivityInstrumentedTest {
    private ActivityScenario<CartActivity> activityScenario;

    @Before
    public void setUp() {
        activityScenario = ActivityScenario.launch(CartActivity.class);
        emptyCart();
    }

    @Test
    public void isVisible_recyclerViewCart() {
        addItemToCart();
        onView(withId(R.id.recycler_view_cart)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_itemImage() {
        addItemToCart();
        onView(withId(R.id.image_view_cart_item)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_itemName() {
        addItemToCart();
        onView(withId(R.id.text_view_cart_item_name)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_itemPrice() {
        addItemToCart();
        onView(withId(R.id.text_view_cart_item_price)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_itemQuantity() {
        addItemToCart();
        onView(withId(R.id.text_view_cart_item_quantity)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_removeItemButton() {
        addItemToCart();
        onView(withId(R.id.button_cart_remove_one)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_addItemButton() {
        addItemToCart();
        onView(withId(R.id.button_cart_add_one)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_text_view_cart_items_label() {
        onView(withId(R.id.text_view_cart_items_label)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_text_view_cart_items_details_label() {
        onView(withId(R.id.text_view_cart_items_details_label)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_text_view_cart_total_items_label() {
        onView(withId(R.id.text_view_cart_total_items_label)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_text_view_cart_total_items() {
        onView(withId(R.id.text_view_cart_total_items)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_text_view_cart_total_cost_label() {
        onView(withId(R.id.text_view_cart_total_cost_label)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_text_view_cart_total_cost() {
        onView(withId(R.id.text_view_cart_total_cost)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_text_view_cart_discount_label() {
        onView(withId(R.id.text_view_cart_discount_label)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_text_view_cart_discount() {
        onView(withId(R.id.text_view_cart_discount)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_text_view_cart_final_cost_label() {
        onView(withId(R.id.text_view_cart_final_cost_label)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_text_view_cart_final_cost() {
        onView(withId(R.id.text_view_cart_final_cost)).check(matches(isDisplayed()));
    }

    @Test
    public void isVisible_button_checkout() {
        onView(withId(R.id.button_checkout)).check(matches(isDisplayed()));
    }

    @Test
    public void isNotEmpty_recyclerViewCart() {
        addItemToCart();
        activityScenario.onActivity(activity -> {
            RecyclerView recyclerView = activity.findViewById(R.id.recycler_view_cart);
            assert (Objects.requireNonNull(recyclerView.getAdapter()).getItemCount() > 0);
        });
    }

    @Test
    public void isRemoved_recyclerViewCartItem() {
        //add a cart item
        addItemToCart();
        activityScenario.onActivity(activity -> {
            RecyclerView recyclerView = activity.findViewById(R.id.recycler_view_cart);
            assert (Objects.requireNonNull(recyclerView.getAdapter()).getItemCount() > 0);
        });

        //remove the first cart item
        onView(withId(R.id.button_cart_remove_item)).perform(click());
        onView(withText(R.string.text_dialog_ok)).perform(click());
        activityScenario.recreate();

        //test if item was removed
        activityScenario.onActivity(activity -> {
            RecyclerView recyclerView = activity.findViewById(R.id.recycler_view_cart);
            assert (Objects.requireNonNull(recyclerView.getAdapter()).getItemCount() == 0);
        });
    }

    private void addItemToCart() {
        AtomicInteger items = new AtomicInteger(0);
        activityScenario.onActivity(activity -> {
            //add item
            CartItemModel item = new CartItemModel(1, 1, "test item", 0.99F, 0, "", 1);
            CartRepository repository = new CartRepository(InstrumentationRegistry.getInstrumentation().getTargetContext());
            repository.addItemToCart(item);
            items.set(1);
        });

        //restart activity
        activityScenario.recreate();
    }

    private void emptyCart() {
        CartRepository cartRepository = new CartRepository(InstrumentationRegistry.getInstrumentation().getTargetContext());
        cartRepository.emptyCart();
    }
}