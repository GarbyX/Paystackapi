package com.garby.fintech.paystackapi.models;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;

public class StartTransaction {
    public InitializeTransactionResponse initTransaction(InitializeTransactionRequest request) throws Exception {
        InitializeTransactionResponse initializeTransactionResponse = null;
        // this variable initializer (null value) is redundant
        try {
            // convert transaction to json then use as a body to post json
            Gson gson = new Gson();
            // add paystack charges
            StringEntity postingString = new StringEntity(gson.toJson(request));
            HttpClient client = HttpClientBuilder.create().build();
            HttpPost post = new HttpPost("https://api.paystack.co/transaction/initialize");
            post.setEntity(postingString);
            post.addHeader("Content-type", "application/json");

            //  post.addHeader("Authorization", "Bearer sk_test_xxxxxxxxxxxxxxxxxxxxxxxxxx");
            post.addHeader("Authorization", "Bearer sk_test_3e9a9b5cbe93e721edd54067fd29efb2ad814374");
            StringBuilder result = new StringBuilder();

            // Raw use of parameterized class i.e. HttpResponse
            HttpResponse response = client.execute(post);
            if (response.getStatusLine().getStatusCode() == STATUS_CODE_OK) {
                BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

                String line;
                while ((line = rd.readLine()) != null) {
                    result.append(line);
                }

            } else {
                throw new NotAuthorizedException("Error Occurred while initializing transaction");
            }
            ObjectMapper mapper = new ObjectMapper();

            initializeTransactionResponse = mapper.readValue(result.toString(), InitializeTransactionResponse.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Failure initializaing transaction");
        }

        return initializeTransactionResponse;
    }
}
