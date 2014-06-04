package com.blogspot.toomuchcoding.topFeaturesYouNeedToKnowAbout.service.cleaning;

/**
 * User: mgrzejszczak
 * Date: 28.05.13
 * Time: 23:01
 */
public class UnhappyCleaningService implements CleaningService {
    private SomeDatabaseService someDatabaseService;

    @Override
    public void cleanTheTables() {
        System.out.println("I am unhappy - I don't care about the tables...");
    }

    @Override
    public boolean sendInformationRightAfterBeingPicked() {
        someDatabaseService.saveDataToDatabase("Give me a break! I have enough problems already!");
        return SUCCESSFUL_MSG_SENDING;
    }

    @Override
    public void sendInformationAfterCleaning() {
        someDatabaseService.saveDataToDatabase("My life is miserable...");
    }

    @Override
    public boolean isCleaningServiceOfType(TypeOfCleaningService typeOfCleaningService) {
        return TypeOfCleaningService.UNHAPPY == typeOfCleaningService;
    }
}
