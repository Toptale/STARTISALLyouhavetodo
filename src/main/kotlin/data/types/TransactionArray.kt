
package data.types

import data.models.Transaction

class TransactionArray : ArrayList<Transaction>() {

    override fun toString(): String {
        var result = ""
        forEach { transaction ->