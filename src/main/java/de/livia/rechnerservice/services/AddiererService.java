package de.livia.rechnerservice.services;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@Service
public class AddiererService {

    Logger log = LoggerFactory.getLogger(this.getClass().getName());

    static final String URL_ADDIERER = "http://localhost:8181/api/rest/addiere?summand1={summand1}&summand2={summand2}";


    public double calculateTotal(double summand1, double summand2){

        log.debug("calculateTax...");

        RestTemplate restTemplate = new RestTemplate();

        HashMap<String, Double> params = new HashMap<>();
        params.put("summand1", summand1);
        params.put("summand2", summand2);

        Double result = restTemplate.getForObject(URL_ADDIERER, Double.class, params);
        log.debug("Result: " + result);


        return result.doubleValue();

    }


    public static void main(String[] args) {

        AddiererService addiererService = new AddiererService();
        addiererService.calculateTotal(10.50, 20.00);

    }
}
