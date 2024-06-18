package org.example.api;

import com.google.gson.Gson;
import okhttp3.*;
import org.example.model.MessageBean;

import java.io.IOException;

public class TchatAPI {

    private static final String MON_API = "http://localhost:8080";
    private static final Gson gson = new Gson();
    private static final OkHttpClient client = new OkHttpClient();

    /* -------------------------------- */
    // Tchat API
    /* -------------------------------- */

    public static void sendMessage(MessageBean message) throws Exception {
        //http://localhost:8080/tchat/saveMessage
        sendPost(MON_API + "/tchat/saveMessage", message);
    }

    public static MessageBean[] getAllMessages() throws Exception {
        //http://localhost:8080/tchat/allMessages
        String res =  sendGet(MON_API + "/tchat/allMessages");

        return gson.fromJson(res, MessageBean[].class);
    }

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
            return response.body().string();
        }
    }

    public static String sendPost(String url, Object toSend) throws Exception {
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
            return response.body().string();
        }
    }
}
