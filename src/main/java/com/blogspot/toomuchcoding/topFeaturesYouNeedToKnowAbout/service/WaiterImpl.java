package com.blogspot.toomuchcoding.topFeaturesYouNeedToKnowAbout.service;

import com.blogspot.toomuchcoding.topFeaturesYouNeedToKnowAbout.exception.IAmTooLazyToDoItException;
import com.blogspot.toomuchcoding.topFeaturesYouNeedToKnowAbout.exception.TooBusyToCalculateException;
import com.blogspot.toomuchcoding.topFeaturesYouNeedToKnowAbout.exception.WrongMealException;
import com.blogspot.toomuchcoding.topFeaturesYouNeedToKnowAbout.meal.Meal;
import com.blogspot.toomuchcoding.topFeaturesYouNeedToKnowAbout.meal.RomanticDinnerMenu;
import com.blogspot.toomuchcoding.topFeaturesYouNeedToKnowAbout.service.cleaning.*;

import java.util.Iterator;
import java.util.List;

/**
 * User: mgrzejszczak
 * Date: 14.05.13
 * Time: 11:22
 */
public class WaiterImpl implements Waiter {

    public static final boolean VEGETARIAN_MEAL = true;
    public static final boolean NON_VEGETARIAN_MEAL = false;
    private static final boolean RESTAURANT_READY_TO_TAKE_AN_ORDER = true;
    private static final boolean RESTAURANT_NOT_READY_TO_TAKE_AN_ORDER = false;
    private static final boolean SUCCESSFULLY_CLEANED_THE_RESTAURANT = true;
    private static final boolean FAILED_TO_CLEAN_THE_RESTAURANT = false;
    private static final boolean SUCCESSFULLY_PREPARED_THE_RESTAURANT_TO_START = true;
    private static final boolean FAILED_TO_PREPARE_THE_RESTAURANT_TO_START = false;

    private final KitchenService kitchenService;
    private final AdministrativeStaffService administrativeStaffService;
    private final CleaningServiceFactory cleaningServiceFactory;

    public WaiterImpl(final KitchenService kitchenService) {
        this.kitchenService = kitchenService;
        this.administrativeStaffService = new GreatAdministrativeService();
        this.cleaningServiceFactory = new CleaningServiceFactoryImpl();
    }

    public WaiterImpl(final KitchenService kitchenService, final AdministrativeStaffService administrativeStaffService) {
        this.kitchenService = kitchenService;
        this.administrativeStaffService = administrativeStaffService;
        this.cleaningServiceFactory = new CleaningServiceFactoryImpl();
    }

    public WaiterImpl(final KitchenService kitchenService, final AdministrativeStaffService administrativeStaffService, final CleaningServiceFactory cleaningServiceFactory) {
        this.kitchenService = kitchenService;
        this.administrativeStaffService = administrativeStaffService;
        this.cleaningServiceFactory = cleaningServiceFactory;
    }

    public WaiterImpl(final CleaningServiceFactory cleaningServiceFactory) {
        this.kitchenService = new SimpleKitchenService();
        this.administrativeStaffService = new GreatAdministrativeService();
        this.cleaningServiceFactory = cleaningServiceFactory;
    }

    @Override
    public List<Meal> bringOrderedVegetarianMeals(List<String> vegetarianMealNames) {
        List<Meal> preparedMeals = kitchenService.prepareVegetarianMeals(vegetarianMealNames);
        removeNonVegetarianMeals(preparedMeals);
        return preparedMeals;
    }

    private void removeNonVegetarianMeals(List<Meal> potentiallyNonVegetarianMeals) {
        Iterator<Meal> mealIterator = potentiallyNonVegetarianMeals.iterator();
        while (mealIterator.hasNext()) {
            Meal preparedMeal = mealIterator.next();
            if (!preparedMeal.isVegetarian()) {
                mealIterator.remove();
            }
        }
    }

