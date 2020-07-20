import ieee754.Double
import ieee754.FloatingPointNumber
import org.junit.Assert
import org.junit.Test

class DoubleTest {
    private val double1 = Double(
            FloatingPointNumber.Sign.MINUS,
            "402".toInt(16),
            "4800000000000".toBigInteger(16)
    )

    private val binary = "1100000000100100100000000000000000000000000000000000000000000000"

    @Test
    fun toStringTest() {
        Assert.assertEquals(binary, double1.toString())
    }

    @Test
    fun toBigIntegerTest() {
        Assert.assertEquals(binary.toBigInteger(2), double1.toBigInteger())
    }

    @Test
    fun toLongTest() {
        Assert.assertEquals(binary.toBigInteger(2).toLong(), double1.toLong())
    }

    @Test
    fun toHexTest() {
        Assert.assertEquals("c024800000000000", double1.toHex())
    }

    @Test
    fun toFloatTest() {
        Assert.assertEquals(-10.25f, double1.toFloat(), 0.0f)
    }

    @Test
    fun toDoubleTest() {
        Assert.assertEquals(-10.25, double1.toDouble(), 0.0)
    }

    @Test
    fun fromBinaryStringTest() {
        val double = Double.fromBinaryString(binary)
        Assert.assertEquals(FloatingPointNumber.Sign.MINUS, double.sign)
        Assert.assertEquals(1026, double.exponent)
        Assert.assertEquals(1_266_637_395_197_952L.toBigInteger(), double.fraction)
    }

    @Test
    fun fromBitsTest() {
        val double = Double.fromBits(binary.toBigInteger(2))
        Assert.assertEquals(FloatingPointNumber.Sign.MINUS, double.sign)
        Assert.assertEquals(1026, double.exponent)
        Assert.assertEquals(1_266_637_395_197_952L.toBigInteger(), double.fraction)
    }

    @Test
    fun fromHexStringTest() {
        val double1 = Double.fromHexString("c024800000000000")
        val double2 = Double.fromHexString("C024800000000000")
        Assert.assertEquals(FloatingPointNumber.Sign.MINUS, double1.sign)
        Assert.assertEquals(FloatingPointNumber.Sign.MINUS, double2.sign)
        Assert.assertEquals(1026, double1.exponent)
        Assert.assertEquals(1026, double2.exponent)
        Assert.assertEquals(1_266_637_395_197_952L.toBigInteger(), double1.fraction)
        Assert.assertEquals(1_266_637_395_197_952L.toBigInteger(), double2.fraction)
    }
}