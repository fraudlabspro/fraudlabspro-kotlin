object TestVerifySMS {
    @JvmStatic
    fun main(args: Array<String>) {
        // Configures FraudLabs Pro API key
        FraudLabsPro.APIKEY = "YOUR_API_KEY"

        // Get Verification Result API
        val verification = SMSVerification()

        // Sets transaction ID and otp details for verification purpose
        val data = java.util.Hashtable<String?, String?>()
        data["tran_id"] = "UNIQUE_TRANS_ID"
        data["otp"] = "OTP_RECEIVED"

        val result = verification.verifySMS(data)
        print(result)
    }
}