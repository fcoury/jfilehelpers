/*
 * MasterDetailMultiRecordFluent.java
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
package org.coury.jfilehelpers.masterdetailmultirecord;

import java.util.Map;

import org.coury.jfilehelpers.masterdetail.RecordActionSelector;

public interface MasterDetailMultiRecordFluent {

	public <T> MasterDetailMultiRecordFluent addHeaderFile(Class<T> clazz, RecordActionSelector action);
	public <T> MasterDetailMultiRecordFluent addHeaderTransaction(Class<T> clazz,RecordActionSelector action);
	public <T> MasterDetailMultiRecordFluent addMaster(Class<T> clazz,RecordActionSelector action);
	public <T> MasterDetailMultiRecordFluent addDetail(Class<T> clazz,RecordActionSelector action);
	public <T> MasterDetailMultiRecordFluent addTraillerTransaction(Class<T> clazz,RecordActionSelector action);
	public <T> MasterDetailMultiRecordFluent addTraillerFile(Class<T> clazz,RecordActionSelector action);
	public Map<Class<?>, RecordActionSelector> getMapper();
	public void setMapper(Map<Class<?>, RecordActionSelector> mapper);
	
	
}
