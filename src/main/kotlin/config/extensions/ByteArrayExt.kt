
package config.extensions

import java.security.KeyFactory
import java.security.PublicKey
import java.security.spec.X509EncodedKeySpec

fun ByteArray.toHexString(): String = joinToString(separator = "") { eachByte -> "%02x".format(eachByte) }

fun ByteArray.toPublicKey(): PublicKey =
    KeyFactory.getInstance("EC")
        .generatePublic(
            X509EncodedKeySpec(this)
        )