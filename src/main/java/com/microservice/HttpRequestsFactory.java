package com.microservice;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * @author Anurag Muthyam
 * url: https://github.com/aryaghan-mutum
 */

public class HttpRequestsFactory {

    /**
     * Http GET method
     * @param url
     * @param appKey
     * @return
     */
    public static JsonElement jsonGET(String url, String appKey) {
        WebClient webClient = WebClient.create();

        String bodyString = webClient.get()
                .uri(url)
                .header("AppKey", appKey)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        JsonParser parser = new JsonParser();
        JsonElement doc = parser.parse(bodyString);
        return doc;
    }


    /**
     * HTTP POST the service
     * @param url
     * @param body
     * @param appKey
     * @return
     */
    public static JsonElement jsonPOST(String url, JsonElement body, String appKey) {
        Gson gson = new Gson();
        String requestJson = gson.toJson(body);
        return httpPOST(url, appKey, requestJson);
    }

    /**
     * HTTP POST
     * @param url
     * @param appKey
     * @param requestJson
     * @return
     */
    public static JsonElement httpPOST(String url, String appKey, String requestJson) {

        WebClient webClient = WebClient.create();

        String bodyString = webClient.post()
                .uri(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .header("AppKey", appKey)
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromPublisher(Mono.just(requestJson), String.class))
                .retrieve()
                .bodyToMono(String.class)
                .block();

        if (bodyString != null) {
            JsonParser parser = new JsonParser();

            return parser.parse(bodyString);
        } else {
            return null;
        }
    }

    /**
     * Http Delete method
     * @param url
     * @param appKey
     */
    public static void httpDELETE(String url, String appKey) {
        WebClient webClient = WebClient.create();

        webClient.delete()
                .uri(url)
                .header("AppKey", appKey)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

}
