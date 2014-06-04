package com.blogspot.toomuchcoding.topFeaturesYouNeedToKnowAbout.service.cleaning;

/**
 * User: mgrzejszczak
 * Date: 28.05.13
 * Time: 23:01
 */
public class BoredCleaningService implements CleaningService {

    private SomeDatabaseService someDatabaseService;

    @Override
    public void cleanTheTables() {
        System.out.println("I am bored - but I'll clean the tables anyway...");
    }

    @Override
    public boolean sendInformationRightAfterBeingPicked() {
        someDatabaseService.saveDataToDatabase("Oh man... I don't want to do it...");
        return SUCCESSFUL_MSG_SENDING;
    }

    @Override
    public void sendInformationAfterCleaning() {
        someDatabaseService.saveDataToDatabase("Although I was bored I managed to somehow clean the tables");
    }

    @Override
    public boolean isCleaningServiceOfType(TypeOfCleaningService typeOfCleaningService) {
        return TypeOfCleaningService.BORED == typeOfCleaningService;
    }

}
