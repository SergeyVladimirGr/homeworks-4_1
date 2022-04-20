package ru.netology

import org.junit.Test

import org.junit.Assert.*

class MainKtTest {

    @Test
    fun commission_MasterCard() {
        // arrange
        val amountInKop = 75_000_00
        val cardType = CardType.MasterCard
        val amountTransfersMonth = 601_000_00
        val expectedCommission = "Комиссия составила $ZERO_COMMISSION руб."
        // act
        val actualCommission = commission(amountInKop, cardType, amountTransfersMonth)
        // assert
        assertEquals(expectedCommission, actualCommission)
    }
    @Test
    fun commission_Maestro() {
        // arrange
        val amountInKop = 75_000_00
        val cardType = CardType.Maestro
        val amountTransfersMonth = 600_000_00
        val expectedCommission = "Комиссия составила $ZERO_COMMISSION руб."
        // act
        val actualCommission = commission(amountInKop, cardType, amountTransfersMonth)
        // assert
        assertEquals(expectedCommission, actualCommission)
    }
    @Test
    fun commission_Visa() {
        // arrange
        val amountInKop = 75_000_00
        val cardType = CardType.Visa
        val amountTransfersMonth = 600_000_00
        val expectedCommission = "Комиссия составила 562 руб."
        // act
        val actualCommission = commission(amountInKop, cardType, amountTransfersMonth)
        // assert
        assertEquals(expectedCommission, actualCommission)
    }
    @Test
    fun commission_Mir() {
        // arrange
        val amountInKop = 75_000_00
        val cardType = CardType.Mir
        val amountTransfersMonth = 600_000_00
        val expectedCommission = "Комиссия составила 562 руб."
        // act
        val actualCommission = commission(amountInKop, cardType, amountTransfersMonth)
        // assert
        assertEquals(expectedCommission, actualCommission)
    }
    @Test
    fun commission_VKPay() {
        // arrange
        val amountInKop = 15_000_00
        val cardType = CardType.VKPay
        val amountTransfersMonth = 40_000_00
        val expectedCommission = "Комиссия составила $ZERO_COMMISSION руб."
        // act
        val actualCommission = commission(amountInKop, cardType, amountTransfersMonth)
        // assert
        assertEquals(expectedCommission, actualCommission)
    }
    @Test
    fun commission_calculationWhenLimitExceededMonth() {
        // arrange
        val amountInKop = 150_000_00
        val cardType = CardType.Visa
        val amountTransfersMonth = 650_000_00
        val expectedCommission = "Вы превысили лимит"
        // act
        val actualCommission = commission(amountInKop, cardType, amountTransfersMonth)
        // assert
        assertEquals(expectedCommission, actualCommission)
    }
    @Test
    fun commission_calculationWhenLimitExceededDay() {
        // arrange
        val amountInKop = 200_000_00
        val cardType = CardType.Visa
        val amountTransfersMonth = 600_000_00
        val expectedCommission = "Вы превысили лимит"
        // act
        val actualCommission = commission(amountInKop, cardType, amountTransfersMonth)
        // assert
        assertEquals(expectedCommission, actualCommission)
    }

    @Test
    fun limitAction_checkStockTransferRange() {
        // arrange
        val amountInKop = 75_000_00
        val expectedLimitAction = true
        // act
        val actualLimitAction = limitAction(amountInKop)
        // assert
        assertEquals(expectedLimitAction, actualLimitAction)
    }

    @Test
    fun cardLimitDay_checkTransferLimitOnCard() {
        // arrange
        val amountInKop = 150_000_00
        val expectedLimitDay = true
        // act
        val actualLimitDay = cardLimitDay(amountInKop)
        // assert
        assertEquals(expectedLimitDay, actualLimitDay)
    }

    @Test
    fun cardLimitMonth_checkAmountTransferPerMonth() {
        // arrange
        val amountTransfersMonth = 600_000_00
        val expectedLimitMonth = true
        // act
        val actualLimitMonth = cardLimitMonth(amountTransfersMonth)
        // assert
        assertEquals(expectedLimitMonth, actualLimitMonth)
    }

    @Test
    fun vkLimitTransfer_checkTransferLimitVk() {
        // arrange
        val amountInKop = 15_000_00
        val expectedVkLimitTransfer = true
        // act
        val actualVkLimitTransfer = vkLimitTransfer(amountInKop)
        // assert
        assertEquals(expectedVkLimitTransfer, actualVkLimitTransfer)
    }

    @Test
    fun vkLimitMonth() {
        // arrange
        val amountTransfersMonth = 40_000_00
        val expectedVkLimitMonth = true
        // act
        val actualVkLimitMonth = vkLimitMonth(amountTransfersMonth)
        // assert
        assertEquals(expectedVkLimitMonth, actualVkLimitMonth)
    }

    @Test
    fun commissionMasterMaestro_stockRangeCalculation() {
        // arrange
        val amountInKop = 75_000_00
        val expectedCommissionMasterMaestro = ZERO_COMMISSION
        // act
        val actualCommissionMasterMaestro = commissionMasterMaestro(amountInKop)
        // assert
        assertEquals(expectedCommissionMasterMaestro, actualCommissionMasterMaestro)
    }
    @Test
    fun commissionMasterMaestro_calculationIsNotStockRange() {
        // arrange
        val amountInKop = 200_00
        val expectedCommissionMasterMaestro = 21
        // act
        val actualCommissionMasterMaestro = commissionMasterMaestro(amountInKop)
        // assert
        assertEquals(expectedCommissionMasterMaestro, actualCommissionMasterMaestro)
    }

    @Test
    fun commissionVisaMir_calculationExceedingMinCommission() {
        // arrange
        val amountInKop = 5_000_00
        val expectedCommissionVisaMir = 37
        // act
        val actualCommissionVisaMir = commissionVisaMir(amountInKop)
        // assert
        assertEquals(expectedCommissionVisaMir, actualCommissionVisaMir)
    }
    @Test
    fun commissionVisaMir_calculationBelowMinCommission() {
        // arrange
        val amountInKop = 3_000_00
        val expectedCommissionVisaMir = MIN_COMMISSION_VISA_MIR / KOPECK
        // act
        val actualCommissionVisaMir = commissionVisaMir(amountInKop)
        // assert
        assertEquals(expectedCommissionVisaMir, actualCommissionVisaMir)
    }

    @Test
    fun commissionVk_calculationCommissionVk() {
        // arrange
        val amountInKop = 3_000_00
        val amountTransfersMonth = 40_000_00
        val expectedCommissionVk = "Комиссия составила $ZERO_COMMISSION руб."
        // act
        val actualCommissionVk = commissionVk(amountInKop, amountTransfersMonth)
        // assert
        assertEquals(expectedCommissionVk, actualCommissionVk)
    }
    @Test
    fun commissionVk_calculationCommissionVkWhenLimitExceeded() {
        // arrange
        val amountInKop = 20_000_00
        val amountTransfersMonth = 40_000_00
        val expectedCommissionVk = "Вы превысили лимит"
        // act
        val actualCommissionVk = commissionVk(amountInKop, amountTransfersMonth)
        // assert
        assertEquals(expectedCommissionVk, actualCommissionVk)
    }
}