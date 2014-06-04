package com.blogspot.toomuchcoding.chapters.topFeaturesYouNeedToKnowAbout;

import com.blogspot.toomuchcoding.topFeaturesYouNeedToKnowAbout.service.WaiterImpl;
import com.blogspot.toomuchcoding.topFeaturesYouNeedToKnowAbout.service.cleaning.CleaningServiceFactory;
import com.blogspot.toomuchcoding.topFeaturesYouNeedToKnowAbout.service.cleaning.TypeOfCleaningService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.exceptions.verification.SmartNullPointerException;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.withSettings;

@RunWith(MockitoJUnitRunner.class)
public class _08TakeAdvantageOfAdvancedMocksConfigurationTest {

    @Mock(answer = Answers.RETURNS_SMART_NULLS) CleaningServiceFactory cleaningServiceFactory;

    @InjectMocks WaiterImpl objectUnderTest;

    @Test
    public void shouldThrowSmartNullPointerExceptionWhenUsingUnstubbedMethod() {
        //given
        // Oops forgotten to stub the CleaningServiceFactory.getMeACleaningService(TypeOfCleaningService) method

        try {
            //when
            objectUnderTest.askTheCleaningServiceToCleanTheRestaurant(TypeOfCleaningService.VERY_FAST);
            fail();
        } catch (SmartNullPointerException smartNullPointerException) {
            System.err.println("A SmartNullPointerException will be thrown here with a very precise information about the error [" + smartNullPointerException + "]");
        }
    }

    @Test
    public void shouldThrowSmartNullPointerExceptionWhenUsingUnstubbedMethodWithoutExplicitlyProvidingTheAnswerForMock() {
        //given
        CleaningServiceFactory cleaningServiceFactoryMock = mock(CleaningServiceFactory.class, withSettings());
        WaiterImpl objectUnderTest = new WaiterImpl(cleaningServiceFactoryMock);

        // Oops forgotten to stub the CleaningServiceFactory.getMeACleaningService(TypeOfCleaningService) method

        try {
            //when
            objectUnderTest.askTheCleaningServiceToCleanTheRestaurant(TypeOfCleaningService.VERY_FAST);
            fail();
        } catch (SmartNullPointerException smartNullPointerException) {
            System.err.println("A SmartNullPointerException will be thrown here with a very precise information about the error [" + smartNullPointerException + "]");
        }
    }



}

