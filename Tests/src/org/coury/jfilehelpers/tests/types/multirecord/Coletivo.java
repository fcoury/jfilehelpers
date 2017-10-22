/*
 * Coletivo.java
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
public class Coletivo implements Serializable{

	/**
	 * 
	 */
	@FieldIgnored
	private static final long serialVersionUID = -4083307532555075193L;
	@FieldFixedLength(8)
	private String campocontrole;
	@FieldFixedLength(10)
	private String codecadcoletivo;
	@FieldFixedLength(10)
	private String codSISRCcoletivo;
	@FieldFixedLength(2)
	private String siglapais;
	@FieldFixedLength(10)
	private String codigotipocoletivo;
	@FieldFixedLength(60)
	private String nomecoletivo;
	@FieldFixedLength(1)
	private String enacional;
	@FieldFixedLength(11)
	private String codigocae;
	@FieldFixedLength(8)
	private String dataatualizacao;
	@FieldFixedLength(60)
	private String nomepseudocoletivo;
	
	
	
	
	
	
	
	
	public Coletivo() {


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
	public String getCodSISRCcoletivo() {
		return codSISRCcoletivo;
	}
	public void setCodSISRCcoletivo(String codSISRCcoletivo) {
		this.codSISRCcoletivo = codSISRCcoletivo;
	}
	public String getSiglapais() {
		return siglapais;
	}
	public void setSiglapais(String siglapais) {
		this.siglapais = siglapais;
	}
	public String getCodigotipocoletivo() {
		return codigotipocoletivo;
	}
	public void setCodigotipocoletivo(String codigotipocoletivo) {
		this.codigotipocoletivo = codigotipocoletivo;
	}
	public String getNomecoletivo() {
		return nomecoletivo;
	}
	public void setNomecoletivo(String nomecoletivo) {
		this.nomecoletivo = nomecoletivo;
	}
	public String getEnacional() {
		return enacional;
	}
	public void setEnacional(String enacional) {
		this.enacional = enacional;
	}
	public String getCodigocae() {
		return codigocae;
	}
	public void setCodigocae(String codigocae) {
		this.codigocae = codigocae;
	}
	public String getDataatualizacao() {
		return dataatualizacao;
	}
	public void setDataatualizacao(String dataatualizacao) {
		this.dataatualizacao = dataatualizacao;
	}
	public String getNomepseudocoletivo() {
		return nomepseudocoletivo;
	}
	public void setNomepseudocoletivo(String nomepseudocoletivo) {
		this.nomepseudocoletivo = nomepseudocoletivo;
	}
	
	
	
	
	
}
