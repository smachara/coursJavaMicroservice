package org.example.api;

import com.google.gson.Gson;
import okhttp3.*;
import org.example.model.MessageBean;
import org.example.model.StudentBean;

import java.io.IOException;

public class MyAPI {
    private static Gson gson = new Gson();
    private static String url = "http://localhost:8080";
    public static OkHttpClient client = new OkHttpClient();

    public static String sendGet(String url) throws Exception {
        System.out.println("url : " + url);


        //Création de la requête
        Request request = new Request.Builder().url(url).build();

        //Le try-with ressource doc ici
        //Nous permet de fermer la réponse en cas de succès ou d'échec (dans le finally)
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            assert response.body() != null;
            return response.body().string();
        }
    }

    public static String sendPost(String url, Object toSend ) throws Exception {
        System.out.println("url : " + url);

        String json = gson.toJson(toSend);

        //Corps de la requête
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, json);

        //Création de la requête
        Request request = new Request.Builder().url(url).post(body).build();

        //Le try-with ressource doc ici
        //Nous permet de fermer la réponse en cas de succès ou d'échec (dans le finally)
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            assert response.body() != null;
            return response.body().string();
        }
    }

    public static StudentBean getStudent() {

        String jsonResponse = null;
        try {
            jsonResponse = sendGet(url + "/api/student");
            return gson.fromJson(jsonResponse, StudentBean.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static MessageBean[] getMessage() {

        String jsonResponse = null;
        try {
            jsonResponse = sendGet(url + "/tchat/allMessages");
            return gson.fromJson(jsonResponse, MessageBean[].class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String sendMessage() {
        MessageBean messageBean = new MessageBean("Samer", "My message");
        try {
            return sendPost(url + "/tchat/saveMessage", messageBean);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
