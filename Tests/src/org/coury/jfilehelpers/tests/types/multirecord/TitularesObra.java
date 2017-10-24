/*
 * TitularesObra.java
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
public class TitularesObra implements Serializable{

	/**
	 * 
	 */
	@FieldIgnored
	private static final long serialVersionUID = -4087990075693879559L;
	@FieldFixedLength(8)
	private String campocontrole;
	@FieldFixedLength(13)
	private String codecadobra;
	@FieldFixedLength(15)
	private String codSISRCobra;
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
	@FieldFixedLength(6)
	private String codtipodireito;
	@FieldFixedLength(2)
	private String codcategoriatitular;
	@FieldFixedLength(2)
	private String codsubcategoriatitular;
	@FieldFixedLength(5)
	private String percentualtitular;
	@FieldFixedLength(8)
	private String datainiciocontrato;
	@FieldFixedLength(8)
	private String datafimciocontrato;
	@FieldFixedLength(1)
	private String situacaotitularobra;
	@FieldFixedLength(1)
	private String titularEnacional;
	@FieldFixedLength(22)
	private String siglasociedade;
	@FieldFixedLength(45)
	private String nomedopseudonimo;
	
	
	
	public TitularesObra() {
	
	}
	
	
	
	public String getCampocontrole() {
		return campocontrole;
	}
	public void setCampocontrole(String campocontrole) {
		this.campocontrole = campocontrole;
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
	public String getCodtipodireito() {
		return codtipodireito;
	}
	public void setCodtipodireito(String codtipodireito) {
		this.codtipodireito = codtipodireito;
	}
	public String getCodcategoriatitular() {
		return codcategoriatitular;
	}
	public void setCodcategoriatitular(String codcategoriatitular) {
		this.codcategoriatitular = codcategoriatitular;
	}
	public String getCodsubcategoriatitular() {
		return codsubcategoriatitular;
	}
	public void setCodsubcategoriatitular(String codsubcategoriatitular) {
		this.codsubcategoriatitular = codsubcategoriatitular;
	}
	public String getPercentualtitular() {
		return percentualtitular;
	}
	public void setPercentualtitular(String percentualtitular) {
		this.percentualtitular = percentualtitular;
	}
	public String getDatainiciocontrato() {
		return datainiciocontrato;
	}
	public void setDatainiciocontrato(String datainiciocontrato) {
		this.datainiciocontrato = datainiciocontrato;
	}
	public String getDatafimciocontrato() {
		return datafimciocontrato;
	}
	public void setDatafimciocontrato(String datafimciocontrato) {
		this.datafimciocontrato = datafimciocontrato;
	}
	public String getSituacaotitularobra() {
		return situacaotitularobra;
	}
	public void setSituacaotitularobra(String situacaotitularobra) {
		this.situacaotitularobra = situacaotitularobra;
	}
	public String getTitularEnacional() {
		return titularEnacional;
	}
	public void setTitularEnacional(String titularEnacional) {
		this.titularEnacional = titularEnacional;
	}
	public String getSiglasociedade() {
		return siglasociedade;
	}
	public void setSiglasociedade(String siglasociedade) {
		this.siglasociedade = siglasociedade;
	}
	public String getNomedopseudonimo() {
		return nomedopseudonimo;
	}
	public void setNomedopseudonimo(String nomedopseudonimo) {
		this.nomedopseudonimo = nomedopseudonimo;
	}



	@Override
	public String toString() {
		return "TitularesObra [campocontrole=" + campocontrole + ", codecadobra=" + codecadobra + ", codSISRCobra="
				+ codSISRCobra + ", codecadtitular=" + codecadtitular + ", codSISRCtitular=" + codSISRCtitular
				+ ", nometitular=" + nometitular + ", tipopessoa=" + tipopessoa + ", cpftitular=" + cpftitular
				+ ", cnpjtitular=" + cnpjtitular + ", codcaetitular=" + codcaetitular + ", codtipodireito="
				+ codtipodireito + ", codcategoriatitular=" + codcategoriatitular + ", codsubcategoriatitular="
				+ codsubcategoriatitular + ", percentualtitular=" + percentualtitular + ", datainiciocontrato="
				+ datainiciocontrato + ", datafimciocontrato=" + datafimciocontrato + ", situacaotitularobra="
				+ situacaotitularobra + ", titularEnacional=" + titularEnacional + ", siglasociedade=" + siglasociedade
				+ ", nomedopseudonimo=" + nomedopseudonimo + "]";
	}
	
	
	
	
	
	
	
	
}
