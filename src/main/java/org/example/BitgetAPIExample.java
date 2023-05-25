package org.example;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

public class BitgetAPIExample {
    public static void main(String[] args) {
        // Создание таймера
        Timer timer = new Timer();
        PriceBtc.price();



        // Задача для выполнения запроса и вывода ордеров
        TimerTask task = new TimerTask() {

            private static String parseSellOne(String responseBody) {
                // Разбор JSON-ответа и получение значения ключа "sellOne"
                JSONObject json = new JSONObject(responseBody);
                JSONObject data = json.getJSONObject("data");
                String sellOne = data.getString("sellOne");
                return sellOne;
            }
            @Override
            public void run() {
                try {
                    // Создание HTTP-клиента
                    OkHttpClient client = new OkHttpClient();

                    // Создание запроса к API Bitget для получения спотовых ордеров на продажу BTCUSDT
                    String apiUrl = "https://api.bitget.com/api/spot/v1/market/fills?symbol=BTCUSDT_SPBL";
                    Request request = new Request.Builder()
                            .url(apiUrl)
                            .build();

                    // Отправка запроса и получение ответа
                    Response response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        String responseBody = response.body().string();
                        JSONArray orders = parseOrders(responseBody);
                        printOrders(orders);
                    } else {
                        System.out.println("Request failed. Response code: " + response.code());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        // Запуск задачи с интервалом 5 секунд
        timer.scheduleAtFixedRate(task, 0, 5000);
    }

    private static JSONArray parseOrders(String responseBody) {
        // Разбор JSON-ответа и получение списка ордеров
        JSONObject json = new JSONObject(responseBody);
        JSONArray orders = json.getJSONArray("data");
        return orders;
    }

    private static void printOrders(JSONArray orders) {
        // Вывод ордеров
        System.out.println("=== Spot Sell Orders ===");
        for (int i = 0; i < orders.length(); i++) {
//            JSONObject order = orders.getJSONObject(i);
//            String orderId = order.getString("orderId");
//            String price = order.getString("price");
//            String quantity = order.getString("quantity");
//            System.out.println("Order ID: " + orderId);
//            System.out.println("Price: " + price);
//            System.out.println("Quantity: " + quantity);
//            System.out.println("------------------------");

            JSONObject order = orders.getJSONObject(i);
            String price = order.getString("fillPrice");
            String orderId = order.getString("tradeId");
            String quantity = order.getString("fillQuantity");
            System.out.println("Order ID: " + orderId);
            System.out.println("Price: " + price);
            System.out.println("Quantity: " + quantity);
            System.out.println("------------------------");
        }


    }
}












// work get last spot orders
//package org.example;
//
//        import okhttp3.OkHttpClient;
//        import okhttp3.Request;
//        import okhttp3.Response;
//        import org.json.JSONArray;
//        import org.json.JSONObject;
//
//        import java.util.Timer;
//        import java.util.TimerTask;
//
//public class BitgetAPIExample {
//    public static void main(String[] args) {
//        // Создание таймера
//        Timer timer = new Timer();
//
//        // Задача для выполнения запроса и вывода ордеров
//        TimerTask task = new TimerTask() {
//            @Override
//            public void run() {
//                try {
//                    // Создание HTTP-клиента
//                    OkHttpClient client = new OkHttpClient();
//
//                    // Создание запроса к API Bitget для получения спотовых ордеров на продажу BTCUSDT
//                    String apiUrl = "https://api.bitget.com/api/spot/v1/market/fills?symbol=BTCUSDT_SPBL";
//                    Request request = new Request.Builder()
//                            .url(apiUrl)
//                            .build();
//
//                    // Отправка запроса и получение ответа
//                    Response response = client.newCall(request).execute();
//                    if (response.isSuccessful()) {
//                        String responseBody = response.body().string();
//                        JSONArray orders = parseOrders(responseBody);
//                        printOrders(orders);
//                    } else {
//                        System.out.println("Request failed. Response code: " + response.code());
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        };
//
//        // Запуск задачи с интервалом 5 секунд
//        timer.scheduleAtFixedRate(task, 0, 5000);
//    }
//
//    private static JSONArray parseOrders(String responseBody) {
//        // Разбор JSON-ответа и получение списка ордеров
//        JSONObject json = new JSONObject(responseBody);
//        JSONArray orders = json.getJSONArray("data");
//        return orders;
//    }
//
//    private static void printOrders(JSONArray orders) {
//        // Вывод ордеров
//        System.out.println("=== Spot Sell Orders ===");
//        for (int i = 0; i < orders.length(); i++) {
////            JSONObject order = orders.getJSONObject(i);
////            String orderId = order.getString("orderId");
////            String price = order.getString("price");
////            String quantity = order.getString("quantity");
////            System.out.println("Order ID: " + orderId);
////            System.out.println("Price: " + price);
////            System.out.println("Quantity: " + quantity);
////            System.out.println("------------------------");
//
//            JSONObject order = orders.getJSONObject(i);
//            String price = order.getString("fillPrice");
//            String orderId = order.getString("tradeId");
//            String quantity = order.getString("fillQuantity");
//            System.out.println("Order ID: " + orderId);
//            System.out.println("Price: " + price);
//            System.out.println("Quantity: " + quantity);
//            System.out.println("------------------------");
//        }
//    }
//}


















//package org.example;
//
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.Response;
//import org.json.JSONObject;
//
//public class BitgetAPIExample {
//    public static void main(String[] args) {
//        try {
//            // Создание HTTP-клиента
//            OkHttpClient client = new OkHttpClient();
//
//            // Создание запроса к API Bitget для получения текущего курса BTCUSDT
//            String apiUrl = "https://api.bitget.com/api/spot/v1/market/ticker?symbol=BTCUSDT_SPBL";
//            Request request = new Request.Builder()
//                    .url(apiUrl)
//                    .build();
//
//            // Отправка запроса и получение ответа
//            Response response = client.newCall(request).execute();
//            if (response.isSuccessful()) {
//                String responseBody = response.body().string();
//                String sellOne = parseSellOne(responseBody);
//                System.out.println("Sell One: " + sellOne);
//            } else {
//                System.out.println("Request failed. Response code: " + response.code());
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static String parseSellOne(String responseBody) {
//        // Разбор JSON-ответа и получение значения ключа "sellOne"
//        JSONObject json = new JSONObject(responseBody);
//        JSONObject data = json.getJSONObject("data");
//        String sellOne = data.getString("sellOne");
//        return sellOne;
//    }
//}
