package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Maps")
public class Maps extends HttpServlet {
    private String error;
    GeoApiContext context;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (error != null) {
            response.getWriter().println(error);
            return;
        }

        if (context == null) {
            response.getWriter().println("API context not initialized.");
            return;
        }

        try {
            GeocodingResult[] results = GeocodingApi.geocode(context, "Kaserneboulevarden 29, 8000 Aarhus").await();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            response.getWriter().println(gson.toJson(results[0].addressComponents));
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println(e.getStackTrace());
        }
    }

    @Override
    public void init() throws ServletException {
        context = new GeoApiContext.Builder().apiKey("AIzaSyD-ItwcEug1kJ8W4mVD9wblikKOYSow_gI").build();
    }
}
