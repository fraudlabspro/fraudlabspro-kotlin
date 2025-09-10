object TestGetTransaction {
    @JvmStatic
    fun main(args: Array<String>) {
        // Configures FraudLabs Pro API key
        FraudLabsPro.APIKEY = "YOUR_API_KEY"

        // Get Order Result API
        val orderResults = Order()

        val data = java.util.Hashtable<String?, String?>()
        data["id"] = "20180709-NHAEUK"

        val result = orderResults.getTransaction(data) // Obtains screening results from FraudLabs Pro
        print(result)
    }
}