package com.blogspot.toomuchcoding.chapters.topFeaturesYouNeedToKnowAbout;

import com.blogspot.toomuchcoding.topFeaturesYouNeedToKnowAbout.meal.Meal;
import com.blogspot.toomuchcoding.topFeaturesYouNeedToKnowAbout.meal.VegetarianFirstCourse;
import com.blogspot.toomuchcoding.topFeaturesYouNeedToKnowAbout.service.AdministrativeStaffService;
import com.blogspot.toomuchcoding.topFeaturesYouNeedToKnowAbout.exception.IAmTooLazyToDoItException;
import com.blogspot.toomuchcoding.topFeaturesYouNeedToKnowAbout.service.KitchenService;
import com.blogspot.toomuchcoding.topFeaturesYouNeedToKnowAbout.service.WaiterImpl;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;
import org.mockito.exceptions.verification.VerificationInOrderFailure;
import org.mockito.exceptions.verification.WantedButNotInvoked;

import java.util.ArrayList;
import java.util.List;

import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

public class _05VerifyingBehaviorTest {

    private static final boolean POWER_ONLINE_SUCCESSFULLY = true;
    private static final boolean OUTFIT_CHANGED_SUCCESSFULLY = true;
    private static final boolean KITCHEN_PREPARED_SUCCESSFULLY = true;

    KitchenService kitchenServiceMock = mock(KitchenService.class);
    AdministrativeStaffService administrativeStaffServiceMock = mock(AdministrativeStaffService.class);

    @Test
    public void shouldCalculateTotalWaitingTimeAndAssertTheArgumentsOnMockUsingArgumentCaptor() throws Exception {
        //given
        final int mealPreparationTime = 10;
        when(kitchenServiceMock.calculatePreparationTime(any(Meal.class))).thenReturn(mealPreparationTime);
        WaiterImpl objectUnderTest = new WaiterImpl(kitchenServiceMock);

        //when
        int waitingTime = objectUnderTest.calculateTotalWaitingTime(createSampleMealsContainingVegetarianFirstCourse());

        //then
        assertThat(waitingTime, is(mealPreparationTime));
        ArgumentCaptor<Meal> mealArgumentCaptor = ArgumentCaptor.forClass(Meal.class);
        verify(kitchenServiceMock).calculatePreparationTime(mealArgumentCaptor.capture());
        assertThat(mealArgumentCaptor.getValue(), is(VegetarianFirstCourse.class));
        assertThat(mealArgumentCaptor.getAllValues().size(), is(1));
    }

    private List<Meal> createSampleMealsContainingVegetarianFirstCourse() {
        List<Meal> meals = new ArrayList<Meal>();
        meals.add(new VegetarianFirstCourse());
        return meals;
    }

    @Test
    public void shouldThrowExceptionWhenRestaurantNotReadyToTakeAnOrder() throws Exception {
        //given
        WaiterImpl objectUnderTest = new WaiterImpl(kitchenServiceMock, administrativeStaffServiceMock);
        when(administrativeStaffServiceMock.isPowerOnline()).thenReturn(POWER_ONLINE_SUCCESSFULLY);
        when(kitchenServiceMock.isOutfitChanged()).thenThrow(new IAmTooLazyToDoItException());

        //when
        catchException(objectUnderTest).readyToTakeAnOrder();

        //then
        assertThat(caughtException(), is(IAmTooLazyToDoItException.class));
        verify(administrativeStaffServiceMock).isPowerOnline();
        verify(kitchenServiceMock).isOutfitChanged();
        verify(kitchenServiceMock, times(1)).isOutfitChanged();
        verify(kitchenServiceMock, atMost(1)).isOutfitChanged();
        verify(kitchenServiceMock, atLeastOnce()).isOutfitChanged();
        verify(kitchenServiceMock, atLeast(1)).isOutfitChanged();
        verify(kitchenServiceMock, only()).isOutfitChanged();
        verify(kitchenServiceMock, never()).isKitchenPrepared();
    }

    @Test
    public void shouldReturnTrueForPowerOnlineAndKitchenServiceReadyVerificationIdOrder() throws Exception {
        //given
        WaiterImpl objectUnderTest = new WaiterImpl(kitchenServiceMock, administrativeStaffServiceMock);
        when(administrativeStaffServiceMock.isPowerOnline()).thenReturn(POWER_ONLINE_SUCCESSFULLY);
        when(kitchenServiceMock.isOutfitChanged()).thenReturn(OUTFIT_CHANGED_SUCCESSFULLY);
        when(kitchenServiceMock.isKitchenPrepared()).thenReturn(KITCHEN_PREPARED_SUCCESSFULLY);

        //when
        boolean readyToTakeAnOrder = objectUnderTest.readyToTakeAnOrder();

        //then
        assertThat(readyToTakeAnOrder, is(true));
        InOrder inOrder = inOrder(administrativeStaffServiceMock, kitchenServiceMock);
        inOrder.verify(administrativeStaffServiceMock).isPowerOnline();
        inOrder.verify(kitchenServiceMock).isOutfitChanged();
        inOrder.verify(kitchenServiceMock).isKitchenPrepared();
    }

    @Test
    public void shouldThrowAnErrorForGreedyAtLeastUsedInInOrderVerification() throws Exception {
        //given
        WaiterImpl objectUnderTest = new WaiterImpl(kitchenServiceMock, administrativeStaffServiceMock);

        //when
        objectUnderTest.bullyOtherWorkers();

        //then
        try {
            InOrder inOrder = inOrder(administrativeStaffServiceMock, kitchenServiceMock);
            inOrder.verify(administrativeStaffServiceMock).isPowerOnline();
            inOrder.verify(kitchenServiceMock, atLeast(2)).isOutfitChanged();
            inOrder.verify(kitchenServiceMock).isKitchenPrepared();
            fail();
        } catch (VerificationInOrderFailure verificationInOrderFailure) {
            System.err.printf("Expected the failure [%s] to happen since atLeast() is greedy.%n", verificationInOrderFailure);
        }
    }

    @Test
    public void shouldCheckOrderOfExecutionUsingNonGreedyCallsInInOrderVerification() throws Exception {
        //given
        WaiterImpl objectUnderTest = new WaiterImpl(kitchenServiceMock, administrativeStaffServiceMock);

        //when
        objectUnderTest.bullyOtherWorkers();

        //then
        InOrder inOrder = inOrder(administrativeStaffServiceMock, kitchenServiceMock);
        inOrder.verify(administrativeStaffServiceMock).isPowerOnline();
        inOrder.verify(kitchenServiceMock, calls(2)).isOutfitChanged();
        inOrder.verify(kitchenServiceMock).isKitchenPrepared();
        inOrder.verify(kitchenServiceMock).isOutfitChanged();
    }

    @Test
    public void shouldThrowErrorWhenTheVerifiedMethodHasNotBeenExecutedDuringGivenTime() throws Exception {
        //given
        final WaiterImpl objectUnderTest = new WaiterImpl(kitchenServiceMock);

        //when
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    System.err.printf("[%s] occurred while trying to make the thread sleep%n", e);
                }
                objectUnderTest.closeTheRestaurant();
            }
        }).start();

        //then
        try {
            verify(kitchenServiceMock, timeout(10)).cleanTheKitchen();
            fail();
        } catch (WantedButNotInvoked wantedButNotInvokedError) {
            System.err.printf("Exception [%s] occurred. The mock's method hasn't been called during the specified timeout%n", wantedButNotInvokedError);
        }
    }

}

