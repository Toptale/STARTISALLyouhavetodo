
package data.types

import data.models.Transaction

class TransactionArray : ArrayList<Transaction>() {

    override fun toString(): String {
        var result = ""
        forEach { transaction ->
            result += transaction.toString()
        }
        return result
    }

    companion object {
        private val GENESIS = TransactionArray()

        fun getGenesis(): TransactionArray =
            if(GENESIS.isEmpty()) {
                GENESIS.add(Transaction.GENESIS)
                GENESIS
            } else {
                GENESIS
            }
    }
}