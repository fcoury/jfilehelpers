/*
 * ObraPoutpourrit.java
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
public class ObraPoutpourrit implements Serializable{

	
	/**
	 * 
	 * 
	 */
	@FieldIgnored
	private static final long serialVersionUID = -6036703391196038534L;
	@FieldFixedLength(8)
	private String campocontrole;
	@FieldFixedLength(9)
	private String codecadpoutpourrit;
	@FieldFixedLength(15)
	private String codSISRCpoutpourrit;
	@FieldFixedLength(13)
	private String codecadobra;
	@FieldFixedLength(15)
	private String codSISRCobra;
	@FieldFixedLength(3)
	private String numeroexecucoes;
	@FieldFixedLength(6)
	private String duracao;
	
	
	public ObraPoutpourrit() {

	}
	
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
	public String getCodSISRCpoutpourrit() {
		return codSISRCpoutpourrit;
	}
	public void setCodSISRCpoutpourrit(String codSISRCpoutpourrit) {
		this.codSISRCpoutpourrit = codSISRCpoutpourrit;
	}
	public String getCodecadobra() {
		return codecadobra;
	}
	public void setCodecadobra(String codecadobra) {
		this.codecadobra = codecadobra;
	}
	public String getCodSISRCobra() {
		return codSISRCobra;
	}
	public void setCodSISRCobra(String codSISRCobra) {
		this.codSISRCobra = codSISRCobra;
	}
	public String getNumeroexecucoes() {
		return numeroexecucoes;
	}
	public void setNumeroexecucoes(String numeroexecucoes) {
		this.numeroexecucoes = numeroexecucoes;
	}
	public String getDuracao() {
		return duracao;
	}
	public void setDuracao(String duracao) {
		this.duracao = duracao;
	}

	
	
	
	
	
}

