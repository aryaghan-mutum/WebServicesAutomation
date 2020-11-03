package com.microservice;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static com.microservice.BaseResponse.getJsonResponse;
import static com.microservice.BaseResponse.getWebClient;

/**
 * @author Anurag Muthyam
 * url: https://github.com/aryaghan-mutum
 */

public class HttpRequestsBuilder {

    /**
     * GET Http method
     * @param url ECR endpoint
     * @return the ECR response based on the environment
     */
    public static JsonElement jsonGet(String url, String appKey) {
        WebClient webClient = getWebClient(url);

        String bodyString = webClient.get()
                .header("AppKey", appKey)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return getJsonResponse(bodyString);
    }

    /**
     * GET Http method
     * @param url Ship Deployment endpoint
     * @return Ship Deployment response based on the environment
     */
    public static JsonElement jsonGet(String url, String username, String password) {
        WebClient webClient = getWebClient(url);

        String responseString = webClient.get()
                .headers(headers -> headers.setBasicAuth(username, password))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return getJsonResponse(responseString);
    }

    /**
     * Http Delete method
     * @param url
     * @param appKey
     */
    public static void httpDELETE(String url, String appKey) {
        WebClient webClient = getWebClient(url);
        webClient.delete()
                .header("AppKey", appKey)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    /**
     * Http Post method
     * @param url
     * @param body
     * @param appKey
     * @return HTTP POST the service
     */
    public static JsonElement jsonPOST(String url, JsonElement body, String appKey) {
        Gson gson = new Gson();
        String requestJson = gson.toJson(body);

        return httpPOST(url, appKey, requestJson);
    }

    /**
     * Http Post method
     * @param url
     * @param appKey
     * @param requestJson
     * @return
     */
    public static JsonElement httpPOST(String url, String appKey, String requestJson) {

        WebClient webClient = getWebClient(url);

        String bodyString = webClient.post()
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

}
