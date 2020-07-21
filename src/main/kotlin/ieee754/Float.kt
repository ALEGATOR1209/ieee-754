package ieee754

import java.math.BigInteger

/** IEEE-754 Float (DD) number. */
class Float(
    sign: Sign,
    exponent: Int,
    fraction: BigInteger
) : FloatingPointNumber(sign, exponent, fraction, EXPONENT_SIZE, FRACTION_SIZE) {
    override fun toFloat() = kotlin.Float.fromBits(toLong().toInt())
    override fun toDouble() = toFloat().toDouble()

    companion object {
        /** Converts bits in string to instance of [Float].
         * @param str string with bits.
         * @return instance of [Float]. */
        fun fromBinaryString(str: String): Float {
            val sign = if (str[0] == '0') Sign.PLUS else Sign.MINUS
            val exponent = str.substring(1, EXPONENT_SIZE + 1).toInt(2)
            val fraction = str.substring(EXPONENT_SIZE + 1).toBigInteger(2)
            return Float(sign, exponent, fraction)
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

        const val EXPONENT_SIZE = 8
        const val FRACTION_SIZE = 23
    }
}
