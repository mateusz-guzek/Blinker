// To parse the JSON, install kotlin's serialization plugin and do:
//
// val json  = Json { allowStructuredMapKeys = true }
// val order = json.parse(Order.serializer(), jsonString)

package pl.mateusz.blinker.modules.storage.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OrderStatus (
        val id: Long,
        val name: String,

        @SerialName("name_for_customer")
        val nameForCustomer: String
)
