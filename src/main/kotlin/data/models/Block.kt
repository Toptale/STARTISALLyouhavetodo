
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