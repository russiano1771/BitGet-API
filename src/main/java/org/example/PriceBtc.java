//package org.example;
//
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.Response;
//import org.json.JSONObject;
//
//
////
////import com.google.gson.Gson;
////import okhttp3.*;
////
////import java.io.IOException;
////
////public class BitgetAPIExample {
////    private static final String API_URL = "https://api.bitget.com";
////    private static final String SYMBOL = "btcusdt";
////
////    public static void main(String[] args) {
////        OkHttpClient client = new OkHttpClient();
////
////        Request request = new Request.Builder()
////                .url("https://api.bitget.com/api/spot/v1/market/ticker?symbol=BTCUSDT_SPBL")
////                .build();
////
////        Call call = client.newCall(request);
////        call.enqueue(new Callback() {
////            @Override
////            public void onFailure(Call call, IOException e) {
////                e.printStackTrace();
////            }
////
////            @Override
////            public void onResponse(Call call, Response response) throws IOException {
////                if (response.isSuccessful()) {
////                    String responseBody = response.body().string();
////                    Gson gson = new Gson();
////                    TickerResponse tickerResponse = gson.fromJson(responseBody, TickerResponse.class);
////                    System.out.println("Last price: " + tickerResponse.getLast());
////                } else {
////                    System.out.println("Request failed. Response code: " + response.code());
////                }
////            }
////        });
////    }
////
////    private static class TickerResponse {
////        private String symbol;
////        private String last;
////
////        public String getSymbol() {
////            return symbol;
////        }
////
////        public String getLast() {
////            return last;
////        }
////    }
////}
//
//
//
//import com.google.gson.Gson;
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URL;
//
//public class BinanceAPIExample {
//    public static void main(String[] args) {
//        try {
//            // Создание URL-адреса API
//            String apiUrl = "https://api.bitget.com/api/api/spot/v1/market/ticker?symbol=btcusdt_spb";
//            URL url = new URL(apiUrl);
//
//            // Создание HTTP-соединения
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            conn.setRequestMethod("GET");
//
//            // Получение ответа от сервера
//            int responseCode = conn.getResponseCode();
//            if (responseCode == HttpURLConnection.HTTP_OK) {
//                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//                String inputLine;
//                StringBuilder response = new StringBuilder();
//
//                while ((inputLine = in.readLine()) != null) {
//                    response.append(inputLine);
//                }
//                in.close();
//
//                // Разбор JSON-ответа и получение курса Bitcoin
//                double btcPrice = parseBtcPrice(response.toString());
//                System.out.println("Current Bitcoin Price: " + btcPrice);
//            } else {
//                System.out.println("Request failed. Response code: " + responseCode);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static double parseBtcPrice(String response) {
//        Gson gson = new Gson();
//        BinanceTickerResponse tickerResponse = gson.fromJson(response, BinanceTickerResponse.class);
//        return Double.parseDouble(tickerResponse.price);
//    }
//
//    private static class BinanceTickerResponse {
//        String symbol;
//        String price;
//    }
//}
//


package org.example;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

public class PriceBtc {
    public static void price() {
        try {
            // Создание HTTP-клиента
            OkHttpClient client = new OkHttpClient();

            // Создание запроса к API Bitget для получения текущего курса BTCUSDT
            String apiUrl = "https://api.bitget.com/api/spot/v1/market/ticker?symbol=BTCUSDT_SPBL";
            Request request = new Request.Builder()
                    .url(apiUrl)
                    .build();

            // Отправка запроса и получение ответа
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                String sellOne = parseSellOne(responseBody);
                System.out.println("Currently Market Price: " + sellOne);
            } else {
                System.out.println("Request failed. Response code: " + response.code());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String parseSellOne(String responseBody) {
        // Разбор JSON-ответа и получение значения ключа "sellOne"
        JSONObject json = new JSONObject(responseBody);
        JSONObject data = json.getJSONObject("data");
        String sellOne = data.getString("sellOne");
        return sellOne;
    }
}