package com.blogspot.toomuchcoding.chapters.quickStart;

import com.blogspot.toomuchcoding.topFeaturesYouNeedToKnowAbout.exception.WrongMealException;
import com.blogspot.toomuchcoding.topFeaturesYouNeedToKnowAbout.meal.FirstCourse;
import com.blogspot.toomuchcoding.topFeaturesYouNeedToKnowAbout.meal.Meal;
import com.blogspot.toomuchcoding.topFeaturesYouNeedToKnowAbout.meal.VegetarianFirstCourse;
import com.blogspot.toomuchcoding.topFeaturesYouNeedToKnowAbout.service.KitchenService;
import com.blogspot.toomuchcoding.topFeaturesYouNeedToKnowAbout.service.SimpleWaiterImpl;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

public class SimpleWaiterTest {

    SimpleWaiterImpl objectUnderTest;

    KitchenService kitchenServiceMock = mock(KitchenService.class);

    @Before
    public void setUp(){
        objectUnderTest = new SimpleWaiterImpl(kitchenServiceMock);
    }

    @Test
    public void shouldReturnAFirstCourseMealWhenOrderedHamburger() throws Exception {
        //given
        String mealName = "Hamburger";
        boolean vegetarian = false;
        Meal referenceMeal = new FirstCourse();
        when(kitchenServiceMock.prepareMeal(mealName, vegetarian)).thenReturn(referenceMeal);

        //when
        Meal orderedMeal = objectUnderTest.bringOrderedMeal(mealName, vegetarian);

        //then
        assertThat(orderedMeal, is(referenceMeal));
        verify(kitchenServiceMock).prepareMeal(mealName, vegetarian);
    }

    @Test
    public void shouldThrowAnExceptionWhenAMealOfImproperTypeHasBeenReturnedFromTheKitchen() throws Exception {
        //given
        String mealName = "Hamburger";
        boolean vegetarian = false;
        Meal referenceMeal = new VegetarianFirstCourse();
        when(kitchenServiceMock.prepareMeal(mealName, vegetarian)).thenReturn(referenceMeal);

        //when
        try{
            objectUnderTest.bringOrderedMeal(mealName, vegetarian);
            fail();
        }catch(WrongMealException wrongMealException){

        }

        //then
        verify(kitchenServiceMock).prepareMeal(mealName, vegetarian);
    }

}
