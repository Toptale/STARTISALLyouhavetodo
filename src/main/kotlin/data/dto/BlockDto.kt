
package data.dto

import java.util.*

data class BlockDto(
    val transactions: ArrayList<TransactionDto>,
    val timestamp: Date,
    val nounce: Int,
    val previousHash: String,
    val hash: String
)