import ieee754.Float
import ieee754.FloatingPointNumber
import org.junit.Assert
import org.junit.Test

class DoubleTest {
    private val float1 = Float(
            FloatingPointNumber.Sign.MINUS,
            "10000010".toInt(2),
            "01001000000000000000000".toInt(2)
    )

    @Test
    fun toStringTest() {
        Assert.assertEquals("11000001001001000000000000000000", float1.toString())
    }

    @Test
    fun toLongTest() {
        Assert.assertEquals(3_240_361_984, float1.toLong())
    }

    @Test
    fun toHexTest() {
        Assert.assertEquals("c1240000", float1.toHex())
    }

    @Test
    fun toFloatTest() {
        Assert.assertEquals(-10.25f, float1.toFloat(), 0.0f)
    }

    @Test
    fun toDoubleTest() {
        Assert.assertEquals(-10.25, float1.toDouble(), 0.0)
    }

    @Test
    fun fromBinaryStringTest() {
        val float = Float.fromBinaryString("11000001001001000000000000000000")
        Assert.assertEquals(FloatingPointNumber.Sign.MINUS, float.sign)
        Assert.assertEquals(130, float.exponent)
        Assert.assertEquals(2359296, float.fraction)
    }

    @Test
    fun fromBitsTest() {
        val float = Float.fromBits(3_240_361_984)
        Assert.assertEquals(FloatingPointNumber.Sign.MINUS, float.sign)
        Assert.assertEquals(130, float.exponent)
        Assert.assertEquals(2359296, float.fraction)
    }

    @Test
    fun fromHexStringTest() {
        val float1 = Float.fromHexString("c1240000")
        val float2 = Float.fromHexString("C1240000")
        Assert.assertEquals(FloatingPointNumber.Sign.MINUS, float1.sign)
        Assert.assertEquals(FloatingPointNumber.Sign.MINUS, float2.sign)
        Assert.assertEquals(130, float1.exponent)
        Assert.assertEquals(130, float2.exponent)
        Assert.assertEquals(2359296, float1.fraction)
        Assert.assertEquals(2359296, float2.fraction)
    }
}