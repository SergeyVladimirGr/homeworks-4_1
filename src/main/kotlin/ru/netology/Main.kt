package ru.netology

import kotlin.random.Random

const val ZERO_COMMISSION = 0
const val COMMISSION_MASTER_MAESTRO = 0.006
const val MIN_COMMISSION_MASTER_MAESTRO = 20
const val MIN_LIMIT_ACTION = 300_00
const val MAX_LIMIT_ACTION = 75_000_00
const val COMMISSION_VISA_MIR = 0.0075
const val MIN_COMMISSION_VISA_MIR = 35_00
const val LIMIT_DAY_CARD = 150_000_00
const val LIMIT_MONTH_CARD = 600_000_00
const val MAX_TRANSFER_VK = 15_000_00
const val LIMIT_MONTH_VK = 40_000_00
const val KOPECK = 100

enum class CardType {
    MasterCard, Maestro, Visa, Mir, VKPay
}

fun main() {
//    while (true) {
    print("Введите сумму перевода в рублях: ")
    val amountInKop = readln().toInt() * KOPECK

    val amountTransfersMonth = Random.nextInt(0,700_000) * KOPECK

    println(commission(amountInKop, CardType.Visa, amountTransfersMonth = 3_000))
}
//}
fun commission(amountInKop: Int,
               cardType: CardType = CardType.VKPay,
               amountTransfersMonth: Int = 0,
): String {
    return if (cardLimitDay(amountInKop) && cardLimitMonth(amountTransfersMonth)) {
        return when (cardType) {
            CardType.MasterCard -> "Комиссия составила ${commissionMasterMaestro(amountInKop)} руб."
            CardType.Maestro -> "Комиссия составила ${commissionMasterMaestro(amountInKop)} руб."
            CardType.Visa -> "Комиссия составила ${commissionVisaMir(amountInKop)} руб."
            CardType.Mir -> "Комиссия составила ${commissionVisaMir(amountInKop)} руб."
            CardType.VKPay -> commissionVk(amountInKop, amountTransfersMonth)
        }
    }  else "Вы превысили лимит"
}

fun limitAction(amountInKop: Int) = amountInKop in MIN_LIMIT_ACTION .. MAX_LIMIT_ACTION
fun cardLimitDay(amountInKop: Int) = amountInKop <= LIMIT_DAY_CARD
fun cardLimitMonth(amountTransfersMonth: Int) = amountTransfersMonth <= LIMIT_MONTH_CARD
fun vkLimitTransfer(amountInKop: Int) = amountInKop <= MAX_TRANSFER_VK
fun vkLimitMonth(amountTransfersMonth: Int) = amountTransfersMonth <= LIMIT_MONTH_VK

fun commissionMasterMaestro(amountInKop: Int): Int {
    val result = if (limitAction(amountInKop)) ZERO_COMMISSION else (amountInKop * COMMISSION_MASTER_MAESTRO) / KOPECK + MIN_COMMISSION_MASTER_MAESTRO
    return result.toInt()
}

fun commissionVisaMir(amountInKop: Int): Int {
    val commission: Double = amountInKop * COMMISSION_VISA_MIR
    val result = if (commission >= MIN_COMMISSION_VISA_MIR) commission / KOPECK else MIN_COMMISSION_VISA_MIR / KOPECK
    return result.toInt()
}

fun commissionVk(amountInKop: Int, amountTransfersMonth: Int): String {
    return if (vkLimitTransfer(amountInKop) && vkLimitMonth(amountTransfersMonth)) "Комиссия составила $ZERO_COMMISSION руб." else "Вы превысили лимит"
}