package ieee754

import java.lang.IllegalArgumentException
import kotlin.math.absoluteValue

/**
 * Extension function for converting binary representation of number to given bit grid.
 * @receiver coded number to be converted
 * @param n size of bit grid
 * @throws IllegalArgumentException if number overflows grid
 * @return String with number in given grid
 */
private fun String.toBits(n: Int = 0) = when {
    n == 0 -> this
    length > n -> throw IllegalArgumentException("Bit grid overflow")
    else -> first().toString().repeat(n - length) + this
}

/**
 * Extension function for signed magnitude representation of a integer number.
 * @receiver the number to be converted
 * @param bits number of bits in result string. If [bits] = 0, number will take minimal number of bits
 * @throws IllegalArgumentException if number bits more than [bits]
 * @return String - signed magnitude representation of given number
 */
fun Int.signedMagnitude(bits: Int = 0) = (
    (if (this >= 0) "0" else "1") + this.absoluteValue.toString(2)
).toBits(bits)

/**
 * Extension function for one's complement representation of a integer number.
 * @receiver the number to be converted
 * @param bits number of bits in result string. If [bits] = 0, number will take minimal number of bits
 * @throws IllegalArgumentException if number bits more than [bits]
 * @return String - one's complement of given number
 */
fun Int.onesComplement(bits: Int = 0) = if (this >= 0) signedMagnitude(bits) else signedMagnitude()
    .replaceFirst('1','0')
    .map { when (it) {
        '0' -> '1'
        '1' -> '0'
        else -> error("Illegal character: $it")
    }}.joinToString("")
    .toBits(bits)

/**
 * Extension function for two's complement representation of a integer number.
 * @receiver the number to be converted
 * @param bits number of bits in result string. If [bits] = 0, number will take minimal number of bits
 * @throws IllegalArgumentException if number bits more than [bits]
 * @return String - two's complement of given number
 */
fun Int.twosComplement(bits: Int) = if (this >= 0) signedMagnitude(bits) else onesComplement()
    .toInt(2)
    .plus(1)
    .toString(2)
    .toBits(bits)
