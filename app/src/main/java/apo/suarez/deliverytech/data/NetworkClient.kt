package apo.suarez.deliverytech.data

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory
import java.security.KeyManagementException
import java.security.NoSuchAlgorithmException
import java.util.concurrent.TimeUnit

object NetworkClient {
    private var client: OkHttpClient? = null
    private var converter: GsonConverterFactory? = null

    val networkClient: OkHttpClient
        @Throws(NoSuchAlgorithmException::class, KeyManagementException::class)
        get() {
            if (client == null) {
                val httpBuilder = OkHttpClient.Builder()
                httpBuilder
                    .connectTimeout(15, TimeUnit.SECONDS)
                    .readTimeout(20, TimeUnit.SECONDS)
                client = httpBuilder.build()

            }
            return client!!
        }


    val gsonConverter: GsonConverterFactory
        get() {
            if(converter == null){
                converter = GsonConverterFactory
                    .create(
                        GsonBuilder()
                            .setLenient()
                            .disableHtmlEscaping()
                            .create())
            }
            return converter!!
        }
}