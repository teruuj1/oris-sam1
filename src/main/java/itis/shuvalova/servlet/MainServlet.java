package itis.shuvalova.servlet;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import itis.shuvalova.util.client.HttpClient;
import itis.shuvalova.util.client.HttpClientClass;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "mainServlet", urlPatterns = "/main")
public class MainServlet extends HttpServlet {

    private static final String API_KEY = "bd5e378503939ddaee76f12ad7a97608";
    private static final String API_URL = "http://api.openweathermap.org/data/2.5/weather";
    HttpClient client = new HttpClientClass();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String city = req.getParameter("city");
        String weather = null;

        if (city != null && !city.isEmpty()) {
            Map<String, String> params = new HashMap<>();
            params.put("q", city);
            params.put("appid", API_KEY);
            params.put("units", "metric");

            String weatherData = client.get(API_URL, null, params);

            if (weatherData != null) {
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode node = objectMapper.readTree(weatherData);

                weather = node.get("main").get("temp").asText() + " °C";
            }
        }
        req.setAttribute("weather", weather);
        req.getRequestDispatcher("/main.jsp").forward(req, resp);
    }
}
