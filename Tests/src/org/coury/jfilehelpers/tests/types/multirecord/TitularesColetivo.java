/*
 * TitularesColetivo.java
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
public class TitularesColetivo implements Serializable{

	/**
	 * 
	 */
	@FieldIgnored
	private static final long serialVersionUID = -1942651478066826935L;
	@FieldFixedLength(8)
	private String campocontrole;
	@FieldFixedLength(10)
	private String codecadcoletivo;
	@FieldFixedLength(10)
	private String SISRCcoleivo;
	@FieldFixedLength(13)
	private String codecadtitular;
	@FieldFixedLength(15)
	private String codSISRCtitular;
	@FieldFixedLength(45)
	private String nometitular;
	@FieldFixedLength(1)
	private String tipopessoa;
	@FieldFixedLength(11)
	private String cpftitular;
	@FieldFixedLength(14)
	private String cnpjtitular;
	@FieldFixedLength(11)
	private String codcaetitular;
	@FieldFixedLength(1)
	private String titularEnacional;
	@FieldFixedLength(8)
	private String dataentrada;
	@FieldFixedLength(8)
	private String datasaida;
	
	
	
	
	
	
	
	
	
	
	public TitularesColetivo() {
	
	
	}
	
	
	public String getCampocontrole() {
		return campocontrole;
	}
	public void setCampocontrole(String campocontrole) {
		this.campocontrole = campocontrole;
	}
	public String getCodecadcoletivo() {
		return codecadcoletivo;
	}
	public void setCodecadcoletivo(String codecadcoletivo) {
		this.codecadcoletivo = codecadcoletivo;
	}
	public String getSISRCcoleivo() {
		return SISRCcoleivo;
	}
	public void setSISRCcoleivo(String sISRCcoleivo) {
		SISRCcoleivo = sISRCcoleivo;
	}
	public String getCodecadtitular() {
		return codecadtitular;
	}
	public void setCodecadtitular(String codecadtitular) {
		this.codecadtitular = codecadtitular;
	}
	public String getCodSISRCtitular() {
		return codSISRCtitular;
	}
	public void setCodSISRCtitular(String codSISRCtitular) {
		this.codSISRCtitular = codSISRCtitular;
	}
	public String getNometitular() {
		return nometitular;
	}
	public void setNometitular(String nometitular) {
		this.nometitular = nometitular;
	}
	public String getTipopessoa() {
		return tipopessoa;
	}
	public void setTipopessoa(String tipopessoa) {
		this.tipopessoa = tipopessoa;
	}
	public String getCpftitular() {
		return cpftitular;
	}
	public void setCpftitular(String cpftitular) {
		this.cpftitular = cpftitular;
	}
	public String getCnpjtitular() {
		return cnpjtitular;
	}
	public void setCnpjtitular(String cnpjtitular) {
		this.cnpjtitular = cnpjtitular;
	}
	public String getCodcaetitular() {
		return codcaetitular;
	}
	public void setCodcaetitular(String codcaetitular) {
		this.codcaetitular = codcaetitular;
	}
	public String getTitularEnacional() {
		return titularEnacional;
	}
	public void setTitularEnacional(String titularEnacional) {
		this.titularEnacional = titularEnacional;
	}
	public String getDataentrada() {
		return dataentrada;
	}
	public void setDataentrada(String dataentrada) {
		this.dataentrada = dataentrada;
	}
	public String getDatasaida() {
		return datasaida;
	}
	public void setDatasaida(String datasaida) {
		this.datasaida = datasaida;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
