package com.blogspot.toomuchcoding.topFeaturesYouNeedToKnowAbout.meal;

/**
 * User: mgrzejszczak
 * Date: 20.05.13
 * Time: 22:27
 */
public abstract class AbstractMeal implements Meal{
    @Override
    public String toString(){
        return String.format("Name [%s] is Vegetarian [%s]", getName(), isVegetarian());
    }
}
