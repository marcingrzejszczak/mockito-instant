package com.blogspot.toomuchcoding.chapters.topFeaturesYouNeedToKnowAbout;

import com.blogspot.toomuchcoding.topFeaturesYouNeedToKnowAbout.meal.Dessert;
import com.blogspot.toomuchcoding.topFeaturesYouNeedToKnowAbout.meal.FirstCourse;
import com.blogspot.toomuchcoding.topFeaturesYouNeedToKnowAbout.meal.Meal;
import com.blogspot.toomuchcoding.topFeaturesYouNeedToKnowAbout.meal.VegetarianFirstCourse;
import com.blogspot.toomuchcoding.topFeaturesYouNeedToKnowAbout.service.KitchenService;
import com.blogspot.toomuchcoding.topFeaturesYouNeedToKnowAbout.exception.TooBusyToCalculateException;
import com.blogspot.toomuchcoding.topFeaturesYouNeedToKnowAbout.service.WaiterImpl;
import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;


/**
 * BELOW YOU CAN FIND TERRIBLE EXAMPLES OF HOW TO STUB LISTS - DO NOT EVER, EVER DO IT!
 */
public class _02StubbingMultipleCallsTest {


    KitchenService kitchenServiceMock = mock(KitchenService.class);

    /**
     * Do not ever stub lists!!! Take a look at the next example fo proper usage of lists in tests
     */
    @Test
    public void shouldCalculatePreparationTimeForAListOfMeals() throws Exception {
        //given
        WaiterImpl objectUnderTest = new WaiterImpl(kitchenServiceMock);

        List<Meal> meals = mock(List.class);
        Iterator<Meal> mealIterator = mock(Iterator.class);
        when(meals.iterator()).thenReturn(mealIterator);
        when(mealIterator.hasNext()).thenReturn(true, true, true, false);
        when(mealIterator.next()).thenReturn(new Dessert(), new FirstCourse(), new VegetarianFirstCourse());

        int dessertPreparationTime = 1;
        int firstCoursePreparationTime = 2;
        int vegetarianFirstCoursePreparationTime = 3;
        int referenceTotalPreparationTime = dessertPreparationTime + firstCoursePreparationTime + vegetarianFirstCoursePreparationTime;

        when(kitchenServiceMock.calculatePreparationTime(any(Meal.class))).thenReturn(dessertPreparationTime)
                .thenReturn(firstCoursePreparationTime)
                .thenReturn(vegetarianFirstCoursePreparationTime);

        //when
        int totalPreparationTime = objectUnderTest.calculateTotalWaitingTime(meals);

        //then
        assertThat(totalPreparationTime, is(referenceTotalPreparationTime));
        verify(kitchenServiceMock, times(3)).calculatePreparationTime(any(Meal.class));
    }

    @Test
    public void shouldCalculatePreparationTimeForAListOfMealsInAGoodWay() throws Exception {
        //given
        WaiterImpl objectUnderTest = new WaiterImpl(kitchenServiceMock);
        List<Meal> meals = Arrays.<Meal>asList(new Dessert(), new FirstCourse(), new VegetarianFirstCourse());
        int dessertPreparationTime = 1;
        int firstCoursePreparationTime = 2;
        int vegetarianFirstCoursePreparationTime = 3;
        int referenceTotalPreparationTime = dessertPreparationTime + firstCoursePreparationTime + vegetarianFirstCoursePreparationTime;
        when(kitchenServiceMock.calculatePreparationTime(any(Meal.class))).thenReturn(dessertPreparationTime)
                .thenReturn(firstCoursePreparationTime)
                .thenReturn(vegetarianFirstCoursePreparationTime);

        //when
        int totalPreparationTime = objectUnderTest.calculateTotalWaitingTime(meals);

        //then
        assertThat(totalPreparationTime, is(referenceTotalPreparationTime));
        verify(kitchenServiceMock, times(3)).calculatePreparationTime(any(Meal.class));
    }

