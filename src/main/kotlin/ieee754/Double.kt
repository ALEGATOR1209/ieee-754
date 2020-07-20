package ieee754

import java.lang.IllegalArgumentException
import java.math.BigInteger
import kotlin.math.pow

/** IEEE-754 Double (DQ) number. */
class Double(
        sign: Sign,
        exponent: Int,
        fraction: BigInteger
) : FloatingPointNumber(sign, exponent, fraction) {
    override val EXPONENT_SIZE = 11
    override val FRACTION_SIZE = 52

    init {
        when {
            exponent >= (2.0).pow(EXPONENT_SIZE) || exponent < 0 -> throw IllegalArgumentException("Invalid exponent: $exponent")
            fraction >= BigInteger.TWO.pow(FRACTION_SIZE) || fraction < BigInteger.ZERO -> throw IllegalArgumentException("Invalid fraction: $fraction")
        }
    }

    override fun toFloat() = toDouble().toFloat()
    override fun toDouble() = kotlin.Double.fromBits(toLong())

    companion object {
        /** Converts bits in string to instance of [Float].
         * @param str string with bits.
         * @return instance of [Float]. */
        fun fromBinaryString(str: String): Double {
            val sign = if (str[0] == '0') Sign.PLUS else Sign.MINUS
            val exponent = str.substring(1, 12).toInt(2)
            val fraction = str.substring(12).toBigInteger(2)
            return Double(sign, exponent, fraction)
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
    }
}
