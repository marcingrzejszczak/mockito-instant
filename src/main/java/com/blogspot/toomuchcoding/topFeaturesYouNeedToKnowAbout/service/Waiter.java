package com.blogspot.toomuchcoding.topFeaturesYouNeedToKnowAbout.service;

import com.blogspot.toomuchcoding.topFeaturesYouNeedToKnowAbout.meal.Meal;
import com.blogspot.toomuchcoding.topFeaturesYouNeedToKnowAbout.meal.RomanticDinnerMenu;
import com.blogspot.toomuchcoding.topFeaturesYouNeedToKnowAbout.service.cleaning.TypeOfCleaningService;

import java.util.List;

/**
 * User: mgrzejszczak
 * Date: 14.05.13
 * Time: 11:18
 */
public interface Waiter {
    List<Meal> bringOrderedVegetarianMeals(List<String> vegetarianMealNames);
    Meal bringOrderedMeal(String mealName, boolean vegetarian);
    int calculateTotalWaitingTime(List<Meal> meals);
    boolean closeTheRestaurant();
    boolean readyToTakeAnOrder();
    void bullyOtherWorkers();
    String describeTheElementsOfTheRomanticDinner(RomanticDinnerMenu romanticDinnerMenu);
    boolean askTheCleaningServiceToCleanTheRestaurant(TypeOfCleaningService cleaningService);
    boolean prepareTheRestaurantToStart();
}
