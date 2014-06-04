package com.blogspot.toomuchcoding.chapters.topFeaturesYouNeedToKnowAbout;

import com.blogspot.toomuchcoding.topFeaturesYouNeedToKnowAbout.service.AdministrativeStaffService;
import com.blogspot.toomuchcoding.topFeaturesYouNeedToKnowAbout.service.KitchenService;
import com.blogspot.toomuchcoding.topFeaturesYouNeedToKnowAbout.service.WaiterImpl;
import com.blogspot.toomuchcoding.topFeaturesYouNeedToKnowAbout.service.cleaning.CleaningService;
import com.blogspot.toomuchcoding.topFeaturesYouNeedToKnowAbout.service.cleaning.CleaningServiceFactory;
import com.blogspot.toomuchcoding.topFeaturesYouNeedToKnowAbout.service.cleaning.TypeOfCleaningService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class _08TakeAdvantageOfAdvancedMocksConfigurationExtraInterfacesTest {

    @Mock(extraInterfaces = {KitchenService.class, AdministrativeStaffService.class}) CleaningService cleaningService;

    @Mock CleaningServiceFactory cleaningServiceFactory;

    @InjectMocks WaiterImpl objectUnderTest;

    @Test
    public void shouldReturnTrueWhenMockThatIsBothCleaningServiceAdministrativeStaffServiceAndKitchenServiceIsReturnedFromCleaningServiceFactory() {
        //given
        when(cleaningServiceFactory.getMeACleaningService(any(TypeOfCleaningService.class))).thenReturn(cleaningService);
        when(((AdministrativeStaffService)cleaningService).isPowerOnline()).thenReturn(true);

        //when
        boolean preparedToStart = objectUnderTest.prepareTheRestaurantToStart();

        //then
        assertThat(preparedToStart, is(true));
        verify(cleaningService).cleanTheTables();
        verify((AdministrativeStaffService)cleaningService).isPowerOnline();
        verify((KitchenService)cleaningService).cleanTheKitchen();
    }

    @Test
    public void shouldReturnTrueWhenMockThatIsBothCleaningServiceAdministrativeStaffServiceAndKitchenServiceIsReturnedFromCleaningServiceFactoryWithoutAnnotations() {
        //given
        CleaningService cleaningService = mock(CleaningService.class, withSettings().extraInterfaces(KitchenService.class, AdministrativeStaffService.class));
        CleaningServiceFactory cleaningServiceFactory = mock(CleaningServiceFactory.class);
        when(cleaningServiceFactory.getMeACleaningService(any(TypeOfCleaningService.class))).thenReturn(cleaningService);
        when(((AdministrativeStaffService)cleaningService).isPowerOnline()).thenReturn(true);
        WaiterImpl objectUnderTest = new WaiterImpl(cleaningServiceFactory);

        //when
        boolean preparedToStart = objectUnderTest.prepareTheRestaurantToStart();

        //then
        assertThat(preparedToStart, is(true));
        verify(cleaningService).cleanTheTables();
        verify((AdministrativeStaffService)cleaningService).isPowerOnline();
        verify((KitchenService)cleaningService).cleanTheKitchen();
    }

    @Test
    public void shouldReturnFalseWhenMockCleaningServiceIsReturnedFromCleaningServiceFactory() {
        //given
        CleaningService cleaningService = mock(CleaningService.class);
        CleaningServiceFactory cleaningServiceFactory = mock(CleaningServiceFactory.class);
        when(cleaningServiceFactory.getMeACleaningService(any(TypeOfCleaningService.class))).thenReturn(cleaningService);
        WaiterImpl objectUnderTest = new WaiterImpl(cleaningServiceFactory);

        //when
        boolean preparedToStart = objectUnderTest.prepareTheRestaurantToStart();

        //then
        assertThat(preparedToStart, is(false));
        verify(cleaningService).cleanTheTables();
    }

}

