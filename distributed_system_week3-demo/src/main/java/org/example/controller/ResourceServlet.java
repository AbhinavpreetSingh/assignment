package org.example.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.example.model.Audio;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;


@WebServlet(name = "audios", value = "audios/*")
public class ResourceServlet extends HttpServlet {
	
	//ConcurrentHashMap is thread safe; 
	ConcurrentHashMap<String, Audio> audioDB = new ConcurrentHashMap<>();
	
	//simply emulation of in memory database;  
	@Override
	public void init() throws ServletException {
		
		
		audioDB.put("audio_1", new Audio("1","50 cents","Candy Shop","Candy Shop","12",1991,"12321312",10));
		audioDB.put("audio_2", new Audio("2","coldplay","Candy Shop","Fix you","12",1992,"12321312",10));
		
//		Audio audio1 = new Audio("1", "artist_name_1", "track_title_1", "album_title_1", "3", 1998, "4", 2);
//		Audio audio2 = new Audio("2", "artist_name_2", "track_title_2", "album_title_2", "3", 1997, "4", 3);
//		Audio audio3 = new Audio("3", "artist_name_3", "track_title_3", "album_title_3", "3", 1999, "4", 4);
//		Audio audio4 = new Audio("4", "artist_name_4", "track_title_4", "album_title_4", "3", 1999, "4", 5);
//		audioDB.put(audio1.getId(), audio1);
//		audioDB.put(audio2.getId(), audio2);
//		audioDB.put(audio3.getId(), audio3);
//		audioDB.put(audio4.getId(), audio4);
		
		 
	 }
	
	private int totalSoldCopies = 0;
	
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pathInfo = request.getPathInfo();
		System.out.println(pathInfo);
		if (pathInfo == null) {
			
			List<Object> audios = audioDB.entrySet().stream()
									.filter(entry -> entry.getKey().matches("audio.*"))
									.map(Map.Entry::getValue)
									.collect(Collectors.toList());
			List<Audio> allAudios = new ArrayList<>();
//		    
		    for (Audio a : audioDB.values()) {
		    	allAudios.add(a);
		    }
		    
		    for (Audio a : allAudios) {
		    	totalSoldCopies += a.getSold_copies();
		    }
			
			Gson gson = new Gson();
		    JsonElement element = gson.toJsonTree(audioDB);

		    PrintWriter out = response.getWriter();
		    response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
//		    out.println("GET RESPONSE IN JSON - single element: " + gson.toJson(audio));
		    out.println("GET RESPONSE IN JSON - all elements " + element.toString());
		    out.println(totalSoldCopies);
		    out.flush();
        } else {

        	String propertyName = pathInfo.substring(1);
        	System.out.println(pathInfo);
     		Audio audio = (Audio) audioDB.get("audio_1");
     		String propertyValue = "";
     		
      		if("id".equals(propertyName)) propertyValue = audio.getId();
     		else if("artistName".equals(propertyName)) propertyValue = audio.getArtistName();
     		else if("trackTitle".equals(propertyName)) propertyValue = audio.getTrackTitle();
     		else if("albumTitle".equals(propertyName)) propertyValue = audio.getAlbumTitle();
     		else if("trackNumber".equals(propertyName)) propertyValue = audio.getTrackNumber();
     		else if("year".equals(propertyName)) propertyValue = Integer.toString(audio.getYear());
     		else if("number_of_reviews".equals(propertyName)) propertyValue = audio.getNumber_of_reviews();
     		else if("sold_copies".equals(propertyName)) propertyValue = Integer.toString(audio.getSold_copies());

     		PrintWriter out = response.getWriter();
     		response.setContentType("application/json");
     		response.setStatus(HttpServletResponse.SC_OK);
     		response.setCharacterEncoding("UTF-8");
             
            out.println(propertyValue);
            out.flush();
        }
        }
//	@Override
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//	    String paramName = request.getParameter("paramName");
//		if(paramName == null) {
//			Gson gson = new Gson();
//		    JsonElement element = gson.toJsonTree(audioDB);
//		    List<Audio> allAudios = new ArrayList<>();
//		    
//		    for (Audio a : audioDB.values()) {
//		    	allAudios.add(a);
//		    }
//		    
//		    for (Audio a : allAudios) {
//		    	totalSoldCopies += a.getSold_copies();
//		    }
//
//		    PrintWriter out = response.getWriter();
//		    response.setContentType("application/json");
//		    response.setCharacterEncoding("UTF-8");
//		    out.println("GET RESPONSE IN JSON - all elements " + element.toString());
//		    out.println("Total sold copies: " + totalSoldCopies);
//		    out.flush();
//		}else {
//		
//	    String value = request.getParameter(paramName);
//
//	    Audio audio = null;
//	    List<Audio> matchingAudios = new ArrayList<>();
//
//	    if ("artistName".equals(paramName) && value != null) {
//	        for (Audio a : audioDB.values()) {
//	            if (value.equals(a.getArtistName())) {
//	            	matchingAudios.add(a);
//	            }
//	        }
//	    }else if("id".equals(paramName) && value != null) {
//	    	int id = Integer.parseInt(value);
//	    	for (Audio a : audioDB.values()) {
//	    		if (id == (a.getId())) {
//	    			matchingAudios.add(a);
//	            }
//	        }
//	    }else if("trackTitle".equals(paramName) && value != null) {
//	    	for (Audio a : audioDB.values()) {
//	    		if (value.equals(a.getTrackTitle())) {
//	    			matchingAudios.add(a);
//	            }
//	        }
//	    }else if("albumTitle".equals(paramName) && value != null) {
//	    	for (Audio a : audioDB.values()) {
//	    		if (value.equals(a.getAlbumTitle())) {
//	    			matchingAudios.add(a);
//	            }
//	        }
//	    }else if("year".equals(paramName) && value != null) {
//	    	int year = Integer.parseInt(value);
//	    	
//	    	for (Audio a : audioDB.values()) {
//	    		if (year == (a.getYear())) {
//	    			matchingAudios.add(a);
//	            }
//	        }
//	    }
//	    for (Audio a : matchingAudios) {
//	    	totalSoldCopies += a.getSold_copies();
//	    }
//
//	    Gson gson = new Gson();
//	    String jsonResponse = matchingAudios.isEmpty() ? "{\"error\": \"No matching Audio found\"}" : gson.toJson(matchingAudios); 
//
//	    PrintWriter out = response.getWriter();
//	    response.setContentType("application/json");
//	    response.setCharacterEncoding("UTF-8");
//	    out.println("Total copies sold: " + totalSoldCopies);
//	    out.print(jsonResponse);
//	    out.flush();
//	}
//	}
//
//
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
               
    	String id = request.getParameter("id");
        String artistName = request.getParameter("artistName");
        String albumTitle = request.getParameter("albumTitle");
        String trackTitle = request.getParameter("trackTitle");
        String trackNumber = request.getParameter("trackNumber");
        int year = Integer.parseInt(request.getParameter("year"));
        String number_of_reviews = request.getParameter("number_of_reviews");
        int sold_copies = Integer.parseInt(request.getParameter("sold_copies"));
        
        Audio audio = new Audio(id, artistName, trackTitle, albumTitle, trackNumber, year, number_of_reviews, sold_copies);
        
        audioDB.put(id, audio);
        response.setStatus(200);
    	
    	response.getOutputStream().println("POST RESPONSE: Audio " + audio + " is added to the database.");
    }
}
