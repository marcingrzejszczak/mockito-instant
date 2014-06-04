package com.blogspot.toomuchcoding.chapters.topFeaturesYouNeedToKnowAbout;

import com.blogspot.toomuchcoding.topFeaturesYouNeedToKnowAbout.meal.FirstCourse;
import com.blogspot.toomuchcoding.topFeaturesYouNeedToKnowAbout.meal.Meal;
import com.blogspot.toomuchcoding.topFeaturesYouNeedToKnowAbout.meal.VegetarianFirstCourse;
import com.blogspot.toomuchcoding.topFeaturesYouNeedToKnowAbout.service.KitchenService;
import com.blogspot.toomuchcoding.topFeaturesYouNeedToKnowAbout.service.WaiterImpl;
import org.junit.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.exceptions.misusing.InvalidUseOfMatchersException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.blogspot.toomuchcoding.topFeaturesYouNeedToKnowAbout.service.WaiterImpl.VEGETARIAN_MEAL;
import static junit.framework.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.AdditionalMatchers.and;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.argThat;
import static org.mockito.Matchers.contains;
import static org.mockito.Matchers.endsWith;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.matches;
import static org.mockito.Matchers.startsWith;
import static org.mockito.Mockito.isA;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.same;

public class _01ArgumentMatcherTest {


    KitchenService kitchenServiceMock = mock(KitchenService.class);

    @Test
    public void shouldReturnAVegetarianMeal() throws Exception {
        //given
        when(kitchenServiceMock.prepareMeal(startsWith("Vegetarian"), eq(VEGETARIAN_MEAL))).thenReturn(new VegetarianFirstCourse());
        WaiterImpl objectUnderTest = new WaiterImpl(kitchenServiceMock);
        String mealName = "Vegetarian Hamburger";

        //when
        Meal meal = objectUnderTest.bringOrderedMeal(mealName, VEGETARIAN_MEAL);

        //then
        assertThat(meal.isVegetarian(), is(true));
        verify(kitchenServiceMock).prepareMeal(mealName, VEGETARIAN_MEAL);
        verify(kitchenServiceMock).prepareMeal(and(contains("Ham"), endsWith("Hamburger")), eq(VEGETARIAN_MEAL));
        verify(kitchenServiceMock).prepareMeal(matches("V.*n H.*r"), anyBoolean());
    }

    @Test
    public void shouldAlwaysReturnAVegetarianMealCheckedUsingArgumentMatcher() throws Exception {
        //given
        when(kitchenServiceMock.prepareMeal(anyString(), anyBoolean())).thenReturn(new VegetarianFirstCourse());
        WaiterImpl objectUnderTest = new WaiterImpl(kitchenServiceMock);
        String mealName = "Vegetarian Hamburger";

        //when
        Meal meal = objectUnderTest.bringOrderedMeal(mealName, VEGETARIAN_MEAL);

        //then
        assertThat(meal.isVegetarian(), is(true));
        verify(kitchenServiceMock).prepareMeal(argThat(new VegetarianMealNameMatcher()), eq(VEGETARIAN_MEAL));
    }

    @Test
    public void shouldAlwaysReturnAVegetarianMealCheckedUsingArgumentMatcherAndBddMockito() throws Exception {
        //given
        given(kitchenServiceMock.prepareMeal(anyString(), anyBoolean())).willReturn(new VegetarianFirstCourse());
        WaiterImpl objectUnderTest = new WaiterImpl(kitchenServiceMock);
        String mealName = "Vegetarian Hamburger";

        //when
        Meal meal = objectUnderTest.bringOrderedMeal(mealName, VEGETARIAN_MEAL);

        //then
        assertThat(meal.isVegetarian(), is(true));
        verify(kitchenServiceMock).prepareMeal(argThat(new VegetarianMealNameMatcher()), eq(VEGETARIAN_MEAL));
    }

    @Test
    public void shouldThrowExceptionWhenYouForgetToUseMatchersForAllParameters() throws Exception {
        //given
        when(kitchenServiceMock.prepareMeal(anyString(), anyBoolean())).thenReturn(new VegetarianFirstCourse());
        WaiterImpl objectUnderTest = new WaiterImpl(kitchenServiceMock);
        String mealName = "Vegetarian Hamburger";

        //when
        Meal meal = objectUnderTest.bringOrderedMeal(mealName, VEGETARIAN_MEAL);

        //then
        try {
            assertThat(meal, notNullValue());
            // Remember that if to the argument list provide at least one matcher then all of the parametrs must become matchers
            verify(kitchenServiceMock).prepareMeal(startsWith("Vegetarian"), VEGETARIAN_MEAL);
            fail();
        } catch (InvalidUseOfMatchersException exception) {
            System.err.println(exception);
        }
    }

    @Test
    public void shouldThrowExceptionWhenYouForgetToUseMatchersForAllParametersForStubbing() throws Exception {
        //given
        try {
            when(kitchenServiceMock.prepareMeal(anyString(), true)).thenReturn(new VegetarianFirstCourse());
            fail();
        } catch (InvalidUseOfMatchersException exception) {
            System.err.println(exception);
        }
    }

    @Test
    public void shouldReturnOnlyVegetarianMeals() throws Exception {
        //given
        when(kitchenServiceMock.prepareVegetarianMeals(anyListOf(String.class))).thenReturn(createSampleMealList());
        WaiterImpl objectUnderTest = new WaiterImpl(kitchenServiceMock);

        //when
        List<String> mealNames = Arrays.asList("First Course", "First Course", "Vegetarian First Course");
        List<Meal> meals = objectUnderTest.bringOrderedVegetarianMeals(mealNames);

        //then
        assertThat(meals.size(), is(1));
        verify(kitchenServiceMock).prepareVegetarianMeals(eq(mealNames));
        verify(kitchenServiceMock).prepareVegetarianMeals(isA(List.class));
        verify(kitchenServiceMock).prepareVegetarianMeals(same(mealNames));
    }

    private List<Meal> createSampleMealList() {
        List<Meal> meals = new ArrayList<Meal>();
        meals.add(new FirstCourse());
        meals.add(new FirstCourse());
        meals.add(new VegetarianFirstCourse());

        return meals;
    }

    private static class VegetarianMealNameMatcher extends ArgumentMatcher<String> {
        @Override
        public boolean matches(Object o) {
            String mealName = (String) o;
            return mealName != null && mealName.startsWith("Vegetarian") && !mealName.contains("Meat");
        }
    }
}
