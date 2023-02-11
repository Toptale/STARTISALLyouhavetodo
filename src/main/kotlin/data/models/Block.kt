
package data.models

import com.google.gson.GsonBuilder
import config.crypto.HASH_DIFFICULTY
import config.crypto.HASH_SIZE
import config.crypto.exceptions.WrongDifficultySizeException
import config.extensions.toHashString
import config.extensions.toHexString
import data.dto.BlockDto