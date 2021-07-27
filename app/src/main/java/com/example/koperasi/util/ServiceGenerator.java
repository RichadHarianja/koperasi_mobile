package com.example.koperasi.util;

import android.os.Build;

import com.example.koperasi.model.InterceptorModel;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import okhttp3.ConnectionSpec;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {
    private static Retrofit retrofit;

    private static final String BASE_URL = "https://koperasi-api.azurewebsites.net/";
    private static InterceptorModel interceptorModel = new InterceptorModel();

    public static Retrofit getRetrofitInstance(String accessToken) {

        interceptorModel.setAccessToken(accessToken);

        /* ConnectionSpec.MODERN_TLS is the default value */
        List tlsSpecs = Arrays.asList(ConnectionSpec.MODERN_TLS);

        /* providing backwards-compatibility for API lower than Lollipop: */
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            tlsSpecs = Arrays.asList(ConnectionSpec.COMPATIBLE_TLS);
        }

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        logging.setLevel(HttpLoggingInterceptor.Level.HEADERS);
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);

        Interceptor interceptor = new Interceptor() {

            @Override
            public Response intercept(Chain chain) throws IOException {
                String accessTokenValid;
                accessTokenValid = interceptorModel.getAccessToken();
                Request newRequest = chain.request().newBuilder().
                        addHeader("Content-Type","application/json").
                        header("Authorization", "Bearer "+ accessTokenValid).
                        build();
                return chain.proceed(newRequest);
            }
        };

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectionSpecs(tlsSpecs)
                .addInterceptor(logging)
                .build();

        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .client(client)
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }
}
