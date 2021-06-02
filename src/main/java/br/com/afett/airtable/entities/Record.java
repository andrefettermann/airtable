/*
  	Record.java
  
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

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * AirTable table record.
 * 
 * @author Andre Fettermann <a href="mailto:andre.fettermann@gmail.com">
 * andre.fettermann@gmail.com</a>
 * <br/>
 * published under the terms and conditions of the GPL License, for details see 
 * file gpl-3.0.txt in the distribution package of this software
 */
public class Record {

	/** Id. */
	private String id;
	
	/** Created date and time. Not used. */
	private Date createdTime;
	
	/** Fields for the record. */
	private Map<String, Object> fields = new HashMap<>();

	/**
	 * @return the id.
	 */
	public final String getId() {
		return this.id;
	}

	/**
	 * @param id the id to set.
	 */
	public final void setId(final String id) {
		this.id = id;
	}

	/**
	 * @return createdDate the createdDate.
	 */
	public final Date getCreatedDate() {
		return this.createdTime;
	}
	
	/**
	 * @param createdTime the createdTime to set.
	 */
	public final void setCreatedTime(final Date createdTime) {
		this.createdTime = createdTime;
	}
	
	/**
	 * @return the fields.
	 */
	public final Map<String, Object> getFields() {
		return this.fields;
	}
	
	/**
	 * @param fields the fields to set
	 */
	public final void setFields(final Map<String, Object> fields) {
		this.fields = fields;
	}

	@Override
	public String toString() {
		return "Record [id=" + id + ", fields=" + fields + "]";
	}
	
}
