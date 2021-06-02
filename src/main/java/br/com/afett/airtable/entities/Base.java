/*
  	Base.java
  
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
package br.com.afett.airtable.entities;

import java.util.ArrayList;
import java.util.List;

import br.com.afett.airtable.exceptions.BaseException;
import br.com.afett.airtable.models.AirTableModel;

/**
 * AirTable base.
 * 
 * @author Andre Fettermann <a href="mailto:andre.fettermann@gmail.com">
 * andre.fettermann@gmail.com</a>
 * <br/>
 * published under the terms and conditions of the GPL License, for details see 
 * file gpl-3.0.txt in the distribution package of this software
 */
public class Base {
	
	private String id;
	
	private String name;
	
	private List<String> tables = new ArrayList<>();
	
	protected Base(String id) {
		this.id = id;
	}

	/**
	 * @return the id.
	 */
	public final String getId() {
		return this.id;
	}

	/**
	 * @return the name.
	 */
	public final String getName() {
		return this.name;
	}
	/**
	 * @param name the name to set. 
	 */
	public final void setName(final String name) {
		this.name = name;
	}
	
	/**
	 * @return a list of table names.
	 */
	public final List<String> getTables() {
		return this.tables;
	}
	/**
	 * @param views a list of table names.
	 */
	public void setViews(final List<String> tables) {
		this.tables = tables;
	}
	/**
	 * @param a table name.
	 */
	public final void addTable(final String table) {
		this.tables.add(table);
	}
	
	/**
	 * Return all records for a table.
	 * 
	 * @param tableName the table name.
	 * @param params the GET request params.
	 * @return list of record for the table.
	 * @throws BaseException reading data.
	 */
	public final List<Record> get(String tableName, String params) 
			throws BaseException {
		var model = AirTableModel.getInstance();
		model.setBase(this);
		
		return model.get(tableName, params);
	}
}
