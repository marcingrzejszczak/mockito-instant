package com.blogspot.toomuchcoding.topFeaturesYouNeedToKnowAbout.service.cleaning;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * User: mgrzejszczak
 * Date: 28.05.13
 * Time: 23:19
 */
public class CleaningServiceFactoryImpl implements CleaningServiceFactory {

    private List<CleaningService> cleaningServices;

    @Override
    public CleaningService getMeACleaningService(TypeOfCleaningService typeOfCleaningService) {
        for (CleaningService cleaningService : cleaningServices) {
            if (cleaningService.isCleaningServiceOfType(typeOfCleaningService)) {
                cleaningService.sendInformationRightAfterBeingPicked();
                return cleaningService;
            }
        }
        throw new NoSuchElementException("Houston, we have a problem, there is no such cleaning service available...");
    }

    /*
        Getters and setters for the List<CleaningService> cleaningServices
     */

    public List<CleaningService> getCleaningServices() {
        return cleaningServices;
    }

    public void setCleaningServices(List<CleaningService> cleaningServices) {
        this.cleaningServices = cleaningServices;
    }
}
