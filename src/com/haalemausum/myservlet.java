package com.haalemausum;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;


@WebServlet("/myServlet")
public class myservlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String location = req.getParameter("input_location");
		String ApiKey = "5cbee294d6cd113aeb2e335aa0311c2b";
		String ApiUrl = "https://api.openweathermap.org/data/2.5/weather?q="+location+"&appid="+ApiKey+"";
		
		try {
			URL url = new URL(ApiUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			InputStream inputStream = connection.getInputStream();
			InputStreamReader reader = new InputStreamReader(inputStream);
			
			Scanner sc = new Scanner(reader);
			StringBuilder responseContent = new StringBuilder();
			
			while(sc.hasNext())
			{
				responseContent.append(sc.nextLine());
			}
			sc.close();
			
			Gson gson = new Gson();
	        JsonObject jsonObject = gson.fromJson(responseContent.toString(), JsonObject.class);
			
	        String city = jsonObject.get("name").getAsString();
	        
	        long dateTimeStamp = jsonObject.get("dt").getAsLong() * 1000;
	        String date = new Date(dateTimeStamp).toString();
	        
	        double tempKelvin = jsonObject.getAsJsonObject("main").get("temp").getAsDouble();
	        int tempCelcius = (int)(tempKelvin - 273.15);
	        
	        int humidity = jsonObject.getAsJsonObject("main").get("humidity").getAsInt();
	        
	        int pressure = jsonObject.getAsJsonObject("main").get("pressure").getAsInt();
	        
	        double windSpeed = jsonObject.getAsJsonObject("wind").get("speed").getAsDouble(); 
			
			String weatherCondition = jsonObject.getAsJsonArray("weather").get(0).getAsJsonObject().get("main").getAsString().toLowerCase();
					
			String visibility = jsonObject.get("visibility").getAsString();
			
			String country = jsonObject.getAsJsonObject("sys").get("country").getAsString();
			
			req.setAttribute("date", date);
			req.setAttribute("city", city);
			req.setAttribute("country", country);
			req.setAttribute("visibility", visibility);
			req.setAttribute("temperature", tempCelcius);
			req.setAttribute("humidity", humidity);
			req.setAttribute("pressure", pressure);
			req.setAttribute("windSpeed", windSpeed);
			req.setAttribute("weatherCondition", weatherCondition);
			req.setAttribute("weatherData", responseContent.toString());
			
			connection.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		req.getRequestDispatcher("index.jsp").forward(req,resp);
		
		
	}
	
}
