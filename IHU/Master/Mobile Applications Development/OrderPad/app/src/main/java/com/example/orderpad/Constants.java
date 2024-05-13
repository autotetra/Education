package com.example.orderpad;

public class Constants {
    public static final String BASE_URL = "http://mad.mywork.gr/";
    public static final String API_TOKEN = "2973";
    public static final String GET_COFFEE_DATA_URL = BASE_URL + "get_coffee_data.php?t=" + API_TOKEN;
    public static final String SEND_ORDER_URL = BASE_URL + "send_order.php?t=" + API_TOKEN;
    public static final String GET_ORDER_URL = BASE_URL + "get_order.php?t" + API_TOKEN + "&tid=";
    public static final String SEND_PAYMENT = BASE_URL + "send_payment.php?t" + API_TOKEN + "&tid=";
}