    /**
     * Do not ever stub lists!!! Take a look at the next example fo proper usage of lists in tests
     */
    @Test
    public void shouldThrowExceptionsWhileCalculatingTotalTimeAndReturnZeroAsTotalTime() throws Exception {
        //given
        WaiterImpl objectUnderTest = new WaiterImpl(kitchenServiceMock);

        List<Meal> meals = mock(List.class);
        Iterator<Meal> mealIterator = mock(Iterator.class);
        when(meals.iterator()).thenReturn(mealIterator);
        when(mealIterator.hasNext()).thenReturn(true, true, true, false);
        when(mealIterator.next()).thenReturn(new Dessert(), new FirstCourse(), new VegetarianFirstCourse());

        when(kitchenServiceMock.calculatePreparationTime(any(Meal.class))).thenThrow(new TooBusyToCalculateException(), new TooBusyToCalculateException(), new TooBusyToCalculateException());

        //when
        int totalPreparationTime = objectUnderTest.calculateTotalWaitingTime(meals);

        //then
        assertThat(totalPreparationTime, is(0));
        verify(kitchenServiceMock, times(3)).calculatePreparationTime(any(Meal.class));
    }

    @Test
    public void shouldThrowExceptionsWhileCalculatingTotalTimeAndReturnZeroAsTotalTimeInAGoodWay() throws Exception {
        //given
        WaiterImpl objectUnderTest = new WaiterImpl(kitchenServiceMock);
        List<Meal> meals = Arrays.<Meal>asList(new Dessert(), new FirstCourse(), new VegetarianFirstCourse());
        when(kitchenServiceMock.calculatePreparationTime(any(Meal.class))).thenThrow(new TooBusyToCalculateException(), new TooBusyToCalculateException(), new TooBusyToCalculateException());

        //when
        int totalPreparationTime = objectUnderTest.calculateTotalWaitingTime(meals);

        //then
        assertThat(totalPreparationTime, is(0));
        verify(kitchenServiceMock, times(3)).calculatePreparationTime(any(Meal.class));
    }

    /**
     * Do not ever stub lists!!! Take a look at the next example fo proper usage of lists in tests
     */
    @Test
    public void shouldThrowOneExceptionWhileCalculatingTotalTimeAndReturnNonZeroAsTotalTime() throws Exception {
        //given
        WaiterImpl objectUnderTest = new WaiterImpl(kitchenServiceMock);

        List<Meal> meals = mock(List.class);
        Iterator<Meal> mealIterator = mock(Iterator.class);
        when(meals.iterator()).thenReturn(mealIterator);
        when(mealIterator.hasNext()).thenReturn(true, true, true, false);
        when(mealIterator.next()).thenReturn(new Dessert(), new FirstCourse(), new VegetarianFirstCourse());

        int dessertPreparationTime = 1;
        int vegetarianFirstCoursePreparationTime = 3;
        int referencePreparationTime = dessertPreparationTime + vegetarianFirstCoursePreparationTime;

        when(kitchenServiceMock.calculatePreparationTime(any(Meal.class))).thenReturn(dessertPreparationTime)
                .thenThrow(new TooBusyToCalculateException())
                .thenReturn(vegetarianFirstCoursePreparationTime);

        //when
        int totalPreparationTime = objectUnderTest.calculateTotalWaitingTime(meals);

        //then
        assertThat(totalPreparationTime, is(referencePreparationTime));
        verify(kitchenServiceMock, times(3)).calculatePreparationTime(any(Meal.class));
    }

    @Test
    public void shouldThrowOneExceptionWhileCalculatingTotalTimeAndReturnNonZeroAsTotalTimeInAGoodWay() throws Exception {
        //given
        WaiterImpl objectUnderTest = new WaiterImpl(kitchenServiceMock);
        List<Meal> meals = Arrays.<Meal>asList(new Dessert(), new FirstCourse(), new VegetarianFirstCourse());
        int dessertPreparationTime = 1;
        int vegetarianFirstCoursePreparationTime = 3;
        int referencePreparationTime = dessertPreparationTime + vegetarianFirstCoursePreparationTime;
        when(kitchenServiceMock.calculatePreparationTime(any(Meal.class))).thenReturn(dessertPreparationTime)
                .thenThrow(new TooBusyToCalculateException())
                .thenReturn(vegetarianFirstCoursePreparationTime);

        //when
        int totalPreparationTime = objectUnderTest.calculateTotalWaitingTime(meals);

        //then
        assertThat(totalPreparationTime, is(referencePreparationTime));
        verify(kitchenServiceMock, times(3)).calculatePreparationTime(any(Meal.class));
    }
}
