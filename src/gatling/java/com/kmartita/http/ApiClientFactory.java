package com.kmartita.http;
import com.kmartita.config.ConfigReader;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.time.Duration;

public class ApiClientFactory {

    public static <S> S getRestApi(Class<S> serviceClass) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        Duration timeout = Duration.ofSeconds(5);

        final OkHttpClient client = builder
                .connectTimeout(timeout)
                .readTimeout(timeout)
                .writeTimeout(timeout)
                .addInterceptor(new HttpLoggingInterceptor())
                .build();

        return new Retrofit.Builder()
                .client(client)
                .baseUrl(ConfigReader.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(serviceClass);
    }
}
