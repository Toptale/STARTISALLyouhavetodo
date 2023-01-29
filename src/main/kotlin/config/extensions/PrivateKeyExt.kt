
package config.extensions

import java.security.PrivateKey

fun PrivateKey.toHexString(): String = encoded.drop(27)
    .toByteArray()
    .toHexString()