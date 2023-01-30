
package config.extensions

import java.security.PublicKey

fun PublicKey.toHexString(): String = encoded.drop(27)
    .toByteArray()
    .toHexString()