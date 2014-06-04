package com.blogspot.toomuchcoding.chapters.topFeaturesYouNeedToKnowAbout;

import com.blogspot.toomuchcoding.topFeaturesYouNeedToKnowAbout.service.cleaning.CleaningService;
import com.blogspot.toomuchcoding.topFeaturesYouNeedToKnowAbout.service.cleaning.CleaningServiceFactoryImpl;
import com.blogspot.toomuchcoding.topFeaturesYouNeedToKnowAbout.service.cleaning.TypeOfCleaningService;
import com.blogspot.toomuchcoding.topFeaturesYouNeedToKnowAbout.service.cleaning.VeryFastCleaningService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.exceptions.base.MockitoException;

import java.util.Arrays;
import java.util.NoSuchElementException;

import static com.blogspot.toomuchcoding.topFeaturesYouNeedToKnowAbout.service.cleaning.CleaningService.SUCCESSFUL_MSG_SENDING;
import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class _06DoPartialMockingSpyingTest {

    private CleaningServiceFactoryImpl objectUnderTest;

    @Before
    public void setUp() {
        objectUnderTest = new CleaningServiceFactoryImpl();
    }

    @Test
    public void shouldCallTheVeryFastCleaningService() {
        //given
        CleaningService veryFastCleaningServiceSpy = spy(new VeryFastCleaningService());
        objectUnderTest.setCleaningServices(Arrays.asList(veryFastCleaningServiceSpy));
        doReturn(SUCCESSFUL_MSG_SENDING).when(veryFastCleaningServiceSpy).sendInformationRightAfterBeingPicked();

        //when
        CleaningService cleaningService = objectUnderTest.getMeACleaningService(TypeOfCleaningService.VERY_FAST);

        //then
        assertThat(cleaningService, is(veryFastCleaningServiceSpy));
        verify(veryFastCleaningServiceSpy).isCleaningServiceOfType(TypeOfCleaningService.VERY_FAST);
        verify(veryFastCleaningServiceSpy).sendInformationRightAfterBeingPicked();
        verify(veryFastCleaningServiceSpy, never()).sendInformationAfterCleaning();
    }

    @Test
    public void shouldThrowExceptionWhenForGivenTypeThereIsNoCleaningServiceAvailable() {
        //given
        CleaningService veryFastCleaningServiceSpy = spy(new VeryFastCleaningService());
        objectUnderTest.setCleaningServices(Arrays.asList(veryFastCleaningServiceSpy));

        //when
        catchException(objectUnderTest).getMeACleaningService(TypeOfCleaningService.BORED);

        //then
        assertThat(caughtException(), is(NoSuchElementException.class));
        verify(veryFastCleaningServiceSpy).isCleaningServiceOfType(TypeOfCleaningService.BORED);
        verify(veryFastCleaningServiceSpy, never()).sendInformationRightAfterBeingPicked();
        verify(veryFastCleaningServiceSpy, never()).sendInformationAfterCleaning();
    }

    @Test
    public void shouldThrowExceptionWhenMockingInterfaceWithCallRealMethodsAnswer() {
        //given
        CleaningService veryFastCleaningServiceSpy = mock(CleaningService.class, CALLS_REAL_METHODS);
        objectUnderTest.setCleaningServices(Arrays.asList(veryFastCleaningServiceSpy));

        //when
        catchException(objectUnderTest).getMeACleaningService(TypeOfCleaningService.VERY_FAST);

        //then
        assertThat(caughtException(), is(MockitoException.class));
    }
}