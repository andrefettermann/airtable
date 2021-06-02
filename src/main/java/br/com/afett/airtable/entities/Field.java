/*
  	Field.java
  
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

/**
 * AirTable table record field.
 * 
 * @author Andre Fettermann <a href="mailto:andre.fettermann@gmail.com">
 * andre.fettermann@gmail.com</a>
 * <br/>
 * published under the terms and conditions of the GPL License, for details see 
 * file gpl-3.0.txt in the distribution package of this software
 */
public class Field {
	
	/** Field name. */
	private String name;
	
	/** Field values. */
	private List<String> values = new ArrayList<>();
	
	/** Field value. */
	private String value;

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
	 * @return the values
	 */
	public final List<String> getValues() {
		return this.values;
	}
	/**
	 * @param values the values to set
	 */
	public final void setValues(final List<String> values) {
		this.values = values;
	}
	/**
	 * @param value the value to add.
	 */
	public final void addValue(final String value) {
		this.values.add(value);
	}

	/**
	 * @return the value.
	 */
	public final String getValue() {
		return this.value;
	}
	/**
	 * @param value the value.
	 */
	public final void setValue(final String value) {
		this.value = value;
		addValue(this.value);
	}

	@Override
	public String toString() {
		return "Field [name=" + name + ", values=" + values + "]";
	}
	
}
