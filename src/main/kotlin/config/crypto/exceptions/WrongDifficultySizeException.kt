
package config.crypto.exceptions

import config.crypto.HASH_SIZE

class WrongDifficultySizeException : Exception(
    "The hash difficulty size is incorrect: You need to define a difficulty with size between 1 and $HASH_SIZE"
)