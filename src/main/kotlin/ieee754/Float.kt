package ieee754

import java.lang.IllegalArgumentException
import kotlin.math.pow

class Float(
    sign: Sign,
    exponent: Int,
    fraction: Int
) : FloatingPointNumber(sign, exponent, fraction) {
    init {
        when {
            exponent >= (2.0).pow(8) || exponent < 0 -> throw IllegalArgumentException("Invalid exponent: $exponent")
            fraction >= (2.0).pow(23) || fraction < 0 -> throw IllegalArgumentException("Invalid fraction: $fraction")
        }
    }

    override fun toFloat() = kotlin.Float.fromBits(toLong().toInt())
    override fun toDouble() = toFloat().toDouble()

    companion object {
        private const val BIAS: Int = 127

        fun fromBinaryString(str: String): Float {
            val sign = if (str[0] == '0') Sign.PLUS else Sign.MINUS
            val exponent = str.substring(1, 9).toInt(2)
            val fraction = str.substring(9).toInt(2)
            return Float(sign, exponent, fraction)
        }

        fun fromBits(num: Long) = fromBinaryString(num.toString(2))
        fun fromHexString(str: String) = fromBits(str.toLowerCase().toLong(16))
    }
}
