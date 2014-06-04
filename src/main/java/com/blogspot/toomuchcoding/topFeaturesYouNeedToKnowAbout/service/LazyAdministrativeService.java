package com.blogspot.toomuchcoding.topFeaturesYouNeedToKnowAbout.service;

import com.blogspot.toomuchcoding.topFeaturesYouNeedToKnowAbout.exception.IAmTooLazyToDoItException;

/**
 * User: mgrzejszczak
 * Date: 20.05.13
 * Time: 16:46
 */
public class LazyAdministrativeService implements AdministrativeStaffService {
    @Override
    public boolean isPowerOnline() {
        throw new IAmTooLazyToDoItException();
    }
}
