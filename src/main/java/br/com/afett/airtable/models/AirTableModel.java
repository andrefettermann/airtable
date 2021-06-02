/*
  	AirTableModel.java
  
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
package br.com.afett.airtable.models;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import br.com.afett.airtable.daos.AirTableDAO;
import br.com.afett.airtable.daos.DAOFactory;
import br.com.afett.airtable.daos.DAOFactoryImpl;
import br.com.afett.airtable.entities.AirTable;
import br.com.afett.airtable.entities.Base;
import br.com.afett.airtable.entities.Record;
import br.com.afett.airtable.exceptions.BaseException;

/**
 * AirTable Model.
 * 
 * @author Andre Fettermann <a href="mailto:andre.fettermann@gmail.com">
 * andre.fettermann@gmail.com</a>
 * <br/>
 * published under the terms and conditions of the GPL License, for details see 
 * file gpl-3.0.txt in the distribution package of this software
 */
public class AirTableModel {
	
	private static final String API_URL = "https://api.airtable.com/v0";
	private static final String PARAM_MAX_RECORDS = "maxRecords";
	private static final String PARAM_VIEW = "view";
	private static final String PARAM_FILTER_BY_FORMULA = "filterByFormula";
	private static final String PARAM_PAGE_SIZE = "pageSize";
	private static final String PARAM_SORT_FIELD = "sort{field}";
	private static final String PARAM_SORT_DIRECTION = "sort{direction}";
	private static final String PARAM_CELL_FORMAT = "cellFormat";
	private static final String PARAM_TIME_ZONE = "timeZone";
	private static final String PARAM_USER_LOCALE = "userLocale";
	private static final String FIELDS = "fields";
	private static final String RECORDS = "records";
	private static final String ID = "id";
	private static final String PARAMS_STRING = "%s=%s&";
	
	private static AirTableModel instance;
	static {
		instance = new AirTableModel();
	}
	public static AirTableModel getInstance() {
		return instance;
	}

	private DAOFactory daofactory;
	private AirTableDAO dao;
	
	private AirTable airTable;
	private Base base;
	
	public void setAirTable(AirTable airTable) {
		this.airTable = airTable;
	}
	
	public void setBase(Base base) {
		this.base = base;
	}
	
	private AirTableModel() {
		daofactory = DAOFactoryImpl.getInstance();
		dao = daofactory.createAirTableDAO();
	}
	
	/**
	 * GET request.
	 * 
	 * @param tableName the table name.
	 * @param params the GET params
	 * @return list of records.
	 * @throws BaseException executing the GET.
	 */
	public List<Record> get(String tableName, String params) 
			throws BaseException {
		List<Record> rekords = new ArrayList<>();
		
		String response;
		try {
			response = dao.getAsJSON(airTable.getKey()
					, buildURL(tableName, params));
			var jsonArray = new JSONObject(response).getJSONArray(RECORDS);
			for (var i = 0; i < jsonArray.length(); i++) {
				var c = jsonArray.getJSONObject(i);
				
				var rekord = new Record();
				rekord.setId(c.getString(ID));
				
				var fieldsObject = c.getJSONObject(FIELDS);
				var fieldsMap = fieldsObject.toMap();
				rekord.setFields(fieldsMap);
				/*
				for (Map.Entry<String, Object> entry : fieldsMap.entrySet()) {
			        var field = new Field();
			        field.setName(entry.getKey());
			        if (entry.getValue() instanceof ArrayList) {
			        	field.setValues((ArrayList<String>) entry.getValue());
			        } else {
			        	field.setValue(entry.getValue().toString());
			        }
			        rekord.addField(field);
			    }
			    */

				rekords.add(rekord);
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new BaseException(e.getMessage());
		}
		return rekords;
	}

	/**
	 * Build the URL.
	 * 
	 * @return the url.
	 * @throws UnsupportedEncodingException when encoding.
	 * @throws ParseException when parsing.
	 */
	private String buildURL(String tableName, String params) 
			throws UnsupportedEncodingException{
		var url = new StringBuilder(
				String.format("%s/%s/%s?", API_URL, base.getId(), tableName));

		if (!params.trim().isEmpty()) {
			var json = new JSONObject(params); 
			if (json.has(PARAM_MAX_RECORDS))  {
				url.append(String.format(
					PARAMS_STRING, PARAM_MAX_RECORDS
						, json.get(PARAM_MAX_RECORDS)
					));
			}

			if (json.has(PARAM_VIEW)) {
				url.append(String.format(PARAMS_STRING, PARAM_VIEW
					, URLEncoder.encode(
    				json.get(PARAM_VIEW).toString(), "UTF-8")
					.replace("+", "%20")));
			}
			
			if (json.has(PARAM_FILTER_BY_FORMULA)) {
				url.append(String.format(
						PARAMS_STRING, PARAM_FILTER_BY_FORMULA
						, URLEncoder.encode(
								json.get(PARAM_FILTER_BY_FORMULA).toString()
								, "UTF-8").replace("+", "%20")));
			}
			
			if (json.has(PARAM_PAGE_SIZE))  {
				url.append(String.format(
					PARAMS_STRING, PARAM_PAGE_SIZE, json.get(PARAM_PAGE_SIZE)
					));
			}

			if (json.has(PARAM_SORT_FIELD))  {
				url.append(String.format(
					PARAMS_STRING, PARAM_SORT_FIELD, json.get(PARAM_SORT_FIELD)
					));
			}

			if (json.has(PARAM_SORT_DIRECTION))  {
				url.append(String.format(
					PARAMS_STRING, PARAM_SORT_DIRECTION
					, json.get(PARAM_SORT_DIRECTION)
					));
			}

			if (json.has(PARAM_CELL_FORMAT))  {
				url.append(String.format(PARAMS_STRING
							, PARAM_CELL_FORMAT, json.get(PARAM_CELL_FORMAT)));
			}

			if (json.has(PARAM_TIME_ZONE))  {
				url.append(String.format(
					PARAMS_STRING, PARAM_TIME_ZONE, json.get(PARAM_TIME_ZONE)));
			}

			if (json.has(PARAM_USER_LOCALE))  {
				url.append(String.format(PARAMS_STRING
						, PARAM_USER_LOCALE, json.get(PARAM_USER_LOCALE)));
			}
		}
		
		return url.toString();
	}
}
