
package data.dto

data class TransactionDto(
    private val fromAddress: String,
    private val toAddress: String,
    private val amount: Double
)