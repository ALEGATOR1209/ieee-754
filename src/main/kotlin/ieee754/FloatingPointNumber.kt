package ieee754

import java.lang.IllegalArgumentException
import java.math.BigInteger
import kotlin.math.pow

/** Abstract class that represents floating point value in IEEE 754 format*/
abstract class FloatingPointNumber(
    val sign: Sign,
    val exponent: Int,
    val fraction: BigInteger,
    private val exponentSize: Int,
    private val fractionSize: Int
) {

    init {
        when {
            exponent >= (2.0).pow(exponentSize) || exponent < 0 -> throw IllegalArgumentException("Invalid exponent: $exponent")
            fraction >= BigInteger.TWO.pow(fractionSize) || fraction < BigInteger.ZERO -> throw IllegalArgumentException("Invalid fraction: $fraction")
        }
    }

    override fun toString() = StringBuilder().apply {
        append(if (sign >= 0) '0' else '1')
        append(exponent.signedMagnitude(exponentSize + 1).substring(1))
        append(fraction.signedMagnitude(fractionSize + 1).substring(1))
    }.toString()


    /** @return [BigInteger] representing bits of this floating point number */
    fun toBigInteger() = toString().toBigInteger(2)

    /** @return [Long] representing bits of this floating point number */
    fun toLong() = toBigInteger().toLong()

    abstract fun toFloat(): kotlin.Float
    abstract fun toDouble(): kotlin.Double

    /** @return string with hexadecimal code of this floating point number */
    fun toHex(): String = toBigInteger().toString(16)

    /** Sealed class that represents sign of floating point number.
     * @sample PLUS
     * @sample MINUS */
    sealed class Sign(private val num: Int) {
        operator fun compareTo(other: Int) = num.compareTo(other)
        object PLUS: Sign(1)
        object MINUS: Sign(-1)
    }
}
