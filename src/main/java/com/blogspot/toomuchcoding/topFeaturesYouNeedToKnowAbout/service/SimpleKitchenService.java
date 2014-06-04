package com.blogspot.toomuchcoding.topFeaturesYouNeedToKnowAbout.service;

import com.blogspot.toomuchcoding.topFeaturesYouNeedToKnowAbout.exception.IAmTooLazyToDoItException;
import com.blogspot.toomuchcoding.topFeaturesYouNeedToKnowAbout.meal.Meal;

import java.util.List;

/**
 * User: mgrzejszczak
 * Date: 02.06.13
 * Time: 19:01
 */
public class SimpleKitchenService implements KitchenService{
    @Override
    public Meal prepareMeal(String mealName, boolean vegetarian) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int calculatePreparationTime(Meal meal) {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<Meal> prepareVegetarianMeals(List<String> vegetarianMealNames) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void cleanTheKitchen() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isKitchenPrepared() throws IAmTooLazyToDoItException {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isOutfitChanged() throws IAmTooLazyToDoItException {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
