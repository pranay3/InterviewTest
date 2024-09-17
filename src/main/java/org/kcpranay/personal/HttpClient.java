package org.kcpranay.personal;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class HttpClient {
    OkHttpClient client = new OkHttpClient.Builder()
            .build();

    public String callGet(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("Accept", "application/json")
                .build();

        Call call = client.newCall(request);
        Response response = call.execute();
        return response.body().string();
    }
}
