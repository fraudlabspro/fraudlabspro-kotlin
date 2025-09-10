object TestSendSMS {
    @JvmStatic
    fun main(args: Array<String>) {
        // Configures FraudLabs Pro API key
        FraudLabsPro.APIKEY = "YOUR_API_KEY"

        // Send SMS Verification API
        val sms = SMSVerification()

        // Sets SMS details for authentication purpose
        val data = java.util.Hashtable<String?, String?>()
        data["tel"] = "+123456789"
        data["country_code"] = "US"
        data["mesg"] = "Hi, your OTP is <otp>."
        data["otp_timeout"] = "3600"

        val result = sms.sendSMS(data)
        print(result)
    }
}