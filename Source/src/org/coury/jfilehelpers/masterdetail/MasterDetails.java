/*
 * MasterDetails.java
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
package org.coury.jfilehelpers.masterdetail;

import java.util.ArrayList;
import java.util.List;

public class MasterDetails<MT, DT> {
	private List<DT> details;
	private MT master;
	
	public MasterDetails() {
		details = new ArrayList<DT>();
	}
	
	public MasterDetails(MT master, List<DT> details) {
		this.master = master;
		this.details = details;
	}

	public List<DT> getDetails() {
		return details;
	}

	public void addDetails(List<DT> details) { 
		if (this.details == null) {
			this.details = new ArrayList<DT>();
		}
		this.details.addAll(details);
	}
	
	public void setDetails(List<DT> details) {
		this.details = details;
	}

	public MT getMaster() {
		return master;
	}

	public void setMaster(MT master) {
		this.master = master;
	}
}
