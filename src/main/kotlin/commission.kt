import ru.netology.intInput
import ru.netology.menu


class OverLimitException(text: String) : Exception(text)


fun calculateCommission(transactionSum: Int,
                        monthSum: Int = 0,
                        cardType: String = "Мир", transactionLimit: Int, monthLimit: Int): Int {
    if (transactionSum > transactionLimit) {
        throw OverLimitException("Транзакция запрещена - сумма перевода превышает лимит")
    }
    if ((monthSum + transactionSum) > monthLimit) {
        throw OverLimitException("Транзакция запрещена - превышен лимит за месяц")
    }
    if (cardType == "Mastercard") {
        // За переводы с карты Mastercard комиссия не взимается,
        // пока не превышен месячный лимит в 75 000 руб.
        // Если лимит превышен, комиссия составит 0,6% + 20 руб.
        if (monthSum + transactionSum <= 75_000) {
            return 0
        } else if (monthSum >= 75_000) {
            return (transactionSum * 0.006 + 20).toInt()
        } else {
            return ((transactionSum - 75_000) * 0.006 + 20).toInt()
        }
    } else if (cardType == "Visa") {
        // За переводы с карты Visa комиссия составит 0,75%,
        // минимальная сумма комиссии 35 руб.
        return Math.max(35, (transactionSum * 0.0075).toInt())
    } else if (cardType == "Мир") {
        // За переводы с карты Мир комиссия не взимается.
        return 0
    } else {
        throw IllegalArgumentException("Unknown card type: $cardType")
    }
}

fun calculateCommissionDemo() {
    println("Тестируем функцию подсчёта комиссии")
    val transactionLimit = 150_000
    val monthLimit = 600_000
    while (true) {
        val transactionSum = intInput("Введите сумму перевода (отрицательная для выхода): ")
        if (transactionSum < 0) {
            println("Пока!")
            return
        }
        val monthSum = intInput("Введите сумму переводов за месяц (отрицательная для выхода): ")
        if (monthSum < 0) {
            println("До встречи!")
            return
        }
        val card = menu("Выберите тип карты", listOf("Visa", "Mastercard", "Мир"))
        try {
            val commission = calculateCommission(transactionSum, monthSum, card, transactionLimit, monthLimit)
            println("Комиссия составляет $commission р.")
        } catch (e: OverLimitException) {
            println(e.message)
        }

    }
}