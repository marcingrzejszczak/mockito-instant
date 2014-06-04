package com.blogspot.toomuchcoding.topFeaturesYouNeedToKnowAbout.service;

import com.blogspot.toomuchcoding.topFeaturesYouNeedToKnowAbout.exception.WrongMealException;
import com.blogspot.toomuchcoding.topFeaturesYouNeedToKnowAbout.meal.Meal;
import com.blogspot.toomuchcoding.topFeaturesYouNeedToKnowAbout.meal.RomanticDinnerMenu;
import com.blogspot.toomuchcoding.topFeaturesYouNeedToKnowAbout.service.cleaning.TypeOfCleaningService;

import java.util.List;

/**
 * User: mgrzejszczak
 * Date: 14.05.13
 * Time: 11:22
 */
public class SimpleWaiterImpl implements Waiter {

    private final KitchenService kitchenService;

    public SimpleWaiterImpl(final KitchenService kitchenService) {
        this.kitchenService = kitchenService;
    }

    @Override
    public Meal bringOrderedMeal(String mealName, boolean vegetarian) {
        Meal preparedMeal = kitchenService.prepareMeal(mealName, vegetarian);
        if (preparedMeal.isVegetarian() != vegetarian) {
            throw new WrongMealException("You've prepared a wrong meal!");
        }
        return preparedMeal;
    }

    public KitchenService getKitchenService() {
        return kitchenService;
    }

    @Override
    public List<Meal> bringOrderedVegetarianMeals(List<String> vegetarianMealNames) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int calculateTotalWaitingTime(List<Meal> meals) {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean closeTheRestaurant() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean readyToTakeAnOrder() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void bullyOtherWorkers() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String describeTheElementsOfTheRomanticDinner(RomanticDinnerMenu romanticDinnerMenu) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean askTheCleaningServiceToCleanTheRestaurant(TypeOfCleaningService cleaningService) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean prepareTheRestaurantToStart() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

}
