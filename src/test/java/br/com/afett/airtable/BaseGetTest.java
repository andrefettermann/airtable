package br.com.afett.airtable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import br.com.afett.airtable.entities.AirTable;
import br.com.afett.airtable.entities.Base;
import br.com.afett.airtable.entities.Record;
import br.com.afett.airtable.exceptions.BaseException;

public class BaseGetTest {
	
	AirTable airTable;
	
	@Before
	public void setUp() {
		airTable = new AirTable("api key");
	}

	/**
	 * <b>Dado</b> que desejo listar todos os registros da tabela.<br/>
	 * <b>Quando</b> for executada consulta.<br/>
	 * <b>Entao</b> deveriam ser trazidos todos os registros da tabela.<br/> 
	 */
	//@Test
	public void shouldListAllTest() {
		Base base = airTable.getBase("base id");
		
		try {
			List<Record> records = base.get("table name", "");
			assertEquals(5, records.size());
		} catch (BaseException e) {
			fail();
		}
	}
	
	/**
	 * <b>Dado</b> que foi informada uma view inexistente.<br/>
	 * <b>Quando</b> for executada consulta.<br/>
	 * <b>Entao</b> deveria ser emitida uma excecao.<br/>
	 * <b>E</b> nao deveriam ser trazidos registros.<br/> 
	 */
	//@Test(expected = BaseException.class)
	public void shouldNotListFromAUnknownViewTest() throws BaseException {
		JSONObject params = new JSONObject();
		params.put("maxRecords", 3);
		params.put("view", "Grid view1");
		
		Base base = airTable.getBase("base id");
		base.get("Features", params.toString());
	}
	
	/**
	 * <b>Dado</b> que foi informada view existente.<br/>
	 * <b>Quando</b> for executada consulta.<br/>
	 * <b>Entao</b> deveriam ser trazidos todos os registros da view.<br/> 
	 */
	//@Test
	public void shouldListAllFromAViewTest() {
		JSONObject params = new JSONObject();
		params.put("view", "Grid view");
		try {
			Base base = airTable.getBase("base id");
			List<Record> rekords = base.get("Table name", params.toString());
			
			assertEquals(5, rekords.size());
		} catch (BaseException e) {
			fail();
		}
	}

	/**
	 * <b>Dado</b> que foi informada view existente.<br/>
	 * <b>E</b> que foi informado o numero limite de registros a serem trazidos.<br/>
	 * <b>Quando</b> for executada consulta.<br/>
	 * <b>Entao</b> deveriam ser trazidos o numero de registros informados.<br/> 
	 */
	//@Test
	public void shouldListNumberOfRecordsFromAViewTest() {
		JSONObject params = new JSONObject();
		params.put("maxRecords", 3);
		params.put("view", "view name");
		try {
			Base base = airTable.getBase("base id");
			List<Record> rekords = base.get("table name", params.toString());
			assertEquals(3, rekords.size());
		} catch (BaseException e) {
			fail();
		}
	}

	/**
	 * <b>Dado</b> que foi informada view existente.<br/>
	 * <b>E</b> que foi informado um filtro por formula.<br/>
	 * <b>Quando</b> for executada consulta.<br/>
	 * <b>Entao</b> deveriam ser trazidos os registros que atendam o filtro.<br/> 
	 */
	//@Test
	public void shouldListFilterByAFormlaTest() {
		JSONObject params = new JSONObject();
		params.put("filterByFormula", "{filed name}='value'");
		try {
			Base base = airTable.getBase("base id");
			List<Record> rekords = base.get("table name", params.toString());
			assertEquals(5, rekords.size());
		} catch (BaseException e) {
			fail();
		}
	}

	/**
	 * <b>Dado</b> que foi informada view existente.<br/>
	 * <b>E</b> que foi informado uma ordenacao.<br/>
	 * <b>Quando</b> for executada consulta.<br/>
	 * <b>Entao</b> deveriam ser trazidos os registros.<br/>
	 * <b>E</b> deveriam ser trazidos odenados como desejado.<br/> 
	 */
	//@Test
	public void shouldListSortedTest() {
		JSONObject params = new JSONObject();
		params.put("sort{field}", "field name");
		params.put("sort{direction}", "desc");
		
		try {
			Base base = airTable.getBase("base id");
			List<Record> rekords = base.get("table name", params.toString());

			assertEquals(5, rekords.size());
		} catch (BaseException e) {
			fail();
		}
	}

	/**
	 * <b>Dado</b> que foi informada view existente.<br/>
	 * <b>E</b> que foi informado uma formatacao para a celula.<br/>
	 * <b>Quando</b> for executada consulta.<br/>
	 * <b>Entao</b> deveriam ser trazidos os registros.<br/>
	 * <b>E</b> deveriam ser trazidos formatados como informado.<br/> 
	 */
	//@Test
	public void shouldListCellFormattedTest() {
		String params = 
		 	"{\"cellFormat\":\"json\"}";
		
		try {
			Base base = airTable.getBase("base id");
			List<Record> rekords = base.get("table name", params.toString());

			assertEquals(5, rekords.size());
		} catch (BaseException e) {
			fail();
		}
	}
}
