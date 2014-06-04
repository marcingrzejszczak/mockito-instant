package com.blogspot.toomuchcoding.chapters.topFeaturesYouNeedToKnowAbout;

import com.blogspot.toomuchcoding.topFeaturesYouNeedToKnowAbout.meal.Meal;
import com.blogspot.toomuchcoding.topFeaturesYouNeedToKnowAbout.meal.RomanticDinnerMenu;
import com.blogspot.toomuchcoding.topFeaturesYouNeedToKnowAbout.meal.VegetarianFirstCourse;
import com.blogspot.toomuchcoding.topFeaturesYouNeedToKnowAbout.service.KitchenService;
import com.blogspot.toomuchcoding.topFeaturesYouNeedToKnowAbout.service.WaiterImpl;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static com.blogspot.toomuchcoding.topFeaturesYouNeedToKnowAbout.service.WaiterImpl.VEGETARIAN_MEAL;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;


public class _04StubbingWithACustomAnswerTest {


    KitchenService kitchenServiceMock = mock(KitchenService.class);

    /**
     * Instead of using assertEquals or assertTrue use assertThat(...)
     */
    @Test
    public void shouldAlwaysReturnAVegetarianMealAndAssertTheArgumentsOnMock() throws Exception {
        //given
        final String mealName = "Vegetarian Hamburger";
        when(kitchenServiceMock.prepareMeal(anyString(), anyBoolean())).thenAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                Object[] arguments = invocationOnMock.getArguments();
                String mealNameFromArg = (String) arguments[0];
                boolean vegetarianFromArg = (Boolean) arguments[1];
                assertEquals(mealName, mealNameFromArg);
                assertTrue(VEGETARIAN_MEAL == vegetarianFromArg);
                return new VegetarianFirstCourse();
            }
        });
        WaiterImpl objectUnderTest = new WaiterImpl(kitchenServiceMock);

        //when
        Meal meal = objectUnderTest.bringOrderedMeal(mealName, VEGETARIAN_MEAL);

        //then
        assertThat(meal.isVegetarian(), is(true));
    }

    @Test
    public void shouldReturnTrueWhenTheRestaurantCanBeClosedAndLogInformationAboutMethodArgumentsUsingCustomAnswer() throws Exception {
        //given
        doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Executing method: [").append(invocationOnMock.getMethod().getName()).append("] ");
                stringBuilder.append("Arguments passed to the method:");
                if (invocationOnMock.getArguments().length == 0) {
                    stringBuilder.append(" none - it's a void method");
                } else {
                    for (Object argument : invocationOnMock.getArguments()) {
                        stringBuilder.append(" Class [").append(argument.getClass()).append("] value [").append(argument).append("]");
                    }
                }
                System.out.println(stringBuilder.toString());
                return null;
            }
        }).when(kitchenServiceMock).cleanTheKitchen();
        WaiterImpl objectUnderTest = new WaiterImpl(kitchenServiceMock);

        //when
        boolean canCloseRestaurant = objectUnderTest.closeTheRestaurant();

        //then
        assertThat(canCloseRestaurant, is(true));
        verify(kitchenServiceMock).cleanTheKitchen();
    }

    @Test
    public void shouldReturnTheStringValueOfTheMealsInTheRomanticDinnerMenu() throws Exception {
        //given
        final String firstCourseMealName = "Fish and chips";
        final String vegetarianFirstCourseName = "Lettuce and chips";
        final String dessertName = "Vanilla ice-cream";

        String referenceRomanticDinnerMenuContents = firstCourseMealName + " " + vegetarianFirstCourseName + " " + dessertName;

        RomanticDinnerMenu romanticDinnerMenuMock = mock(RomanticDinnerMenu.class, RETURNS_DEEP_STUBS);
        when(romanticDinnerMenuMock.getFirstCourse().getName()).thenReturn(firstCourseMealName);
        when(romanticDinnerMenuMock.getVegetarianFirstCourse().getName()).thenReturn(vegetarianFirstCourseName);
        when(romanticDinnerMenuMock.getDessert().getName()).thenReturn(dessertName);
        WaiterImpl objectUnderTest = new WaiterImpl(kitchenServiceMock);

        //when
        String romanticDinnerMenuContents = objectUnderTest.describeTheElementsOfTheRomanticDinner(romanticDinnerMenuMock);

        //then
        assertThat(romanticDinnerMenuContents, equalTo(referenceRomanticDinnerMenuContents));
    }
}
