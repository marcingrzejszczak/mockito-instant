package com.blogspot.toomuchcoding.topFeaturesYouNeedToKnowAbout.meal;

/**
 * User: mgrzejszczak
 * Date: 14.05.13
 * Time: 10:03
 */
public class Dessert extends AbstractMeal implements Meal {
    @Override
    public boolean isVegetarian() {
        return true;
    }

    @Override
    public String getName() {
        return "Chocolate Cake";
    }
}
