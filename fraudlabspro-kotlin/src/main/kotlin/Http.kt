import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

internal object Http {
    @JvmStatic
    fun get(url: URL): String {
        try {
            val conn = url.openConnection() as HttpURLConnection
            conn.setRequestMethod("GET")
            conn.setRequestProperty("Accept", "application/json")

            if (conn.getResponseCode() != 200) {
                return ("Failed : HTTP error code : " + conn.getResponseCode())
            }
            val br = BufferedReader(
                InputStreamReader(
                    (conn.getInputStream())
                )
            )

            var output: String?
            val resultFromHttp = StringBuilder()
            while ((br.readLine().also { output = it }) != null) {
                resultFromHttp.append(output).append("\n")
            }

            br.close()
            conn.disconnect()
            return resultFromHttp.toString()
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    @JvmStatic
    fun post(url: URL, post: String): String {
        try {
            val conn = url.openConnection() as HttpURLConnection
            conn.setRequestMethod("POST")
            conn.setRequestProperty("Accept", "application/json")

            val urlParameters = post

            conn.setDoOutput(true)
            val dos = DataOutputStream(conn.getOutputStream())
            dos.writeBytes(urlParameters)
            dos.flush()
            dos.close()

            if (conn.getResponseCode() != 200) {
                return ("Failed : HTTP error code : " + conn.getResponseCode())
            }

            val br = BufferedReader(
                InputStreamReader(
                    (conn.getInputStream())
                )
            )

            var output: String?
            val resultFromHttp = StringBuilder()
            while ((br.readLine().also { output = it }) != null) {
                resultFromHttp.append(output).append("\n")
            }

            br.close()
            conn.disconnect()
            return resultFromHttp.toString()
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}
