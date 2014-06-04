package com.blogspot.toomuchcoding.topFeaturesYouNeedToKnowAbout.meal;

/**
 * User: mgrzejszczak
 * Date: 14.05.13
 * Time: 13:15
 */
public class VegetarianFirstCourse extends AbstractMeal implements Meal {
    @Override
    public boolean isVegetarian() {
        return true;
    }

    @Override
    public String getName() {
        return "Bean Soup";
    }
}
