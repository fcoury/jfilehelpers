/*
 * MasterDetailMultiRecord.java
 *
 * Copyright (C) 2007 Felipe Gonçalves Coury <felipe.coury@gmail.com>
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package org.coury.jfilehelpers.tests.masterdetailmultirecord;



import java.util.List;
import java.util.Map;

import org.coury.jfilehelpers.masterdetail.RecordAction;
import org.coury.jfilehelpers.masterdetail.RecordActionSelector;
import org.coury.jfilehelpers.masterdetailmultirecord.MasterDetailMultiRecordEngine;
import org.coury.jfilehelpers.masterdetailmultirecord.MasterDetailMultiRecordFluentImplement;
import org.coury.jfilehelpers.tests.types.multirecord.Fonograma;
import org.coury.jfilehelpers.tests.types.multirecord.HeaderFonograma;
import org.coury.jfilehelpers.tests.types.multirecord.HeaderObra;
import org.coury.jfilehelpers.tests.types.multirecord.HeaderTitular;
import org.coury.jfilehelpers.tests.types.multirecord.InstrumentosFonograma;
import org.coury.jfilehelpers.tests.types.multirecord.LocalizacaoEDocumentacaoHelper;
import org.coury.jfilehelpers.tests.types.multirecord.ObraMusicalHelper;
import org.coury.jfilehelpers.tests.types.multirecord.PseudonimoHelper;
import org.coury.jfilehelpers.tests.types.multirecord.SubTitulo;
import org.coury.jfilehelpers.tests.types.multirecord.TitularHelper;
import org.coury.jfilehelpers.tests.types.multirecord.TitularesFonograma;
import org.coury.jfilehelpers.tests.types.multirecord.TitularesObra;
import org.coury.jfilehelpers.tests.types.multirecord.TraillerFonograma;
import org.coury.jfilehelpers.tests.types.multirecord.TraillerObra;
import org.coury.jfilehelpers.tests.types.multirecord.TraillerTitular;

import junit.framework.TestCase;

public class MasterDetailMultiRecordTest extends TestCase {

	
	
	public void testMasterDetailMultiRecord() throws Exception {

		MasterDetailMultiRecordFluentImplement fluent = new MasterDetailMultiRecordFluentImplement();
		//Titulares
		fluent
		.addHeaderTransaction(HeaderTitular.class, setSelector("0TIT", RecordAction.HeaderTransaction))
		.addMaster(TitularHelper.class, setSelector("TIT1", RecordAction.Master))
		.addDetail(LocalizacaoEDocumentacaoHelper.class, setSelector("TIT2", RecordAction.Detail))
		.addDetail(PseudonimoHelper.class, setSelector("TIT4", RecordAction.Detail))
		.addTraillerTransaction(TraillerTitular.class, setSelector("9TIT", RecordAction.TraillerTransaction))
		//Obra
		.addHeaderTransaction(HeaderObra.class, setSelector("0OBM", RecordAction.HeaderTransaction))
		.addMaster(ObraMusicalHelper.class, setSelector("OBM1", RecordAction.Master))
		.addDetail(TitularesObra.class, setSelector("OBM2", RecordAction.Detail))
		.addDetail(SubTitulo.class, setSelector("OBM3", RecordAction.Detail))
		.addTraillerTransaction(TraillerObra.class, setSelector("9OBM", RecordAction.TraillerTransaction))
		//Fonograma
		.addHeaderTransaction(HeaderFonograma.class, setSelector("0FON", RecordAction.HeaderTransaction))
		.addMaster(Fonograma.class, setSelector("FON1", RecordAction.Master))
		.addDetail(TitularesFonograma.class, setSelector("FON2", RecordAction.Detail))
		.addDetail(InstrumentosFonograma.class, setSelector("FON3", RecordAction.Detail))
		.addTraillerTransaction(TraillerFonograma.class, setSelector("9FON", RecordAction.TraillerTransaction));
		
		MasterDetailMultiRecordEngine engine = new MasterDetailMultiRecordEngine(fluent);
		Map<Object, List<?>> result = engine.readFile(System.getProperty("user.dir") + "/Resources/test/Good/teste_isrc.IMP");
		engine.writeFile(System.getProperty("user.dir") + "/Resources/test/Good/masterdetailmultirecord.txt", result, 1);
		
	}

	private RecordActionSelector setSelector(String token, RecordAction action) {
		return new RecordActionSelector() {
			@Override
			public RecordAction getRecordAction(String recordString) {
				if(recordString.contains(token))
				return action;
			return RecordAction.Skip;
			}
		};
	}
	
	
}
