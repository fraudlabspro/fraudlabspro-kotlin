import java.net.URL
import java.net.URLEncoder
import java.security.MessageDigest
import java.text.DecimalFormat
import java.util.*

/**
 * FraudLabsPro Order module.
 * Validates order for possible fraud and feedback user decision.
 */
class Order {
    // Order statuses
    val APPROVE: String = "APPROVE"
    val REJECT: String = "REJECT"
    val REJECT_BLACKLIST: String = "REJECT_BLACKLIST"

    // Payment methods
    val CREDIT_CARD: String = "CREDITCARD"
    val PAYPAL: String = "PAYPAL"
    val CASH_ON_DELIVERY: String = "COD"
    val BANK_DEPOSIT: String = "BANKDEPOSIT"
    val GIFT_CARD: String = "GIFTCARD"
    val CRYPTO: String = "CRYPTO"
    val WIRE_TRANSFER: String = "WIRED"
    val OTHERS: String = "OTHERS"

    // ID types.
    val FLP_ID: String = "fraudlabspro_id"
    val ORDER_ID: String = "user_order_id"

    /** Screen Order API
     * Screen an order transaction for payment fraud.
     * This REST API will detects all possibles fraud traits based on the input parameters supplied.
     * The more input parameter supplied, the higher accuracy of fraud detection.
     * @param data
     * Parameters that required to screen orders
     * @return string
     * Returns processed orders in JSON || XML format
     */
    fun validate(data: Hashtable<String?, String?>): String {
        try {
            if (data.get("email") != null && !data.get("email")!!.isEmpty()) {
                data["email_domain"] =
                    data.get("email")!!.substring(data.get("email")!!.indexOf("@") + 1) // gets the email domain
                data["email_hash"] = doHash(data.get("email")!!) // generates email hash
            }

            if (data.get("user_phone") != null && !data.get("user_phone")!!.isEmpty()) {
                data["user_phone"] = data.get("user_phone")!!.replace("[^0-9]".toRegex(), "")
            }

            if (data.get("currency") == null || data.get("currency")!!.isEmpty()) {
                data["currency"] = "USD" //  default currency is USD
            }

            if (data.get("number") != null && !data.get("number")!!.isEmpty()) {
                data["bin_no"] = data.get("number")!!.substring(0, 6)
                data["card_hash"] = doHash(data.get("number")!!) // generates card hash
            }

            if (data.get("amount") != null && !data.get("amount")!!.isEmpty()) {
                data["amount"] = DecimalFormat("#.00").format(data.get("amount")!!.toDouble())
            }

            val dataStr = StringBuilder()
            data["source"] = "sdk-java"
            data["source_version"] = FraudLabsPro.VERSION
            for (entry in data.entries) {
                dataStr.append("&").append(entry.key).append("=").append(URLEncoder.encode(entry.value, "UTF-8"))
            }

            val post = "key=" + FraudLabsPro.APIKEY + dataStr

            return Http.post(URL("https://api.fraudlabspro.com/v2/order/screen"), post)
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    /** Feedback Order API
     * Update status of a transaction from pending-manual-review to APPROVE, REJECT or IGNORE.
     * The FraudLabs Pro algorithm will improve the formula in determine the FraudLabs Pro score using the data collected.
     * @param data
     * Parameters that required to set feedback
     * @return string
     * Returns order feedback in JSON || XML format
     */
    fun feedback(data: Hashtable<String?, String?>): String {
        try {
            val dataStr = StringBuilder()
            data["source"] = "sdk-java"
            data["source_version"] = FraudLabsPro.VERSION
            for (entry in data.entries) {
                dataStr.append("&").append(entry.key).append("=").append(URLEncoder.encode(entry.value, "UTF-8"))
            }
            val post = "key=" + FraudLabsPro.APIKEY + dataStr

            return Http.post(URL("https://api.fraudlabspro.com/v2/order/feedback"), post)
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    /** Get Order Result API
     * Retrieve an existing transaction from FraudLabs Pro fraud detection system.
     * @param data
     * Parameters that required to get order results
     * @return string
     * Returns order results in JSON || XML format
     */
    fun getTransaction(data: Hashtable<String?, String?>): String {
        try {
            val dataStr = StringBuilder()
            data["source"] = "sdk-java"
            data["source_version"] = FraudLabsPro.VERSION
            for (entry in data.entries) {
                dataStr.append("&").append(entry.key).append("=").append(URLEncoder.encode(entry.value, "UTF-8"))
            }

            return Http.get(URL("https://api.fraudlabspro.com/v2/order/result?key=" + FraudLabsPro.APIKEY + dataStr))
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    /** To hash a string to protect its real value
     *
     * @param value
     * Value to be hashed
     * @return
     * Returns hashed string value
     */
    fun doHash(s: String): String {
        // Step 1: initial prefix
        var hash = "fraudlabspro_$s"

        // Step 2: 65536 iterations of sha1
        val sha1 = MessageDigest.getInstance("SHA-1")
        repeat(65536) {
            val data = "fraudlabspro_$hash".toByteArray(Charsets.UTF_8)
            val h = sha1.digest(data)
            hash = h.joinToString("") { "%02x".format(it) }
        }

        // Step 3: final sha256
        val sha256 = MessageDigest.getInstance("SHA-256")
        val h2 = sha256.digest(hash.toByteArray(Charsets.UTF_8))
        return h2.joinToString("") { "%02x".format(it) }
    }
}
