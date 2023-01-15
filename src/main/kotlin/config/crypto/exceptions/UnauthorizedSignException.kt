
package config.crypto.exceptions

class UnauthorizedSignException : Exception(
    "Impossible to sign transaction from another owner"
)