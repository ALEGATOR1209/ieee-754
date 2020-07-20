package ieee754

import java.lang.IllegalArgumentException
import kotlin.math.pow

/** IEEE-754 Float (DD) number. */
class Float(
    sign: Sign,
    exponent: Int,
    fraction: Int
) : FloatingPointNumber(sign, exponent, fraction) {
    init {
        when {
            exponent >= (2.0).pow(EXPONENT_SIZE) || exponent < 0 -> throw IllegalArgumentException("Invalid exponent: $exponent")
            fraction >= (2.0).pow(FRACTION_SIZE) || fraction < 0 -> throw IllegalArgumentException("Invalid fraction: $fraction")
        }
    }

    override fun toFloat() = kotlin.Float.fromBits(toLong().toInt())
    override fun toDouble() = toFloat().toDouble()

    companion object {
        /** Size of exponent bits. */
        const val EXPONENT_SIZE = 8

        /** Size of fraction bits. */
        const val FRACTION_SIZE = 23

        /** Converts bits in string to instance of [Float].
         * @param str string with bits.
         * @return instance of [Float]. */
        fun fromBinaryString(str: String): Float {
            val sign = if (str[0] == '0') Sign.PLUS else Sign.MINUS
            val exponent = str.substring(1, EXPONENT_SIZE + 1).toInt(2)
            val fraction = str.substring(EXPONENT_SIZE + 1).toInt(2)
            return Float(sign, exponent, fraction)
        }

        /** Converts bits from Long to instance of [Float].
         * @param num bits.
         * @return instance of [Float]. */
        fun fromBits(num: Long) = fromBinaryString(num.toString(2))

        /** Converts hexadecimal representation of number to instance of [Float].
         * Case insensitive.
         * @param str string with hex representation of number.
         * @return instance of [Float]. */
        fun fromHexString(str: String) = fromBits(str.toLowerCase().toLong(16))
    }
}
