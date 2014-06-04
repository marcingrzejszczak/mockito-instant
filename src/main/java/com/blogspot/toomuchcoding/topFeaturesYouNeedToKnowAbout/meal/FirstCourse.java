package com.blogspot.toomuchcoding.topFeaturesYouNeedToKnowAbout.meal;

/**
 * User: mgrzejszczak
 * Date: 14.05.13
 * Time: 10:03
 */
public class FirstCourse extends AbstractMeal implements Meal {
    @Override
    public boolean isVegetarian() {
        return false;
    }

    @Override
    public String getName() {
        return "Chicken with potatoes";
    }
}
