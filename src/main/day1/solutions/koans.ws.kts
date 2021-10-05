// function

// class

// string template

val month = "(JAN|FEB|MAR|APR|MAY|JUN|JUL|AUG|SEP|OCT|NOV|DEC)"

//fun getPattern() = """\d{2}\.\d{2}\.\d{4}"""
fun getPattern(): String = """\d{2} ${month}\ \d{4}"""

fun evaluatePattern() {
    assert(Regex(getPattern()).matches("13 JUN 1992"))
}

// for loop

// collection