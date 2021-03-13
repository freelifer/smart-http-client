package freelifer.smart.http

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Thread({
            Log.i("testtest", "result")
            val req = HttpOrOkHttpClient.HttpRequest()
            req.url = "http://ip-api.com/json?lang=zh-CN"

            val client = HttpOrOkHttpClient()
            val response = client.execute(req).runCatching {
                Log.i("testtest", "result")
                body().string()
            }.toString()
            Log.i("testtest", "result $response")
        }).start()
    }
}