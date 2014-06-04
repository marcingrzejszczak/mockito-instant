package com.blogspot.toomuchcoding.topFeaturesYouNeedToKnowAbout.meal;

/**
 * User: mgrzejszczak
 * Date: 21.05.13
 * Time: 00:12
 */
public class RomanticDinnerMenu implements Meal{
    private final FirstCourse firstCourse;
    private final Dessert dessert;
    private final VegetarianFirstCourse vegetarianFirstCourse;

    public RomanticDinnerMenu(final FirstCourse firstCourse, final Dessert dessert, final VegetarianFirstCourse vegetarianFirstCourse) {
        this.firstCourse = firstCourse;
        this.dessert = dessert;
        this.vegetarianFirstCourse = vegetarianFirstCourse;
    }

    public FirstCourse getFirstCourse() {
        return firstCourse;
    }

    public Dessert getDessert() {
        return dessert;
    }

    public VegetarianFirstCourse getVegetarianFirstCourse() {
        return vegetarianFirstCourse;
    }

    @Override
    public boolean isVegetarian() {
        return false;
    }

    @Override
    public String getName() {
        return "Menu perfect for a romantic dinner";
    }
}
