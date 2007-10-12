/*
 * BeforeReadRecord.java
 *
 * Copyright (C) 2007 Felipe Gon�alves Coury <felipe.coury@gmail.com>
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

package org.coury.jfilehelpers.events;

import org.coury.jfilehelpers.engines.EngineBase;

/**
 * @author Robert Eccardt
 *
 * @param <T> record type
 */
public interface AfterWriteRecordHandler<T> {
	/**
	 * The name of this method is very misleading because the 
	 * record has not yet been written.  It gives the caller the
	 * opportunity to examine the proposed output and make any desired
	 * modifications.
	 */
	public void handleAfterWriteRecord(EngineBase<T> engine, AfterWriteRecordEventArgs<T> e);
}
