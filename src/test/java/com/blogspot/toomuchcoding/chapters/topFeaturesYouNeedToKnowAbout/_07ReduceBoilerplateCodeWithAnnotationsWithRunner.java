package com.blogspot.toomuchcoding.chapters.topFeaturesYouNeedToKnowAbout;

import com.blogspot.toomuchcoding.topFeaturesYouNeedToKnowAbout.meal.Meal;
import com.blogspot.toomuchcoding.topFeaturesYouNeedToKnowAbout.meal.VegetarianFirstCourse;
import com.blogspot.toomuchcoding.topFeaturesYouNeedToKnowAbout.service.KitchenService;
import com.blogspot.toomuchcoding.topFeaturesYouNeedToKnowAbout.service.WaiterImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class _07ReduceBoilerplateCodeWithAnnotationsWithRunner {

    @Mock KitchenService kitchenServiceMock;
    @Captor ArgumentCaptor<Meal> mealArgumentCaptor;

    @InjectMocks WaiterImpl objectUnderTest;

    @Test
    public void shouldCalculateTotalWaitingTimeAndAssertTheArgumentsOnMockUsingArgumentCaptor() throws Exception {
        //given
        final int mealPreparationTime = 10;
        when(kitchenServiceMock.calculatePreparationTime(any(Meal.class))).thenReturn(mealPreparationTime);

        //when
        int waitingTime = objectUnderTest.calculateTotalWaitingTime(createSampleMealsContainingVegetarianFirstCourse());

        //then
        assertThat(waitingTime, is(mealPreparationTime));
        verify(kitchenServiceMock).calculatePreparationTime(mealArgumentCaptor.capture());
        assertThat(mealArgumentCaptor.getValue(), is(VegetarianFirstCourse.class));
        assertThat(mealArgumentCaptor.getAllValues().size(), is(1));
    }

    private List<Meal> createSampleMealsContainingVegetarianFirstCourse() {
        List<Meal> meals = new ArrayList<Meal>();
        meals.add(new VegetarianFirstCourse());
        return meals;
    }

}

