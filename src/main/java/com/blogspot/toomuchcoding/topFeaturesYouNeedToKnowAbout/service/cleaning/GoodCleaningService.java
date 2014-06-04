package com.blogspot.toomuchcoding.topFeaturesYouNeedToKnowAbout.service.cleaning;

/**
 * User: mgrzejszczak
 * Date: 28.05.13
 * Time: 23:01
 */
public class GoodCleaningService implements CleaningService {
    private SomeWebservice someWebservice;

    @Override
    public void cleanTheTables() {
        System.out.println("I am a good cleaning service so I'll do very good cleaning...");
    }

    @Override
    public boolean sendInformationRightAfterBeingPicked() {
        someWebservice.sendDataThroughAWebService("I am happy that I will be able to clean the table");
        return SUCCESSFUL_MSG_SENDING;
    }

    @Override
    public void sendInformationAfterCleaning() {
        someWebservice.sendDataThroughAWebService("I'll inform my manager that I should get a raise!");
    }

    @Override
    public boolean isCleaningServiceOfType(TypeOfCleaningService typeOfCleaningService) {
        return TypeOfCleaningService.GOOD == typeOfCleaningService;
    }
}
