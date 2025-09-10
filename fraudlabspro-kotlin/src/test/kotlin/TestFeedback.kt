object TestFeedback {
    @JvmStatic
    fun main(args: Array<String>) {
        // Configures FraudLabs Pro API key
        FraudLabsPro.APIKEY = "YOUR_API_KEY"

        // Feedback Order API
        val fb = Order()

        // Sets feedback details
        val data = java.util.Hashtable<String?, String?>()
        data["id"] = "20180709-NHAEUK"
        data["action"] = fb.REJECT // Please refer to reference section for full list of feedback statuses
        data["note"] = "This customer made a valid purchase before."

        val result = fb.feedback(data) // Sends feedback details to FraudLabs Pro
        print(result)
    }
}