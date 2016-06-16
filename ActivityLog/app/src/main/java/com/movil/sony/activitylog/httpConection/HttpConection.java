package com.movil.sony.activitylog.httpConection;


import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by SONY on 15/06/2016.
 */
public class HttpConection {

    static final int  TIMEOUT_READ = 10000;
    static final int  TIMEOUT_CON= 7000;

    public HttpResponse get (String url) throws IOException {
        URL u = new URL(url);
        HttpURLConnection con = (HttpURLConnection) u.openConnection();

        con.setRequestMethod("GET");
        con.setDoInput(true);
        con.setReadTimeout(TIMEOUT_READ);
        con.setConnectTimeout(TIMEOUT_CON);

        con.connect();

        InputStream in = con.getInputStream();

        HttpResponse resp = new HttpResponse();
        resp.setStatus(con.getResponseCode());
        resp.setMsj(streamToString(in));

        return resp;
    }

    public HttpResponse post(String url, String json) throws IOException {
        return request("POST",url,json);
    }

    public HttpResponse put(String url, String json) throws IOException {
        return request("PUT",url,json);
    }

    public HttpResponse delete (String url, String json) throws IOException {
        return request("DELETE", url,json);
    }


    private HttpResponse request(String metodo, String url, String json) throws IOException {
        URL u = new URL(url);
        HttpURLConnection con = (HttpURLConnection) u.openConnection();
        con.setRequestMethod(metodo);
        con.setDoInput(true);
        con.setReadTimeout(TIMEOUT_READ);
        con.setConnectTimeout(TIMEOUT_CON);

        if (json != null)
            con.setDoOutput(true);
        con.setRequestProperty("Content-Type", "application/json");
        con.connect();

        if (json != null) {
            DataOutputStream out = new DataOutputStream(con.getOutputStream());
            out.write(json.getBytes());
            out.flush();
            out.close();
        }

        InputStream in = con.getInputStream();
        HttpResponse resp = new HttpResponse();
        resp.setStatus(con.getResponseCode());
        resp.setMsj(streamToString(in));
        return resp;

    }


    private String streamToString (InputStream in) throws IOException {
        InputStreamReader reader = new InputStreamReader(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        int ch;
        while ((ch = reader.read()) != -1){
            out.write(ch);
        }

        String rta = new String(out.toByteArray());

        return  rta;
    }
}
