import java.io.IOException
import java.net.URL
import java.net.URLEncoder
import java.util.*

internal class Payment {
    /** Payment Feedback API
     * Report the final payment status back to the system, helping improve fraud detection and risk assessment.
     * @param data
     * Parameters that required to send payment feedback
     * @return string
     * Returns feedback results in JSON || XML format
     */
    fun feedback(data: Hashtable<String?, String?>): String {
        try {
            val dataStr = StringBuilder()
            data["source"] = FraudLabsPro.SOURCE
            data["source_version"] = FraudLabsPro.VERSION
            for (entry in data.entries) {
                dataStr.append("&").append(entry.key).append("=").append(URLEncoder.encode(entry.value, "UTF-8"))
            }
            val post = "key=" + FraudLabsPro.APIKEY + dataStr

            return Http.post(URL("https://api.fraudlabspro.com/v2/payment/feedback"), post)
        } catch (e: IOException) {
            throw RuntimeException(e)
        }
    }
}
