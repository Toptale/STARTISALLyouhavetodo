
package data.types

import java.security.KeyPair

interface Singable {

    fun sign(keyPair: KeyPair)
    fun verify(): Boolean
}