package com.blogspot.toomuchcoding.topFeaturesYouNeedToKnowAbout.service.cleaning;

/**
 * User: mgrzejszczak
 * Date: 28.05.13
 * Time: 23:01
 */
public class NiceCleaningService implements CleaningService {
    private SomeWebservice someWebservice;

    @Override
    public void cleanTheTables() {
        System.out.println("I am a nice cleaning service so I won't be the best but who cares...?");
    }

    @Override
    public boolean sendInformationRightAfterBeingPicked() {
        someWebservice.sendDataThroughAWebService("Ok, I'll do it but then I'm going home");
        return SUCCESSFUL_MSG_SENDING;
    }

    @Override
    public void sendInformationAfterCleaning() {
        someWebservice.sendDataThroughAWebService("Ok, I'm done - I'm going home...");
    }

    @Override
    public boolean isCleaningServiceOfType(TypeOfCleaningService typeOfCleaningService) {
        return TypeOfCleaningService.NICE == typeOfCleaningService;
    }
}
