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

/**
 * DAO Factory Implementation.
 *
 * @author Andre Fettermann <a href="mailto:andre.fettermann@gmail.com">
 * andre.fettermann@gmail.com</a>
 * <br/>
 * published under the terms and conditions of the GPL License, for details see 
 * file gpl-3.0.txt in the distribution package of this software
 */
public final class DAOFactoryImpl implements DAOFactory {
	
  /**
   * @label singleton
   */
  private static DAOFactoryImpl instance;

  static{
    instance = new DAOFactoryImpl();
  }

  /**
   * @return DAOFactoryImpl class instance.
   */
  	public static DAOFactoryImpl getInstance(){
    	return instance;
  	}

	public AirTableDAO createAirTableDAO() {
		return AirTableDAOImpl.getInstance();
	}
}
