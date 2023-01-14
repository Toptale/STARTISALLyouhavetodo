
package config.crypto

import java.security.MessageDigest
import java.security.Signature

// Message digest
val MD: MessageDigest = MessageDigest.getInstance("SHA-256")
// Signature
val S = Signature.getInstance("SHA256withECDSA")

val HASH_SIZE: Int = when(MD.algorithm) {
    "SHA-256" -> 64
    else -> 32
}

const val HASH_DIFFICULTY = "a0"