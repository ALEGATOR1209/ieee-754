package ieee754

/** Abstract class that represents floating point value in IEEE 754 format*/
abstract class FloatingPointNumber(
    val sign: Sign,
    val exponent: Int,
    val fraction: Int
) {
    override fun toString() = StringBuilder().apply {
        append(if (sign >= 0) '0' else '1')
        append(exponent.signedMagnitude(9).substring(1))
        append(fraction.signedMagnitude(24).substring(1))
    }.toString()


    /** @return integer representing this floating point number */
    fun toLong() = toString().toLong(2)

    abstract fun toFloat(): kotlin.Float
    abstract fun toDouble(): kotlin.Double

    /** @return string with hexadecimal code of this floating point number */
    fun toHex() = toLong().toString(16)

    /** Sealed class that represents sign of floating point number.
     * @sample PLUS
     * @sample MINUS */
    sealed class Sign(private val num: Int) {
        operator fun compareTo(other: Int) = num.compareTo(other)
        object PLUS: Sign(1)
        object MINUS: Sign(-1)
    }
}
