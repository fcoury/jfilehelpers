/*
 * Poutpourrit.java
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
package org.coury.jfilehelpers.tests.types.multirecord;

import java.io.Serializable;

import org.coury.jfilehelpers.annotations.FieldFixedLength;
import org.coury.jfilehelpers.annotations.FieldIgnored;
import org.coury.jfilehelpers.annotations.FixedLengthRecord;
import org.coury.jfilehelpers.enums.FixedMode;

@FixedLengthRecord(fixedMode=FixedMode.AllowLessChars)
public class Poutpourrit implements Serializable{

	
	
	/**
	 * 
	 */
	@FieldIgnored
	private static final long serialVersionUID = -911966238606528897L;
	@FieldFixedLength(8)
	private String campocontrole;
	@FieldFixedLength(9)
	private String codecadpoutpourrit;
	@FieldFixedLength(15)
	private String codSISRCPoutpourrit;
	@FieldFixedLength(90)
	private String titulopoutpourrit;
	@FieldFixedLength(6)
	private String duracaototal;
	@FieldFixedLength(11)
	private String codiswc;
	public String getCampocontrole() {
		return campocontrole;
	}
	public void setCampocontrole(String campocontrole) {
		this.campocontrole = campocontrole;
	}
	public String getCodecadpoutpourrit() {
		return codecadpoutpourrit;
	}
	public void setCodecadpoutpourrit(String codecadpoutpourrit) {
		this.codecadpoutpourrit = codecadpoutpourrit;
	}
	public String getCodSISRCPoutpourrit() {
		return codSISRCPoutpourrit;
	}
	public void setCodSISRCPoutpourrit(String codSISRCPoutpourrit) {
		this.codSISRCPoutpourrit = codSISRCPoutpourrit;
	}
	public String getTitulopoutpourrit() {
		return titulopoutpourrit;
	}
	public void setTitulopoutpourrit(String titulopoutpourrit) {
		this.titulopoutpourrit = titulopoutpourrit;
	}
	public String getDuracaototal() {
		return duracaototal;
	}
	public void setDuracaototal(String duracaototal) {
		this.duracaototal = duracaototal;
	}
	public String getCodiswc() {
		return codiswc;
	}
	public void setCodiswc(String codiswc) {
		this.codiswc = codiswc;
	}
	
	
	
	
	
	
	
}
