package com.blogspot.toomuchcoding.topFeaturesYouNeedToKnowAbout.service.cleaning;

/**
 * User: mgrzejszczak
 * Date: 28.05.13
 * Time: 23:16
 */
public interface CleaningServiceFactory {
    CleaningService getMeACleaningService(TypeOfCleaningService typeOfCleaningService);
}
