package com.blogspot.toomuchcoding.chapters.soWhatIsMockito;

import com.blogspot.toomuchcoding.topFeaturesYouNeedToKnowAbout.meal.Meal;
import com.blogspot.toomuchcoding.topFeaturesYouNeedToKnowAbout.meal.VegetarianFirstCourse;
import com.blogspot.toomuchcoding.topFeaturesYouNeedToKnowAbout.service.KitchenService;
import com.blogspot.toomuchcoding.topFeaturesYouNeedToKnowAbout.service.WaiterImpl;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.AdditionalMatchers;
import org.mockito.Mockito;

import static com.blogspot.toomuchcoding.topFeaturesYouNeedToKnowAbout.service.WaiterImpl.VEGETARIAN_MEAL;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.AdditionalMatchers.and;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.contains;
import static org.mockito.Matchers.endsWith;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.matches;
import static org.mockito.Matchers.startsWith;
import static org.mockito.Mockito.*;

public class SoWhatIsMockitoTest {

    KitchenService kitchenServiceMock = mock(KitchenService.class);

    @Test
    public void shouldReturnAVegetarianMeal() throws Exception {
        //given
        Mockito.when(kitchenServiceMock.prepareMeal(Mockito.startsWith("Vegetarian"), Mockito.eq(VEGETARIAN_MEAL))).thenReturn(new VegetarianFirstCourse());
        WaiterImpl objectUnderTest = new WaiterImpl(kitchenServiceMock);
        String mealName = "Vegetarian Hamburger";

        //when
        Meal meal = objectUnderTest.bringOrderedMeal(mealName, VEGETARIAN_MEAL);

        //then
        Assert.assertThat(meal.isVegetarian(), CoreMatchers.is(true));
        Mockito.verify(kitchenServiceMock).prepareMeal(mealName, VEGETARIAN_MEAL);
        Mockito.verify(kitchenServiceMock).prepareMeal(AdditionalMatchers.and(Mockito.contains("Ham"), Mockito.endsWith("Hamburger")), Mockito.eq(VEGETARIAN_MEAL));
        Mockito.verify(kitchenServiceMock).prepareMeal(Mockito.matches("V.*n H.*r"), Mockito.anyBoolean());
    }

    @Test
    public void shouldReturnAVegetarianMealWithImportStatics() throws Exception {
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
}
