
package data.models

import com.google.gson.GsonBuilder
import config.crypto.HASH_DIFFICULTY
import config.crypto.HASH_SIZE
import config.crypto.exceptions.WrongDifficultySizeException
import config.extensions.toHashString
import config.extensions.toHexString
import data.dto.BlockDto
import data.dto.TransactionDto
import data.types.Hashable
import data.types.Miner
import data.types.TransactionArray
import data.types.Verifiable
import java.util.*
import kotlin.jvm.Throws

class Block : Hashable,
    Miner,
    Verifiable {

    val transactions: TransactionArray = TransactionArray()
    private val timestamp: Date = Date()
    var nounce: Int = 0
    var previousHash: String = "0"
    lateinit var hash: String

    override fun calculateHash(): String =
        "$transactions$timestamp$nounce$previousHash".toHashString()

    override fun mine() {
        if (HASH_DIFFICULTY.isEmpty() || HASH_DIFFICULTY.length > HASH_SIZE) throw WrongDifficultySizeException()

        println("|\tBlock mining started...\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t|")
        while (!(calculateHash().also { hash = it }).contains(HASH_DIFFICULTY)) {
            nounce = (Math.random() * 10 * HASH_DIFFICULTY.length).toInt()
        }

        println("|\tBlock have been mined, the resulting hash is : $hash\t\t\t\t\t\t\t\t\t\t|")
    }

    override fun isValid(): Boolean {
        transactions.forEach { transaction ->
            if (!transaction.isValid()) return false
        }
        return true
    }


    override fun toString(): String =
        GsonBuilder()
            .setPrettyPrinting()
            .create()
            .toJson(
                BlockDto(
                    transactions.map { transaction ->
                        TransactionDto(
                            transaction.fromAddress?.toHexString() ?: "",
                            transaction.toAddress.toHexString(),
                            transaction.amount
                        )
                    } as ArrayList<TransactionDto>,
                    timestamp,