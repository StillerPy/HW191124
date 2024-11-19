package ru.netology


fun intInput(message: String, min: Int = 0, max: Int = 100_000): Int {
    print(message)
    try {
        val out = readln().toInt()
        if (out < min) {
            println("\tЧисло должно быть не меньше $min!")
            return intInput(message, min, max)
        }
        if (out > max) {
            println("\tЧисло должно быть не больше $max!")
            return intInput(message, min, max)
        }
        return out
    } catch (e: NumberFormatException) {
        println("\tНеверно введено число!")
        return intInput(message, min, max)
    }
}

fun intInput(message: String): Int {
    print(message)
    try {
        return readln().toInt()
    } catch (e: NumberFormatException) {
        println("\tНеверно введено число!")
        return intInput(message)
    }
}

fun boolInput(message: String): Boolean {
    val yesCommands = arrayOf("д", "да", "y", "yes", "1")
    val noCommands = arrayOf("н", "не", "нет", "no", "n", "0")
    print(message)
    val command = readln().lowercase().replace(" ", "")
    if (yesCommands.contains(command)) {
        return true
    } else if (noCommands.contains(command)) {
        return false
    } else {
        println("\tИспользуйте команды: ${yesCommands.joinToString(", ")}, ${noCommands.joinToString(", ")}")
        return boolInput(message)
    }
}

fun <T> menu(title: String, options: List<T>): T {
    println(title)
    for (i in options.indices) {
        println("\t${i + 1}) ${options[i]}")
    }
    return options[intInput("Введите номер: ", 1, options.size) - 1]
}