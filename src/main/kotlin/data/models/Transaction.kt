
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

        S.initSign(keyPair.private)
        S.update(calculateHash().fromHexString())
        signature = S.sign()
    }

    override fun verify(): Boolean {
        if(fromAddress == null) return true

        if(signature == null || signature?.isEmpty() == true) throw NoSignatureException()

        S.initVerify(fromAddress)
        S.update(calculateHash().fromHexString())
        return S.verify(signature)
    }

    override fun isValid(): Boolean = verify()

    override fun toString(): String =
        Gson().toJson(
            TransactionDto(
                fromAddress?.toHexString() ?: "",
                toAddress.toHexString(),
                amount
            )
        )

    companion object {
        val GENESIS = Transaction(
            Wallet.GENESIS.keyPair.public,
            Wallet.GENESIS.keyPair.public,
            0.0
        ).apply {
            sign(Wallet.GENESIS.keyPair)
        }
    }
}