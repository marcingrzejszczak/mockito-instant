package com.blogspot.toomuchcoding.chapters.topFeaturesYouNeedToKnowAbout;

import com.blogspot.toomuchcoding.topFeaturesYouNeedToKnowAbout.service.AdministrativeStaffService;
import com.blogspot.toomuchcoding.topFeaturesYouNeedToKnowAbout.service.KitchenService;
import com.blogspot.toomuchcoding.topFeaturesYouNeedToKnowAbout.service.cleaning.CleaningService;
import com.blogspot.toomuchcoding.topFeaturesYouNeedToKnowAbout.service.cleaning.CleaningServiceFactoryImpl;
import com.blogspot.toomuchcoding.topFeaturesYouNeedToKnowAbout.service.cleaning.TypeOfCleaningService;
import com.blogspot.toomuchcoding.topFeaturesYouNeedToKnowAbout.service.cleaning.VeryFastCleaningService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.Arrays;

import static com.blogspot.toomuchcoding.topFeaturesYouNeedToKnowAbout.service.cleaning.CleaningService.SUCCESSFUL_MSG_SENDING;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;


public class _07ReduceBoilerplateCodeWithAnnotations {

    @Mock KitchenService kitchenServiceMock;
    @Mock AdministrativeStaffService administrativeStaffServiceMock;

    CleaningServiceFactoryImpl objectUnderTest;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        objectUnderTest = new CleaningServiceFactoryImpl();
    }

    @Spy  CleaningService veryFastCleaningServiceSpy = new VeryFastCleaningService();

    @Test
    public void shouldCallTheVeryFastCleaningService() {
        //given
        objectUnderTest.setCleaningServices(Arrays.asList(veryFastCleaningServiceSpy));
        doReturn(SUCCESSFUL_MSG_SENDING).when(veryFastCleaningServiceSpy).sendInformationRightAfterBeingPicked();

        //when
        CleaningService cleaningService = objectUnderTest.getMeACleaningService(TypeOfCleaningService.VERY_FAST);

        //then
        assertThat(cleaningService, is(veryFastCleaningServiceSpy));
        verify(veryFastCleaningServiceSpy).isCleaningServiceOfType(TypeOfCleaningService.VERY_FAST);
        verify(veryFastCleaningServiceSpy).sendInformationRightAfterBeingPicked();
    }
}