    @Override
    public Meal bringOrderedMeal(String mealName, boolean vegetarian) {
        Meal preparedMeal = kitchenService.prepareMeal(mealName, vegetarian);
        if (preparedMeal.isVegetarian() != vegetarian) {
            throw new WrongMealException("You've prepared a wrong meal!");
        }
        return preparedMeal;
    }

    @Override
    public int calculateTotalWaitingTime(List<Meal> meals) {
        int totalTime = 0;
        for (Meal meal : meals) {
            try {
                totalTime = totalTime + kitchenService.calculatePreparationTime(meal);
            } catch (TooBusyToCalculateException tooBusyToCalculateException) {
                System.err.printf("I am sorry but I couldn't calculate the preparation time of the meal [%s]%n", meal.getName());
            }
        }
        return totalTime;
    }

    @Override
    public boolean closeTheRestaurant() {
        boolean successfullyClosedTheKitchen = true;
        try {
            kitchenService.cleanTheKitchen();
        } catch (IAmTooLazyToDoItException iAmTooLazyToDoItException) {
            successfullyClosedTheKitchen = false;
            System.err.println("Beat it! I am too lazy to clean the kitchen");
        }
        return successfullyClosedTheKitchen;
    }

    @Override
    public boolean readyToTakeAnOrder() throws IAmTooLazyToDoItException {
        if (administrativeStaffService.isPowerOnline() && kitchenService.isOutfitChanged() && kitchenService.isKitchenPrepared()) {
            return RESTAURANT_READY_TO_TAKE_AN_ORDER;
        }
        return RESTAURANT_NOT_READY_TO_TAKE_AN_ORDER;
    }

    @Override
    public void bullyOtherWorkers() {
        administrativeStaffService.isPowerOnline();
        kitchenService.isOutfitChanged();
        kitchenService.isOutfitChanged();
        kitchenService.isKitchenPrepared();
        kitchenService.isOutfitChanged();
    }

    @Override
    public String describeTheElementsOfTheRomanticDinner(RomanticDinnerMenu romanticDinnerMenu) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(romanticDinnerMenu.getFirstCourse().getName()).append(" ");
        stringBuilder.append(romanticDinnerMenu.getVegetarianFirstCourse().getName()).append(" ");
        stringBuilder.append(romanticDinnerMenu.getDessert().getName());
        return stringBuilder.toString();
    }

    @Override
    public boolean askTheCleaningServiceToCleanTheRestaurant(TypeOfCleaningService typeOfCleaningService) {
        CleaningService cleaningService = cleaningServiceFactory.getMeACleaningService(typeOfCleaningService);
        try{
            cleaningService.cleanTheTables();
            cleaningService.sendInformationAfterCleaning();
            return SUCCESSFULLY_CLEANED_THE_RESTAURANT;
        }catch(CommunicationException communicationException){
            System.err.println("An exception took place while trying to send info about cleaning the restaurant");
            return FAILED_TO_CLEAN_THE_RESTAURANT;
        }
    }

    @Override
    public boolean prepareTheRestaurantToStart() {
        CleaningService cleaningService = cleaningServiceFactory.getMeACleaningService(TypeOfCleaningService.GOOD);
        cleaningService.cleanTheTables();
        try{
            AdministrativeStaffService administrativeStaffService = (AdministrativeStaffService)cleaningService; // that looks bad...
            if(administrativeStaffService.isPowerOnline()){
                KitchenService kitchenService = (KitchenService)cleaningService; // that looks bad... again...
                kitchenService.cleanTheKitchen();
            }
            return SUCCESSFULLY_PREPARED_THE_RESTAURANT_TO_START;
        }catch(ClassCastException classCastException){
            System.err.println("Sorry this class can't do more than just clean the restaurant");
            return FAILED_TO_PREPARE_THE_RESTAURANT_TO_START;
        }
    }


    public KitchenService getKitchenService() {
        return kitchenService;
    }
}
