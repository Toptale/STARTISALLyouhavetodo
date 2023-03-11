
package data.models

import com.google.gson.Gson
import config.crypto.S
import config.crypto.exceptions.NoSignatureException
import config.crypto.exceptions.UnauthorizedSignException
import config.extensions.fromHexString
import config.extensions.toHashString
import config.extensions.toHexString
import data.dto.TransactionDto
import data.types.Hashable
import data.types.Singable
import data.types.Verifiable
import java.security.KeyPair
import java.security.PublicKey

class Transaction(
    val fromAddress: PublicKey?,
    val toAddress: PublicKey,
    val amount: Double
) : Hashable,
    Singable,
Verifiable {

    private var signature: ByteArray? = null

    override fun calculateHash(): String =
        "${fromAddress?.toHexString()}${toAddress.toHexString()}$amount".toHashString()

    override fun sign(keyPair: KeyPair) {
        println("|\tSigning transaction :\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t|")
        if(
            fromAddress == null ||
            keyPair.public != fromAddress
        )
            throw UnauthorizedSignException()