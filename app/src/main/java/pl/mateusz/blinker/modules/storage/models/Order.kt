// To parse the JSON, install kotlin's serialization plugin and do:
//
// val json  = Json { allowStructuredMapKeys = true }
// val order = json.parse(Order.serializer(), jsonString)

package pl.mateusz.blinker.modules.storage.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Order (
    @SerialName("order_id")
    val orderID: Long,

    @SerialName("shop_order_id")
    val shopOrderID: Long,

    @SerialName("external_order_id")
    val externalOrderID: String,

    @SerialName("order_source")
    val orderSource: String,

    @SerialName("order_source_id")
    val orderSourceID: Long,

    @SerialName("order_source_info")
    val orderSourceInfo: String,

    @SerialName("order_status_id")
    val orderStatusID: Long,

    @SerialName("date_add")
    val dateAdd: Long,

    @SerialName("date_confirmed")
    val dateConfirmed: Long,

    @SerialName("date_in_status")
    val dateInStatus: Long,

    @SerialName("user_login")
    val userLogin: String,

    val phone: String,
    val email: String,

    @SerialName("user_comments")
    val userComments: String,

    @SerialName("admin_comments")
    val adminComments: String,

    val currency: String,

    @SerialName("payment_method")
    val paymentMethod: String,

    @SerialName("payment_method_cod")
    val paymentMethodCod: String,

    @SerialName("payment_done")
    val paymentDone: String,

    @SerialName("delivery_method")
    val deliveryMethod: String,

    @SerialName("delivery_price")
    val deliveryPrice: Long,

    @SerialName("delivery_package_module")
    val deliveryPackageModule: String,

    @SerialName("delivery_package_nr")
    val deliveryPackageNr: String,

    @SerialName("delivery_fullname")
    val deliveryFullname: String,

    @SerialName("delivery_company")
    val deliveryCompany: String,

    @SerialName("delivery_address")
    val deliveryAddress: String,

    @SerialName("delivery_city")
    val deliveryCity: String,

    @SerialName("delivery_state")
    val deliveryState: String,

    @SerialName("delivery_postcode")
    val deliveryPostcode: String,

    @SerialName("delivery_country")
    val deliveryCountry: String,

    @SerialName("delivery_point_id")
    val deliveryPointID: String,

    @SerialName("delivery_point_name")
    val deliveryPointName: String,

    @SerialName("delivery_point_address")
    val deliveryPointAddress: String,

    @SerialName("delivery_point_postcode")
    val deliveryPointPostcode: String,

    @SerialName("delivery_point_city")
    val deliveryPointCity: String,

    @SerialName("invoice_fullname")
    val invoiceFullname: String,

    @SerialName("invoice_company")
    val invoiceCompany: String,

    @SerialName("invoice_nip")
    val invoiceNip: String,

    @SerialName("invoice_address")
    val invoiceAddress: String,

    @SerialName("invoice_city")
    val invoiceCity: String,

    @SerialName("invoice_state")
    val invoiceState: String,

    @SerialName("invoice_postcode")
    val invoicePostcode: String,

    @SerialName("invoice_country")
    val invoiceCountry: String,

    @SerialName("want_invoice")
    val wantInvoice: String,

    @SerialName("extra_field_1")
    val extraField1: String,

    @SerialName("extra_field_2")
    val extraField2: String,

    @SerialName("custom_extra_fields")
    val customExtraFields: Map<String, String>,

    @SerialName("order_page")
    val orderPage: String,

    @SerialName("pick_status")
    val pickStatus: String,

    @SerialName("pack_status")
    val packStatus: String,

    val products: List<Product>
)

@Serializable
data class Product (
    val storage: String,

    @SerialName("storage_id")
    val storageID: Long,

    @SerialName("order_product_id")
    val orderProductID: Long,

    @SerialName("product_id")
    val productID: String,

    @SerialName("variant_id")
    val variantID: Long,

    val name: String,
    val attributes: String,
    val sku: String,
    val ean: String,
    val location: String,

    @SerialName("auction_id")
    val auctionID: String,

    @SerialName("price_brutto")
    val priceBrutto: Long,

    @SerialName("tax_rate")
    val taxRate: Long,

    val quantity: Long,
    val weight: Long,

    @SerialName("bundle_id")
    val bundleID: Long
)
