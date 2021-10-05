// function

// class

// string template

val month = "(JAN|FEB|MAR|APR|MAY|JUN|JUL|AUG|SEP|OCT|NOV|DEC)"

//fun getPattern() = """\d{2}\.\d{2}\.\d{4}"""
fun getPattern(): String = TODO()

fun evaluatePattern() {
    assert(Regex(getPattern()).matches("13 JUN 1992"))
}

// for loop

class DateRange(val start: MyDate, val end: MyDate)

fun iterateOverDateRange(firstDate: MyDate, secondDate: MyDate, handler: (MyDate) -> Unit) {
    for (date in firstDate..secondDate) {
        handler(date)
    }
}

// collection