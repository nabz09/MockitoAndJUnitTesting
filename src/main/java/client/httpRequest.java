package client;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class httpRequest {
    final static Logger LOGGER = LoggerFactory.getLogger(httpRequest.class);

    public static void main(String args[]) throws IOException {
        makePostRequest();
    }

    private static void makeGetRequest() throws IOException {
        HttpClient client = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://httpbin.org/ip");

        HttpResponse response = client.execute(httpGet);
        HttpEntity entity = response.getEntity();
        if(entity != null) {
            try {
                String result = EntityUtils.toString(response.getEntity());
                LOGGER.info("reslt {}", result);
            } finally {
                EntityUtils.consume(response.getEntity());
            }
        }
    }

    private static void makePostRequest() throws IOException {
        HttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://httpbin.org/post");

        ////////How work does it/////////////////////////
        String jsonRequest = "{" +
                "\"license\" : \"4gdhhgf\"," +
                "\"age\" : \"18\"," +
                "\"location\" : \"Birmingham\"," +
                "}";
        JSONObject jsonObject = new JSONObject(jsonRequest);
        System.out.println(jsonObject.get("location"));

        ///////////Alternative/////////////////////////
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("license", "4fhd643fg"));
        params.add(new BasicNameValuePair("age", "18"));
        params.add(new BasicNameValuePair("location", "Birmingham"));
        // httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

        httpPost.setEntity(new StringEntity(jsonRequest));
        HttpResponse response = client.execute(httpPost);
        HttpEntity entity = response.getEntity();
        System.out.println("post request: "+ EntityUtils.toString(httpPost.getEntity()));

        if(entity != null) {
            InputStream inputStream = entity.getContent();
            try{
                String result = EntityUtils.toString(response.getEntity());
                //System.out.println(result);
            } finally {
                inputStream.close();
            }
        }
    }
}