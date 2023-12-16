package com.sp.chat.client;

import okhttp3.*;

import java.io.IOException;

public class ChatClient {
    private static final OkHttpClient client = new OkHttpClient();
    private static final String PROTOCOL = "http://";
    private static final String HOST = "localhost";
    private static final String PORT = ":8080";

    public static Response login(String name) throws IOException {
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        Request req = new Request.Builder()
                .post(RequestBody.create(mediaType, ""))
                .url(PROTOCOL + HOST + PORT + "/chat/login?name=" + name)
                .build();
        return client.newCall(req).execute();
    }

    public static Response say(String name, String msg) throws IOException {
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        Request req = new Request.Builder()
                .post(RequestBody.create(mediaType, ""))
                .url(PROTOCOL + HOST + PORT + "/chat/say?name=" + name + "&msg=" + msg)
                .build();
        return client.newCall(req).execute();
    }
}
