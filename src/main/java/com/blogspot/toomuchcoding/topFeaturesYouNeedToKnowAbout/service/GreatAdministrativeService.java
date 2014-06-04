package com.blogspot.toomuchcoding.topFeaturesYouNeedToKnowAbout.service;

/**
 * User: mgrzejszczak
 * Date: 20.05.13
 * Time: 16:44
 */
public class GreatAdministrativeService implements AdministrativeStaffService {
    private static final boolean POWER_ALWAYS_ON = true;

    @Override
    public boolean isPowerOnline() {
        return POWER_ALWAYS_ON;
    }
}
