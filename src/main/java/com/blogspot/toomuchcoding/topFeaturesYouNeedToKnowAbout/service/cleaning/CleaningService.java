package com.blogspot.toomuchcoding.topFeaturesYouNeedToKnowAbout.service.cleaning;

/**
 * User: mgrzejszczak
 * Date: 28.05.13
 * Time: 23:00
 */
public interface CleaningService {
    boolean SUCCESSFUL_MSG_SENDING = true;
    boolean UNSUCCESSFUL_MSG_SENDING = false;

    void cleanTheTables();
    boolean sendInformationRightAfterBeingPicked();
    void sendInformationAfterCleaning();
    boolean isCleaningServiceOfType(TypeOfCleaningService typeOfCleaningService);
}
