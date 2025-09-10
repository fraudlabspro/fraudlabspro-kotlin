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