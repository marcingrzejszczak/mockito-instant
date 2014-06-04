package com.blogspot.toomuchcoding.topFeaturesYouNeedToKnowAbout.service.cleaning;

/**
 * User: mgrzejszczak
 * Date: 28.05.13
 * Time: 23:01
 */
public class LazyCleaningService implements CleaningService {
    private SomeDatabaseService someDatabaseService;

    @Override
    public void cleanTheTables() {
        System.out.println("I am lazy - I won't clean the tables");
    }

    @Override
    public boolean sendInformationRightAfterBeingPicked() {
        someDatabaseService.saveDataToDatabase("Come on! There are other people who can do it!");
        return SUCCESSFUL_MSG_SENDING;
    }

    @Override
    public void sendInformationAfterCleaning() {
        someDatabaseService.saveDataToDatabase("I'll tell the manager that somebody dirtied the tables after I cleaned it");
    }

    @Override
    public boolean isCleaningServiceOfType(TypeOfCleaningService typeOfCleaningService) {
        return TypeOfCleaningService.LAZY == typeOfCleaningService;
    }
}
