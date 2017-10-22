/*
 * ColetivosFonograma.java
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
public class ColetivosFonograma implements Serializable{

	/**
	 * 
	 */
	@FieldIgnored
	private static final long serialVersionUID = 3279771949504420936L;
	@FieldFixedLength(8)
	private String campocontrole;
	@FieldFixedLength(9)
	private String codecadfonograma;
	@FieldFixedLength(15)
	private String codSISRCfonograma;
	@FieldFixedLength(10)
	private String codecadcoletivo;
	@FieldFixedLength(10)
	private String codSISRCcoletivo;
	
	
	
	public ColetivosFonograma() {
	
	}
	
	
	
	public String getCampocontrole() {
		return campocontrole;
	}
	public void setCampocontrole(String campocontrole) {
		this.campocontrole = campocontrole;
	}
	public String getCodecadfonograma() {
		return codecadfonograma;
	}
	public void setCodecadfonograma(String codecadfonograma) {
		this.codecadfonograma = codecadfonograma;
	}
	public String getCodSISRCfonograma() {
		return codSISRCfonograma;
	}
	public void setCodSISRCfonograma(String codSISRCfonograma) {
		this.codSISRCfonograma = codSISRCfonograma;
	}
	public String getCodecadcoletivo() {
		return codecadcoletivo;
	}
	public void setCodecadcoletivo(String codecadcoletivo) {
		this.codecadcoletivo = codecadcoletivo;
	}
	public String getCodSISRCcoletivo() {
		return codSISRCcoletivo;
	}
	public void setCodSISRCcoletivo(String codSISRCcoletivo) {
		this.codSISRCcoletivo = codSISRCcoletivo;
	}
	
	
	
	
	
	
	
	
}
