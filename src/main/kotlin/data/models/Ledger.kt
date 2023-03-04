
package data.models

import config.crypto.exceptions.InvalidTransactionException
import config.extensions.toHexString
import data.types.TransactionArray
import data.types.Verifiable
import java.security.PublicKey

class Ledger : Verifiable {

    private val chain: ArrayList<Block> = arrayListOf(Block.GENESIS)
    private val pendingTransactions: TransactionArray = TransactionArray.getGenesis()

    fun addBlock() {
        val block = Block()
        println("|\tAdding block...\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t|")
        block.previousHash = chain[chain.size - 1].hash
        block.transactions.clear()