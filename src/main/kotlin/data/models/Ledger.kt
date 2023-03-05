
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
                if (transaction.fromAddress == address) balance -= transaction.amount

                if (transaction.toAddress == address) balance += transaction.amount
            }
        }
        println("|\tBalance = $balance\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t|")
        return balance
    }

    override fun isValid(): Boolean {
        for (i in 1 until chain.size) {
            if (!chain[i].isValid()) return false

            if (chain[i].hash != chain[i].calculateHash()) return false

            if (chain[i].previousHash != chain[i - 1].calculateHash()) return false
        }
        return true
    }

    companion object {
        const val MINING_REWARD = 10.0