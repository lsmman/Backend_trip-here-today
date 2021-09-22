package com.devHyun.TodayTrip01.Util;

import com.devHyun.TodayTrip01.Configuration.RestTemplateConfig;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

class Area{
    private String hi;

    public String getHi() {
        return hi;
    }

    public void setHi(String hi) {
        this.hi = hi;
    }

    public Area(String hi) {
        this.hi = hi;
    }
}

public class TripDataLoader {
    private RestTemplate restTemplate = new RestTemplateConfig().restTemplate();
    private String endpointURL = "http://api.visitkorea.or.kr/openapi/service/rest/KorService";
    private final String serviceKey = new KeyLoader().getServiceKey();
    private int numOfRows = 100000;

    public void _request_example() throws Exception {
        String URL = "http://localhost:8080/api/Test";
        String response = restTemplate.getForObject(URL, String.class);
        System.out.println(response);
//        ResponseEntity<String> response = restTemplate.exchange(
//                URL,
//                HttpMethod.GET,
//                null,
//                String.class,
//                1
//        );
    }


    public void getAreaApi(){
        String Url = endpointURL + "/areaCode";
        HttpHeaders headers = new HttpHeaders();
        Map<String, String> param = new HashMap<>();
        HttpEntity request;

        param.put("ServiceKey", serviceKey);
        param.put("numOfRows", Integer.toString(numOfRows));
        param.put("pageNo", "10000");
        param.put("areaCode", "1");


        headers.set("MobileOS", "ETC");
        headers.set("MobileApp", "TodayHere");

        request = new HttpEntity(headers);
        ResponseEntity<String> response = restTemplate.exchange(
                Url,
                HttpMethod.GET,
                request,
                String.class,
                1
        );

        if (response.getStatusCode() == HttpStatus.OK){
            System.out.println("Request Successful");
            System.out.println(response.getBody());
        }
        else {
            System.out.println("Request Failed : " + response.getStatusCode());
        }
    }
    public static void main(String[] args) {
        new TripDataLoader().getAreaApi();
    }
}
