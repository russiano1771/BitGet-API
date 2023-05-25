package org.example;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.apache.commons.codec.binary.Hex;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.time.Instant;

public class BuyBitcoin {
    private static final String API_KEY = "bg_bb9afaeb9c417a9712af9a6f52f2bc34";
    private static final String SECRET_KEY = "735d762d711ff28d22511971a70a01836eb33d45af583006b2298ec8df1ceb5e";
    private static final String API_BASE_URL = "https://api.bitget.com";

    public void Buy(String Orderid) {
        String orderId = Orderid; // Идентификатор ордера на продажу
        String symbol = "BTCUSDT"; // Пара валют

        // Создание подписи для авторизации запроса
        String timestamp = Instant.now().toEpochMilli() + "";
        String signature = generateSignature(API_KEY, SECRET_KEY, timestamp);

        // Создание HTTP-клиента
        OkHttpClient client = new OkHttpClient();

        // Создание запроса на выкуп ордера
        String apiUrl = API_BASE_URL + "/api/spot/v1/orders/" + orderId + "/trigger";
        RequestBody requestBody = RequestBody.create("", MediaType.get("application/json"));
        Request request = new Request.Builder()
                .url(apiUrl)
                .header("Content-Type", "application/json")
                .header("APIKEY", API_KEY)
                .header("Timestamp", timestamp)
                .header("SIGNATURE", signature)
                .post(requestBody)
                .build();

        try {
            // Отправка запроса и получение ответа
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                System.out.println("Order executed successfully");
            } else {
                System.out.println("Request failed. Response code: " + response.code());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String generateSignature(String apiKey, String secretKey, String timestamp) {
        try {
            String data = apiKey + timestamp;
            Mac mac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), "HmacSHA256");
            mac.init(secretKeySpec);
            byte[] hmacData = mac.doFinal(data.getBytes());
            return Hex.encodeHexString(hmacData);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
