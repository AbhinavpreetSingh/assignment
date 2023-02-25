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

import org.example.model.Audio;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

//@WebServlet(name = "skiiers", value = "skiiers")
@WebServlet(name = "audios", value = "audios")
public class ResourceServlet extends HttpServlet {
    
	
	/*
	 * ConcurrentHashMap is thread safe; 
	 */
	ConcurrentHashMap<Integer, Audio> audioDB = new ConcurrentHashMap<>();
	
	/*
	 * simply emulation of in memory database;  
	 */
	@Override
	public void init() throws ServletException {
		Audio audio1 = new Audio(1, "artist_name_1", "track_title_1", "album_title_1", 3, 1998, 4, 2);
		Audio audio2 = new Audio(2, "artist_name_2", "track_title_2", "album_title_2", 3, 1997, 4, 3);
		Audio audio3 = new Audio(3, "artist_name_3", "track_title_3", "album_title_3", 3, 1999, 4, 4);
		Audio audio4 = new Audio(4, "artist_name_4", "track_title_4", "album_title_4", 3, 1999, 4, 5);
		audioDB.put(audio1.getId(), audio1);
		audioDB.put(audio2.getId(), audio2);
		audioDB.put(audio3.getId(), audio3);
		audioDB.put(audio4.getId(), audio4);
		
		 
	 }
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String pathInfo = request.getPathInfo();
//		System.out.println(pathInfo);
	    String paramName = request.getParameter("paramName");
		if(paramName == null) {
//			Audio audio = null;
			Gson gson = new Gson();
		    JsonElement element = gson.toJsonTree(audioDB);

		    PrintWriter out = response.getWriter();
		    response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
//		    out.println("GET RESPONSE IN JSON - single element: " + gson.toJson(audio));
		    out.println("GET RESPONSE IN JSON - all elements " + element.toString());
		    out.flush();
		}else {
		
//	    String paramName = request.getParameter("paramName");
	    String value = request.getParameter(paramName);

	    Audio audio = null;
	    List<Audio> matchingAudios = new ArrayList<>();

	    if ("artistName".equals(paramName) && value != null) {
	        for (Audio a : audioDB.values()) {
	            if (value.equals(a.getArtistName())) {
	            	matchingAudios.add(a);
//	                audio = a;
//	                break;
	            }
	        }
	    }else if("id".equals(paramName) && value != null) {
	    	int id = Integer.parseInt(value);
	    	for (Audio a : audioDB.values()) {
	    		if (id == (a.getId())) {
	    			matchingAudios.add(a);
//	                audio = a;
//	                break;
	            }
	        }
	    }else if("trackTitle".equals(paramName) && value != null) {
	    	for (Audio a : audioDB.values()) {
	    		if (value.equals(a.getTrackTitle())) {
	    			matchingAudios.add(a);
//	                audio = a;
//	                break;
	            }
	        }
	    }else if("albumTitle".equals(paramName) && value != null) {
	    	for (Audio a : audioDB.values()) {
	    		if (value.equals(a.getAlbumTitle())) {
	    			matchingAudios.add(a);
//	                audio = a;
//	                break;
	            }
	        }
	    }else if("year".equals(paramName) && value != null) {
	    	int year = Integer.parseInt(value);
	    	
	    	for (Audio a : audioDB.values()) {
	    		if (year == (a.getYear())) {
	    			matchingAudios.add(a);
	            }
	        }
	    }

	    Gson gson = new Gson();
	    String jsonResponse = matchingAudios.isEmpty() ? "{\"error\": \"No matching Audio found\"}" : gson.toJson(matchingAudios); // audio != null ? gson.toJson(audio) : "{\"error\": \"Audio not found\"}";

	    PrintWriter out = response.getWriter();
	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    out.print(jsonResponse);
	    out.flush();
	}
	}


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
               
    	int id = Integer.parseInt(request.getParameter("id"));
        String artistName = request.getParameter("artistName");
        String albumTitle = request.getParameter("albumTitle");
        String trackTitle = request.getParameter("trackTitle");
        int trackNumber = Integer.parseInt(request.getParameter("trackNumber"));
        int year = Integer.parseInt(request.getParameter("year"));
        int number_of_reviews = Integer.parseInt(request.getParameter("number_of_reviews"));
        int sold_copies = Integer.parseInt(request.getParameter("sold_copies"));
        
        Audio audio = new Audio(id, artistName, trackTitle, albumTitle, trackNumber, year, number_of_reviews, sold_copies);
        
        audioDB.put(id, audio);
        response.setStatus(200);
    	
    	response.getOutputStream().println("POST RESPONSE: Audio " + audio + " is added to the database.");
    }
}
