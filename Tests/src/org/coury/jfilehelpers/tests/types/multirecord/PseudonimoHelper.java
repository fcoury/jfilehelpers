/*
 * Pseudonimo.java
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
import org.coury.jfilehelpers.annotations.FieldOptional;
import org.coury.jfilehelpers.annotations.FixedLengthRecord;
import org.coury.jfilehelpers.enums.FixedMode;


@FixedLengthRecord(fixedMode=FixedMode.AllowLessChars)
public class PseudonimoHelper implements Serializable {
	
	/**
	 * 
	 */
	@FieldIgnored
	private static final long serialVersionUID = -7247193814165524103L;
	@FieldFixedLength(8)
	private String campocontrole;
	@FieldFixedLength(13)
	private String codecadtitular;
	@FieldFixedLength(15)
	private String codsisrctitular;
	@FieldFixedLength(2)
	@FieldOptional
	private String codcategoriatitular;
	@FieldFixedLength(2)
	@FieldOptional
	private String codqualitycod;
	@FieldFixedLength(11)
	@FieldOptional
	private String codcae;
	@FieldFixedLength(45)
	@FieldOptional
	private String pseudonimo;
	@FieldFixedLength(1)
	@FieldOptional
	private String epseudonimoprincipal;
	
	
	
	
	public PseudonimoHelper() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	public String getCampocontrole() {
		return campocontrole;
	}
	public void setCampocontrole(String campocontrole) {
		this.campocontrole = campocontrole;
	}
	public String getCodecadtitular() {
		return codecadtitular;
	}
	public void setCodecadtitular(String codecadtitular) {
		this.codecadtitular = codecadtitular;
	}
	public String getCodsisrctitular() {
		return codsisrctitular;
	}
	public void setCodsisrctitular(String codsisrctitular) {
		this.codsisrctitular = codsisrctitular;
	}
	public String getCodcategoriatitular() {
		return codcategoriatitular;
	}
	public void setCodcategoriatitular(String codcategoriatitular) {
		this.codcategoriatitular = codcategoriatitular;
	}
	public String getCodqualitycod() {
		return codqualitycod;
	}
	public void setCodqualitycod(String codqualitycod) {
		this.codqualitycod = codqualitycod;
	}
	public String getCodcae() {
		return codcae;
	}
	public void setCodcae(String codcae) {
		this.codcae = codcae;
	}
	public String getPseudonimo() {
		return pseudonimo;
	}
	public void setPseudonimo(String pseudonimo) {
		this.pseudonimo = pseudonimo;
	}
	public String getEpseudonimoprincipal() {
		return epseudonimoprincipal;
	}
	public void setEpseudonimoprincipal(String epseudonimoprincipal) {
		this.epseudonimoprincipal = epseudonimoprincipal;
	}




	@Override
	public String toString() {
		return "PseudonimoHelper [campocontrole=" + campocontrole + ", codecadtitular=" + codecadtitular
				+ ", codsisrctitular=" + codsisrctitular + ", codcategoriatitular=" + codcategoriatitular
				+ ", codqualitycod=" + codqualitycod + ", codcae=" + codcae + ", pseudonimo=" + pseudonimo
				+ ", epseudonimoprincipal=" + epseudonimoprincipal + "]";
	}
	
	
	
	
	
	
	
}
