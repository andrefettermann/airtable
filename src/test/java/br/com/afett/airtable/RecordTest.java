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

public class RecordTest {
	
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
			assertEquals(49, records.size());
		} catch (BaseException e) {
			fail();
		}
	}
}
