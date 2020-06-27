import ieee754.onesComplement
import ieee754.signedMagnitude
import ieee754.twosComplement
import org.junit.Assert
import org.junit.Test

class IntegerArithmeticsTest {
    @Test
    fun signedMagnitudeTest() {
        val numbers = mapOf(
            0    to "00000000",
            1    to "00000001",
            2    to "00000010",
            4    to "00000100",
            5    to "00000101",
            10   to "00001010",
            127  to "01111111",
            -1   to "11111111",
            -2   to "11111110",
            -4   to "11111100",
            -5   to "11111101",
            -10  to "11111010",
            -127 to "11111111"
        )

        numbers.forEach { (t, u) -> Assert.assertEquals("Testing: $t", u, t.signedMagnitude(8)) }
    }

    @Test
    fun onesComplementTest() {
        val numbers = mapOf(
            0    to "00000000",
            1    to "00000001",
            2    to "00000010",
            4    to "00000100",
            5    to "00000101",
            10   to "00001010",
            127  to "01111111",
            -1   to "11111110",
            -2   to "11111101",
            -4   to "11111011",
            -5   to "11111010",
            -10  to "11110101",
            -127 to "10000000"
        )

        numbers.forEach { (t, u) -> Assert.assertEquals("Testing: $t", u, t.onesComplement(8)) }
    }

    @Test
    fun twosComplementTest() {
        val numbers = mapOf(
            0    to "00000000",
            1    to "00000001",
            2    to "00000010",
            4    to "00000100",
            5    to "00000101",
            10   to "00001010",
            127  to "01111111",
            -1   to "11111111",
            -2   to "11111110",
            -4   to "11111100",
            -5   to "11111011",
            -10  to "11110110",
            -127 to "10000001"
        )

        numbers.forEach { (t, u) -> Assert.assertEquals("Testing: $t", u, t.twosComplement(8)) }
    }
}