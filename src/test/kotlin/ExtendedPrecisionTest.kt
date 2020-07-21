import ieee754.ExtendedPrecision
import ieee754.FloatingPointNumber
import org.junit.Assert
import org.junit.Test

class ExtendedPrecisionTest {
    private val dt = ExtendedPrecision(
            FloatingPointNumber.Sign.MINUS,
            "100000000001001".toInt(2),
            "011001110111001111110000000000000000000000000000000000000000000".toBigInteger(2)
    )

    private val binary = "11000000000010011011001110111001111110000000000000000000000000000000000000000000"

    @Test
    fun toStringTest() {
        Assert.assertEquals(binary, dt.toString())
    }

    @Test
    fun toBigIntegerTest() {
        Assert.assertEquals(binary.toBigInteger(2), dt.toBigInteger())
    }

    @Test
    fun toLongTest() {
        Assert.assertEquals(binary.toBigInteger(2).toLong(), dt.toLong())
    }

    @Test
    fun toHexTest() {
        Assert.assertEquals("c009b3b9f80000000000", dt.toHex())
    }

    @Test
    fun toFloatTest() {
        Assert.assertEquals(-1437.812f, dt.toFloat(), 0.01f)
    }

    @Test
    fun toDoubleTest() {
        Assert.assertEquals(-1437.812, dt.toDouble(), 0.001)
    }

    @Test
    fun fromBinaryStringTest() {
        val dt = ExtendedPrecision.fromBinaryString(binary)
        Assert.assertEquals(FloatingPointNumber.Sign.MINUS, dt.sign)
        Assert.assertEquals(16393, dt.exponent)
        Assert.assertEquals(ExtendedPrecision.IntegerPart.ONE, dt.integerPart)
        Assert.assertEquals(3_727_282_845_509_484_544L.toBigInteger(), dt.fraction)
    }

    @Test
    fun fromBitsTest() {
        val dt = ExtendedPrecision.fromBits(binary.toBigInteger(2))
        Assert.assertEquals(FloatingPointNumber.Sign.MINUS, dt.sign)
        Assert.assertEquals(16393, dt.exponent)
        Assert.assertEquals(ExtendedPrecision.IntegerPart.ONE, dt.integerPart)
        Assert.assertEquals(3_727_282_845_509_484_544L.toBigInteger(), dt.fraction)
    }

    @Test
    fun fromHexStringTest() {
        val dt1 = ExtendedPrecision.fromHexString("c009b3b9f80000000000")
        val dt2 = ExtendedPrecision.fromHexString("C009B3B9F80000000000")
        Assert.assertEquals(FloatingPointNumber.Sign.MINUS, dt1.sign)
        Assert.assertEquals(FloatingPointNumber.Sign.MINUS, dt2.sign)
        Assert.assertEquals(16393, dt1.exponent)
        Assert.assertEquals(16393, dt2.exponent)
        Assert.assertEquals(ExtendedPrecision.IntegerPart.ONE, dt1.integerPart)
        Assert.assertEquals(ExtendedPrecision.IntegerPart.ONE, dt2.integerPart)
        Assert.assertEquals(3_727_282_845_509_484_544L.toBigInteger(), dt1.fraction)
        Assert.assertEquals(3_727_282_845_509_484_544L.toBigInteger(), dt2.fraction)
    }
}