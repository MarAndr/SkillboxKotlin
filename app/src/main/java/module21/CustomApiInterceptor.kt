package module21

import okhttp3.Interceptor
import okhttp3.Response

class CustomApiInterceptor(
    private val apiName: String,
    private val apiValue: String
): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val url = chain.request().url()
            .newBuilder()
            .addQueryParameter(apiName, apiValue)
            .build()

        val request = chain.request().newBuilder().url(url).build()
        return chain.proceed(request)

    }
}