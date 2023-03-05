
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
        block.transactions.addAll(pendingTransactions)
        pendingTransactions.clear()
        block.mine()
        chain.add(block)
        println("|\tAdded block value :\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t|")
        println("$block")
    }

    fun addBlockWithReward(address: PublicKey) {
        addTransaction(
            Transaction(
                null,
                address,
                MINING_REWARD
            )
        )

        addBlock()
    }

    fun addTransaction(transaction: Transaction) {
        if(!transaction.isValid())
            throw InvalidTransactionException()

        pendingTransactions.add(transaction)
    }

    fun getBalanceForAddress(address: PublicKey): Double {
        var balance = 0.0
        println("|\tGetting balance for ${address.toHexString()} :\t|")
        chain.forEach { block ->
            block.transactions.forEach { transaction ->