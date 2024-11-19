package ru.netology

fun agoToText(secondsAgo: Int): String {
    if (secondsAgo <= 0) {
        throw IllegalArgumentException("secondsAgo must be greater than 0")
    }
    if (secondsAgo < 60) {
        return "был(а) только что"
    } else if (secondsAgo < 60*60) {
        // less than an hour -> depict minutes
        return "был(а) " + minutesAgoToText(secondsAgo / 60)
    } else if (secondsAgo < 60*60*24) {
        // less than 24 hours -> depict hours
        return "был(а) " + hoursAgoToText(secondsAgo / (60*60))
    } else if (secondsAgo < 60*60*24*2) {
        // less than 48 hours -> depict "was yesterday"
        return "был(а) вчера"
    } else if (secondsAgo < 60*60*24*3) {
        // less than 72 hours -> depict "was the day before yesterday"
        return "был(а) позавчера"
    }
    return "был(а) давно"
}

fun minutesAgoToText(minutesAgo: Int): String {
    return when (minutesAgo) {
        1, 21, 31, 41, 51 -> "$minutesAgo минуту назад"
        2, 3, 4, 22, 23, 24, 32, 33, 34, 42, 43, 44, 52, 53, 54 -> "$minutesAgo минуты назад"
        else -> "$minutesAgo минут назад"
    }
}

fun hoursAgoToText(hoursAgo: Int): String {
    return when (hoursAgo) {
        1, 21 -> "$hoursAgo час назад"
        2, 3, 4, 22, 23 -> "$hoursAgo часа назад"
        else -> "$hoursAgo часов назад"
    }
}

fun agoToTextDemo() {
    println("Тестируем функцию, возвращающую сообщение по количеству секунд")
    while (true) {
        val seconds = intInput("Количество секунд (отрицательное для выхода): ")
        if (seconds <= 0) {
            println("Пока!")
            return
        }
        val message = agoToText(seconds)
        println(message)
    }
}







