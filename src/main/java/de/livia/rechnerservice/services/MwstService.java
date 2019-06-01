package de.livia.rechnerservice.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@Service
public class MwstService {
    Logger log = LoggerFactory.getLogger(this.getClass().getName());

    static final String URL_VAT = "http://localhost:8282/api/rest/mwst?value={interimResult}";


    public double calculateVat(double result) {

        // consume RESTful web services
        // send HTTP Request and also handle the HTTP Response
        RestTemplate restTemplate = new RestTemplate();

        HashMap<String, Double> params = new HashMap<>();
        params.put("interimResult", result);

        Double vat = restTemplate.getForObject(URL_VAT, Double.class, params);

        return vat.doubleValue();
    }

}
