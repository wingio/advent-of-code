package util

fun <T> T.print(transform: (T) -> Any? = { this }): T = println(this?.run(transform)).run { this@print }