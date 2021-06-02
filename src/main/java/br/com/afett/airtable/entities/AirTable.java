/*
  	AirTable.java

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

import br.com.afett.airtable.models.AirTableModel;

/**
 * AirTable. Utilitarios para acesso ao AirTable API.
 *
 * @author Andre Fettermann
 * <a href="mailto:andre.fettermann@gmail.com">andre.fettermann@gmail.com</a>
 * <br/>
 * published under the terms and conditions of the GPL License, for details see
 * file gpl-3.0.txt in the distribution package of this software
 */
public class AirTable {

    /* API Key. */
    private String key;
    
    private AirTableModel model;
        
    public String getKey() {
    	return this.key;
    }
    
    /**
     * Sets the base id to connect to.
     * @param id the base id.
     * @return the base entity.
     */
    public Base getBase(String id) {
    	return new Base(id);
    }
    
    /**
     * Constructor.
     * 
     * @param key the api key.
     */
    public  AirTable(String key) {
        this.key = String.format("Bearer key%s", key);
        model = AirTableModel.getInstance();
        model.setAirTable(this);
    }

}
