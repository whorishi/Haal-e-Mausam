<%@page import="java.util.concurrent.locks.Condition"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="style.css">
    <title>Haal-e-Mausam ${city}</title>
</head>

<body>
    <div class="card">
    	<div class="search">
    		<form action="myServlet" method="post">
    			<input type="text" placeholder="city" spellcheck="false" name="input_location" value="${city}">
    			<button type="submit"><img src="images/search.png"></button>
    		</form>
    	</div>
    	<% 
    		String cond =  (String)request.getAttribute("weatherCondition");
    		String imgsrc="images/"+cond+".png";
    	%>
    	<div>
    		<img src="<%=imgsrc %>" class="weather-icon">
    		<h1 class="temp">${temperature}°C</h1>
    		<h3 class="city">${city}, ${country}</h3><br>
    		<h6>${date}</h6>
    		<br><hr>
    		<div class="details">
    			<div class="col">
    				<img src="images/humidity.png">
    				<div>
    					<p>${humidity}%</p>
    					<p class="humidity">humidity</p>
    				</div>
    			</div>
    			<div class="col">
    				<img src="images/wind.png">
    				<div>
    					<p>${windSpeed} kmph</p>
    					<p class="wind">wind</p>
    				</div>
    			</div>
    			<div class="col">
    				<img src="images/pressure.png">
    				<div>
    					<p>${pressure} mb</p>
    					<p class="pressure"> pressure</p>
    				</div>
    			</div>
    		</div>
    	</div>
    </div>
    
</body>

</html>