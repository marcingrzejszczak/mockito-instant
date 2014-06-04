package com.blogspot.toomuchcoding.chapters.topFeaturesYouNeedToKnowAbout;

import com.blogspot.toomuchcoding.topFeaturesYouNeedToKnowAbout.exception.IAmTooLazyToDoItException;
import com.blogspot.toomuchcoding.topFeaturesYouNeedToKnowAbout.service.*;
import org.junit.Test;
import org.mockito.Mockito;

import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class _03WorkingWithVoidMethodsAndThrownExceptionsTest {


    KitchenService kitchenServiceMock = mock(KitchenService.class);

    @Test
    public void shouldReturnTrueIfKitchenNotCleanedPreviously() throws Exception {
        //given
        WaiterImpl objectUnderTest = new WaiterImpl(kitchenServiceMock);

        //when
        boolean canCloseRestaurant = objectUnderTest.closeTheRestaurant();

        //then
        assertThat(canCloseRestaurant, is(true));
        verify(kitchenServiceMock).cleanTheKitchen();
    }

    @Test
    public void shouldReturnFalseIfAnExceptionHasBeenThrownWhileCleaning() throws Exception {
        //given
        WaiterImpl objectUnderTest = new WaiterImpl(kitchenServiceMock);
        Mockito.doThrow(new IAmTooLazyToDoItException()).when(kitchenServiceMock).cleanTheKitchen();

        //when
        boolean canCloseRestaurant = objectUnderTest.closeTheRestaurant();

        //then
        assertThat(canCloseRestaurant, is(false));
        verify(kitchenServiceMock).cleanTheKitchen();
    }

    @Test
    public void shouldReturnFalseWhenTryingToCloseRestaurantWhenKitchenServiceIsLazy() throws Exception {
        //given
        WaiterImpl objectUnderTest = new WaiterImpl(kitchenServiceMock);
        doThrow(new IAmTooLazyToDoItException()).doNothing().when(kitchenServiceMock).cleanTheKitchen();

        //when
        boolean canCloseRestaurantWhenKitchenServiceIsLazy = objectUnderTest.closeTheRestaurant();
        boolean canCloseRestaurant = objectUnderTest.closeTheRestaurant();

        //then
        assertThat(canCloseRestaurantWhenKitchenServiceIsLazy, is(false));
        assertThat(canCloseRestaurant, is(true));
        verify(kitchenServiceMock, times(2)).cleanTheKitchen();
    }

    @Test(expected = IAmTooLazyToDoItException.class)
    public void shouldThrowAnExceptionWhenCheckingForChangedOutfitButInFactAdministrativeServiceThrowsIt() throws Exception {
        //given
        WaiterImpl objectUnderTest = new WaiterImpl(kitchenServiceMock, new LazyAdministrativeService());
        when(kitchenServiceMock.isOutfitChanged()).thenThrow(new IAmTooLazyToDoItException());

        //when
        objectUnderTest.readyToTakeAnOrder();
    }

    @Test
    public void shouldThrowAnExceptionWhenCheckingForChangedOutfitAndNoMoreInteractionsTakePlaceInKitchen() throws Exception {
        //given
        WaiterImpl objectUnderTest = new WaiterImpl(kitchenServiceMock, new GreatAdministrativeService());
        when(kitchenServiceMock.isOutfitChanged()).thenThrow(new IAmTooLazyToDoItException());

        //when
        try {
            objectUnderTest.readyToTakeAnOrder();
            fail();
        } catch (IAmTooLazyToDoItException exception) {

        }

        //then
        verify(kitchenServiceMock).isOutfitChanged();
        verifyNoMoreInteractions(kitchenServiceMock);
    }

    @Test
    public void shouldThrowAnExceptionWhenCheckingForChangedOutfitAndNoMoreInteractionsTakePlaceInKitchenUsingCatchException() throws Exception {
        //given
        WaiterImpl objectUnderTest = new WaiterImpl(kitchenServiceMock, new GreatAdministrativeService());
        when(kitchenServiceMock.isOutfitChanged()).thenThrow(new IAmTooLazyToDoItException());

        //when
        catchException(objectUnderTest).readyToTakeAnOrder();

        //then
        assertThat(caughtException(), is(IAmTooLazyToDoItException.class));
        verify(kitchenServiceMock).isOutfitChanged();
        verifyNoMoreInteractions(kitchenServiceMock);
    }
}
