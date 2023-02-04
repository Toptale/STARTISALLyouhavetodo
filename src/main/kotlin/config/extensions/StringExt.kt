
package config.extensions

import config.crypto.MD
import java.math.BigInteger

fun String.toHashString(): String {
    MD.update(toByteArray())
    return BigInteger(1, MD.digest()).toString(16)
}


fun String.fromHexString(): ByteArray =
    if (length % 2 != 0)
        byteArrayOf()
    else
        chunked(2)
            .map { it.toInt(16).toByte() }
            .toByteArray()