
package config.crypto.exceptions

class InvalidTransactionException : Exception(
    "Invalid transaction ; missing one of the addresses, or else the signing failed"
)