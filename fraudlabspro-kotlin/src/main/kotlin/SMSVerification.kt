import java.io.IOException
import java.net.URL
import java.net.URLEncoder
import java.util.*

internal class SMSVerification {
    /** Send SMS Verification API
     * Send an SMS with verification code and a custom message for authentication purpose.
     * @param data
     * Parameters that required to send SMS verification
     * @return string
     * Returns SMS verification results in JSON || XML format
     */
    fun sendSMS(data: Hashtable<String?, String?>): String {
        try {
            val dataStr = StringBuilder()
            data["source"] = "sdk-java"
            data["source_version"] = FraudLabsPro.VERSION
            for (entry in data.entries) {
                dataStr.append("&").append(entry.key).append("=").append(URLEncoder.encode(entry.value, "UTF-8"))
            }
            val post = "key=" + FraudLabsPro.APIKEY + dataStr

            return Http.post(URL("https://api.fraudlabspro.com/v2/verification/send"), post)
        } catch (e: IOException) {
            throw RuntimeException(e)
        }
    }

    /** Get Verification Result API
     * Verify that an OTP sent by the Send SMS Verification API is valid.
     * @param data
     * Parameters that required to get SMS verification results
     * @return string
     * Returns sms verification results in JSON || XML format
     */
    fun verifySMS(data: Hashtable<String?, String?>): String {
        try {
            val dataStr = StringBuilder()
            data["source"] = "sdk-java"
            data["source_version"] = FraudLabsPro.VERSION
            for (entry in data.entries) {
                dataStr.append("&").append(entry.key).append("=").append(URLEncoder.encode(entry.value, "UTF-8"))
            }

            return Http.get(URL("https://api.fraudlabspro.com/v2/verification/result?key=" + FraudLabsPro.APIKEY + dataStr))
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}
