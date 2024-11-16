package itis.shuvalova.util.client;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class HttpClientClass implements HttpClient {

    @Override
    public String get(String url, Map<String, String> headers, Map<String, String> params) {
        try {
            StringBuilder urlWithParams = new StringBuilder(url);
            if (params != null && !params.isEmpty()) {
                urlWithParams.append("?").append(getParamsString(params));
            }

            HttpURLConnection connection = (HttpURLConnection) new URL(urlWithParams.toString()).openConnection();
            connection.setRequestMethod("GET");

            if (headers != null) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    connection.setRequestProperty(entry.getKey(), entry.getValue());
                }
            }

            return readResponse(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String post(String url, Map<String, String> headers, Map<String, String> data) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            if (headers != null) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    connection.setRequestProperty(entry.getKey(), entry.getValue());
                }
            }

            if (data != null && !data.isEmpty()) {
                String postData = getParamsString(data);
                try (OutputStream os = connection.getOutputStream()) {
                    byte[] input = postData.getBytes(StandardCharsets.UTF_8);
                    os.write(input, 0, input.length);
                }
            }

            return readResponse(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String put(String url, Map<String, String> headers, Map<String, String> data) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("PUT");
            connection.setDoOutput(true);

            if (headers != null) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    connection.setRequestProperty(entry.getKey(), entry.getValue());
                }
            }

            if (data != null && !data.isEmpty()) {
                String putData = getParamsString(data);
                try (OutputStream os = connection.getOutputStream()) {
                    byte[] input = putData.getBytes(StandardCharsets.UTF_8);
                    os.write(input, 0, input.length);
                }
            }

            return readResponse(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String delete(String url, Map<String, String> headers, Map<String, String> data) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("DELETE");
            connection.setDoOutput(true);

            if (headers != null) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    connection.setRequestProperty(entry.getKey(), entry.getValue());
                }
            }

            if (data != null && !data.isEmpty()) {
                String deleteData = getParamsString(data);
                try (OutputStream os = connection.getOutputStream()) {
                    byte[] input = deleteData.getBytes(StandardCharsets.UTF_8);
                    os.write(input, 0, input.length);
                }
            }

            return readResponse(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String readResponse(HttpURLConnection connection) throws IOException {
        StringBuilder response = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
        }
        return response.toString();
    }

    private String getParamsString(Map<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (!result.isEmpty()) {
                result.append("&");
            }
            result.append(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8));
        }
        return result.toString();
    }
}