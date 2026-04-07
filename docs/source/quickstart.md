# Quickstart

## Dependencies

This module requires API key to function. You may subscribe a free API key at [https://www.fraudlabspro.com](https://www.fraudlabspro.com)

## Sample Codes

### Validate Order

You can validate your order as below:

```kotlin
object TestValidate {
    @JvmStatic
    fun main(args: Array<String>) {
        // Configures FraudLabs Pro API key
        FraudLabsPro.APIKEY = "YOUR_API_KEY"

        // Screen Order API
        val order = Order()

        // Sets order details
        val data = java.util.Hashtable<String?, String?>()

        data["ip"] = "146.112.62.105" // IP parameter is mandatory
        data["first_name"] = "Hector"
        data["last_name"] = "Henderson"
        data["email"] = "hh5566@gmail.com"
        data["user_phone"] = "561-628-8674"

        // Billing information
        data["bill_addr"] = "1766 PowderHouse Road"
        data["bill_city"] = "West Palm Beach"
        data["bill_state"] = "FL"
        data["bill_country"] = "US"
        data["bill_zip_code"] = "33401"
        data["number"] = "4556553172971283"

        // Order information
        data["user_order_id"] = "67398"
        data["user_order_memo"] = "Online shop"
        data["amount"] = "79.89"
        data["quantity"] = "1"
        data["currency"] = "USD"
        data["payment_gateway"] = "stripe"
        data["payment_mode"] = order.CREDIT_CARD // Please refer reference section for full list of payment methods

        // Shipping information
        data["ship_first_name"] = "Hector"
        data["ship_last_name"] = "Henderson"
        data["ship_addr"] = "4469 Chestnut Street"
        data["ship_city"] = "Tampa"
        data["ship_state"] = "FL"
        data["ship_zip_code"] = "33602"
        data["ship_country"] = "US"

        val result = order.validate(data) // Sends order details to FraudLabs Pro
        print(result)
    }
}
```

### Get Transaction

You can get the details of a transaction as below:

```kotlin
object TestGetTransaction {
    @JvmStatic
    fun main(args: Array<String>) {
        // Configures FraudLabs Pro API key
        FraudLabsPro.APIKEY = "YOUR_API_KEY"

        // Get Order Result API
        val orderResults = Order()

        val data = java.util.Hashtable<String?, String?>()
        data["id"] = "20180709-NHAEUK" // FraudLabs Pro transaction ID to retrieve

        val result = orderResults.getTransaction(data) // Obtains screening results from FraudLabs Pro
        print(result)
    }
}
```

### Feedback

You can approve, reject or blacklist a transaction as below:

```kotlin
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
```

### Send SMS Verification

You can send SMS verification for authentication purpose as below:

```kotlin
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
```

### Get SMS Verification Result

You can verify the OTP sent by Fraudlabs Pro SMS verification API as below:

```kotlin
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
```

### Report Payment Gateway Feedback

You can report payment gateway feedback as below:

```kotlin
object TestPaymentFeedback {
    @JvmStatic
    fun main(args: Array<String>) {
        // Configures FraudLabs Pro API key
        FraudLabsPro.APIKEY = "YOUR_API_KEY"

        // Payment API
        val pay = Payment()

        // Report payment gateway feedback
        val data = java.util.Hashtable<String?, String?>()
        data["email"] = "hh5566@gmail.com"
        data["status"] = "declined"
        data["message"] = "Call Issuer. Pick Up Card. (2047)"
        data["fraudlabspro_id"] = "20260131-O263CR"

        val result = pay.feedback(data)
        print(result)
    }
}
```
