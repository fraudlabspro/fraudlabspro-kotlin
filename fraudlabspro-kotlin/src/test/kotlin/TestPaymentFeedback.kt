import java.util.Hashtable

object TestPaymentFeedback {
    @JvmStatic
    fun main(args: Array<String>) {
        // Configures FraudLabs Pro API key
        FraudLabsPro.APIKEY = "YOUR_API_KEY"

        // Get Payment API
        val pay = Payment()

        // Sets feedback details
        val data = Hashtable<String?, String?>()
        data["email"] = "hh5566@gmail.com"
        data["status"] = "declined"
        data["message"] = "Call Issuer. Pick Up Card. (2047)"
        data["fraudlabspro_id"] = "20260131-O263CR"
        val result = pay.feedback(data)
        print(result)
    }
}