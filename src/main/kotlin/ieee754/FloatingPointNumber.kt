package ieee754

abstract class FloatingPointNumber(
    val sign: Sign,
    val exponent: Int,
    val fraction: Int
) {
    override fun toString() = StringBuilder().apply {
        append(if (sign >= 0) '0' else '1')
        append(exponent.toString(2))
        append(fraction.toString(2))
    }.toString()

    fun toHex() = toString().toInt().toString(16)
    fun toInt() = sign * (-1)  

    sealed class Sign(val num: Int) {
        operator fun times(other: Int) = num * other
        operator fun compareTo(other: Int) = num.compareTo(other)
        object PLUS: Sign(1)
        object MINUS: Sign(-1)
    }
}
