package com.blogspot.toomuchcoding.topFeaturesYouNeedToKnowAbout.service;

import com.blogspot.toomuchcoding.topFeaturesYouNeedToKnowAbout.exception.IAmTooLazyToDoItException;
import com.blogspot.toomuchcoding.topFeaturesYouNeedToKnowAbout.meal.Meal;

import java.util.List;

/**
 * User: mgrzejszczak
 * Date: 14.05.13
 * Time: 10:01
 */
public interface KitchenService {
    Meal prepareMeal(String mealName, boolean vegetarian);
    int calculatePreparationTime(Meal meal);
    List<Meal> prepareVegetarianMeals(List<String> vegetarianMealNames);
    void cleanTheKitchen();
    boolean isKitchenPrepared() throws IAmTooLazyToDoItException;
    boolean isOutfitChanged() throws IAmTooLazyToDoItException;
}
