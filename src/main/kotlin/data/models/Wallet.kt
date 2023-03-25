
package data.models

import config.extensions.toHexString
import java.security.KeyPair
import java.security.KeyPairGenerator
import java.security.PublicKey
import java.security.spec.ECGenParameterSpec

class Wallet {

    val keyPair: KeyPair = KeyPairGenerator.getInstance("EC")
        .run {
            initialize(ECGenParameterSpec("secp256k1"))
            genKeyPair()
        }

    val publicKey: PublicKey = keyPair.public

    override fun toString(): String =