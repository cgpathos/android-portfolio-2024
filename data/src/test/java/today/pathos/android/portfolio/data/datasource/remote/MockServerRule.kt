package today.pathos.android.portfolio.data.datasource.remote

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import java.nio.charset.StandardCharsets

class MockServerRule : TestWatcher() {
    private val mockServer = MockWebServer()

    override fun finished(description: Description?) {
        super.finished(description)
        mockServer.shutdown()
    }

    fun url(path: String) = mockServer.url(path)

    fun enqueue(fileName: String, code: Int = 200) {
        val inputStream = javaClass.classLoader?.getResourceAsStream("api-response/$fileName")

        val source = inputStream?.source()?.buffer()
        source?.let {
            mockServer.enqueue(
                MockResponse()
                    .setResponseCode(code)
                    .setBody(source.readString(StandardCharsets.UTF_8))
            )
        }
    }
}
