
package config.extensions

import config.crypto.MD
import java.math.BigInteger

fun String.toHashString(): String {
    MD.update(toByteArray())
    return BigInteger(1, MD.digest()).toString(16)
}