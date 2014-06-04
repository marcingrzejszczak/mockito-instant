package com.blogspot.toomuchcoding.topFeaturesYouNeedToKnowAbout.service.cleaning;

/**
 * User: mgrzejszczak
 * Date: 28.05.13
 * Time: 23:01
 */
public class VeryFastCleaningService implements CleaningService {
    private SomeWebservice someWebservice;

    @Override
    public void cleanTheTables() {
        System.out.println("I am a very fast cleaning service so I'll clean these tables extremely fast!");
    }

    @Override
    public boolean sendInformationRightAfterBeingPicked() {
        someWebservice.sendDataThroughAWebService("Ok, I'll do it as fast as possible!");
        return SUCCESSFUL_MSG_SENDING;
    }

    @Override
    public void sendInformationAfterCleaning() {
        someWebservice.sendDataThroughAWebService("I'll text my boss that I'm the best!");
    }

    @Override
    public boolean isCleaningServiceOfType(TypeOfCleaningService typeOfCleaningService) {
        return TypeOfCleaningService.VERY_FAST == typeOfCleaningService;
    }
}
