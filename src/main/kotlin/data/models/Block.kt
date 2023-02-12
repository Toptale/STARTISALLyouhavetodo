
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