package com.blogspot.toomuchcoding.topFeaturesYouNeedToKnowAbout.service;

import com.blogspot.toomuchcoding.topFeaturesYouNeedToKnowAbout.exception.IAmTooLazyToDoItException;
import com.blogspot.toomuchcoding.topFeaturesYouNeedToKnowAbout.meal.Meal;
import com.blogspot.toomuchcoding.topFeaturesYouNeedToKnowAbout.service.cleaning.CleaningService;
import com.blogspot.toomuchcoding.topFeaturesYouNeedToKnowAbout.service.cleaning.TypeOfCleaningService;

import java.util.List;

/**
 * User: mgrzejszczak
 * Date: 02.06.13
 * Time: 19:13
 */
public class CanDoEverthingService implements AdministrativeStaffService, KitchenService, CleaningService {
    /*
        implementations of all the methods - removed for readability reasons
     */
    @Override
    public boolean isPowerOnline() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

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

    @Override
    public void cleanTheTables() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean sendInformationRightAfterBeingPicked() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void sendInformationAfterCleaning() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isCleaningServiceOfType(TypeOfCleaningService typeOfCleaningService) {
        return TypeOfCleaningService.GOOD == typeOfCleaningService;
    }
}
