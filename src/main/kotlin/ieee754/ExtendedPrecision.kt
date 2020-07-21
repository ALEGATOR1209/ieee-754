package ieee754

import java.math.BigInteger
import kotlin.Double
import kotlin.Float
import kotlin.math.absoluteValue

/** IEEE-754 Extended Precision (DT) number. */
class ExtendedPrecision(
        sign: Sign,
        exponent: Int,
        fraction: BigInteger,
        val integerPart: IntegerPart = IntegerPart.ONE
) : FloatingPointNumber(sign, exponent, fraction, EXPONENT_SIZE, FRACTION_SIZE) {
    override fun toDouble() = when {
        16383 - exponent.absoluteValue > 1023 -> if (sign == Sign.PLUS) Double.POSITIVE_INFINITY else Double.NEGATIVE_INFINITY
        integerPart == IntegerPart.ZERO -> Double.NaN
        else -> Double(sign, exponent - 16383 + 1023, fraction.shiftRight(FRACTION_SIZE - 52)).toDouble()
    }
    override fun toFloat() = toDouble().toFloat()

    override fun toString() = StringBuilder().apply {
        append(if (sign >= 0) '0' else '1')
        append(exponent.signedMagnitude(EXPONENT_SIZE + 1).substring(1))
        append(integerPart.num)
        append(fraction.signedMagnitude(FRACTION_SIZE + 1).substring(1))
    }.toString()

    enum class IntegerPart(val num: Int) {
        ZERO(0),
        ONE(1),
    }

    companion object {
        /** Converts bits in string to instance of [Float].
         * @param str string with bits.
         * @return instance of [Float]. */
        fun fromBinaryString(str: String): ExtendedPrecision {
            val sign = if (str[0] == '0') Sign.PLUS else Sign.MINUS
            val exponent = str.substring(1, EXPONENT_SIZE + 1).toInt(2)
            val integerPart = if (str[EXPONENT_SIZE + 1] == '1') IntegerPart.ONE else IntegerPart.ZERO
            val fraction = str.substring(EXPONENT_SIZE + 2).toBigInteger(2)
            return ExtendedPrecision(sign, exponent, fraction, integerPart)
        }

        /** Converts bits from Long to instance of [Float].
         * @param num bits.
         * @return instance of [Float]. */
        fun fromBits(num: BigInteger) = fromBinaryString(num.toString(2))

        /** Converts hexadecimal representation of number to instance of [Float].
         * Case insensitive.
         * @param str string with hex representation of number.
         * @return instance of [Float]. */
        fun fromHexString(str: String) = fromBits(str.toLowerCase().toBigInteger(16))

        const val EXPONENT_SIZE = 15
        const val FRACTION_SIZE = 63
    }
}
