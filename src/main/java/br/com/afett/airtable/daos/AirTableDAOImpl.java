/*
  	DAOFactoryImpl.java
  
  	Copyright (c) 2021, Andre Fettermann,  andre.fettermann@gmail.com
  	All rights reserved.

	This file is part of SimpleAirTableAPI.

    SimpleAirTableAPI is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    SimpleAirTableAPI is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with SimpleAirTableAPI.  If not, see <http://www.gnu.org/licenses/>.
 */
package br.com.afett.airtable.daos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import javax.net.ssl.HttpsURLConnection;

/**
 * AirTable DAO interface implementation.
 * 
 * @author Andre Fettermann <a href="mailto:andre.fettermann@gmail.com">
 * andre.fettermann@gmail.com</a>
 * <br/>
 * published under the terms and conditions of the GPL License, for details see 
 * file gpl-3.0.txt in the distribution package of this software
 */
public class AirTableDAOImpl implements AirTableDAO {
	
	private static AirTableDAOImpl instance;

	static {
		instance = new AirTableDAOImpl();
	}

	protected static AirTableDAO getInstance() {
		return instance;
	}

	/** Construtor. */
	private AirTableDAOImpl() { }
	
	public String getAsJSON(String key, String getURL) throws IOException {
    	var response = new StringBuilder();
        var url = new URL(getURL);
        HttpsURLConnection urlConnection = 
        		(HttpsURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");
        urlConnection.setRequestProperty("Authorization", key);
               
        urlConnection.connect();
        
        var inputStream = urlConnection.getInputStream();
        var reader = new BufferedReader(
        		new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        String jsonLine;
        while((jsonLine = reader.readLine()) != null) {
            response.append(jsonLine);
            response.append("\n");
        }

        urlConnection.disconnect();   
        return response.toString();
	}
}
