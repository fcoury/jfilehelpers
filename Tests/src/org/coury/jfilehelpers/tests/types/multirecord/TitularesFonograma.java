/*
 * TitularesFonograma.java
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
public class TitularesFonograma implements Serializable{
	
	/**
	 * 
	 */
	@FieldIgnored
	private static final long serialVersionUID = 6389367497486306435L;
	@FieldFixedLength(8)
	private String campocontrole;
	@FieldFixedLength(9)
	private String codigoecadfonograma;
	@FieldFixedLength(15)
	private String codSISRCfonograma;
	@FieldFixedLength(13)
	private String codecadtitular;
	@FieldFixedLength(15)
	private String codSISRCtitular;
	@FieldFixedLength(45)
	private String nometitular;
	@FieldFixedLength(1)
	private String tipopessoatitular;
	@FieldFixedLength(11)
	private String cpftitular;
	@FieldFixedLength(14)
	private String cnpjtitular;
	@FieldFixedLength(11)
	private String codigocaetitular;
	@FieldFixedLength(2)
	private String codcategoriatitular;
	@FieldFixedLength(2)
	private String codsubcategoria;
	@FieldFixedLength(1)
	private String interpreteprincipal;
	@FieldFixedLength(10)
	private String percentalparticipacaotitular;
	@FieldFixedLength(1)
	private String titularEnacional;
	@FieldFixedLength(22)
	private String siglasociedade;
	@FieldFixedLength(45)
	private String nomepseudonimo;
	@FieldFixedLength(10)
	private String codigoecadcoletivo;
	@FieldFixedLength(10)
	private String codSISRCcoletivo;
	@FieldFixedLength(8)
	private String datainiciocontratolicenciamento;
	@FieldFixedLength(8)
	private String datafimcontratolicenciamento;
	@FieldFixedLength(1)
	private String percentualecalculadoautomaticamente;
	
	
	
	
	
	
	
	public TitularesFonograma() {
	

	}
	
	
	
	
	public String getCampocontrole() {
		return campocontrole;
	}
	public void setCampocontrole(String campocontrole) {
		this.campocontrole = campocontrole;
	}
	public String getCodigoecadfonograma() {
		return codigoecadfonograma;
	}
	public void setCodigoecadfonograma(String codigoecadfonograma) {
		this.codigoecadfonograma = codigoecadfonograma;
	}
	public String getCodSISRCfonograma() {
		return codSISRCfonograma;
	}
	public void setCodSISRCfonograma(String codSISRCfonograma) {
		this.codSISRCfonograma = codSISRCfonograma;
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
	public String getTipopessoatitular() {
		return tipopessoatitular;
	}
	public void setTipopessoatitular(String tipopessoatitular) {
		this.tipopessoatitular = tipopessoatitular;
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
	public String getCodigocaetitular() {
		return codigocaetitular;
	}
	public void setCodigocaetitular(String codigocaetitular) {
		this.codigocaetitular = codigocaetitular;
	}
	public String getCodcategoriatitular() {
		return codcategoriatitular;
	}
	public void setCodcategoriatitular(String codcategoriatitular) {
		this.codcategoriatitular = codcategoriatitular;
	}
	public String getCodsubcategoria() {
		return codsubcategoria;
	}
	public void setCodsubcategoria(String codsubcategoria) {
		this.codsubcategoria = codsubcategoria;
	}
	public String getInterpreteprincipal() {
		return interpreteprincipal;
	}
	public void setInterpreteprincipal(String interpreteprincipal) {
		this.interpreteprincipal = interpreteprincipal;
	}
	public String getPercentalparticipacaotitular() {
		return percentalparticipacaotitular;
	}
	public void setPercentalparticipacaotitular(String percentalparticipacaotitular) {
		this.percentalparticipacaotitular = percentalparticipacaotitular;
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
	public String getNomepseudonimo() {
		return nomepseudonimo;
	}
	public void setNomepseudonimo(String nomepseudonimo) {
		this.nomepseudonimo = nomepseudonimo;
	}
	public String getCodigoecadcoletivo() {
		return codigoecadcoletivo;
	}
	public void setCodigoecadcoletivo(String codigoecadcoletivo) {
		this.codigoecadcoletivo = codigoecadcoletivo;
	}
	public String getCodSISRCcoletivo() {
		return codSISRCcoletivo;
	}
	public void setCodSISRCcoletivo(String codSISRCcoletivo) {
		this.codSISRCcoletivo = codSISRCcoletivo;
	}
	public String getDatainiciocontratolicenciamento() {
		return datainiciocontratolicenciamento;
	}
	public void setDatainiciocontratolicenciamento(String datainiciocontratolicenciamento) {
		this.datainiciocontratolicenciamento = datainiciocontratolicenciamento;
	}
	public String getDatafimcontratolicenciamento() {
		return datafimcontratolicenciamento;
	}
	public void setDatafimcontratolicenciamento(String datafimcontratolicenciamento) {
		this.datafimcontratolicenciamento = datafimcontratolicenciamento;
	}
	public String getPercentualecalculadoautomaticamente() {
		return percentualecalculadoautomaticamente;
	}
	public void setPercentualecalculadoautomaticamente(String percentualecalculadoautomaticamente) {
		this.percentualecalculadoautomaticamente = percentualecalculadoautomaticamente;
	}




	@Override
	public String toString() {
		return "TitularesFonograma [campocontrole=" + campocontrole + ", codigoecadfonograma=" + codigoecadfonograma
				+ ", codSISRCfonograma=" + codSISRCfonograma + ", codecadtitular=" + codecadtitular
				+ ", codSISRCtitular=" + codSISRCtitular + ", nometitular=" + nometitular + ", tipopessoatitular="
				+ tipopessoatitular + ", cpftitular=" + cpftitular + ", cnpjtitular=" + cnpjtitular
				+ ", codigocaetitular=" + codigocaetitular + ", codcategoriatitular=" + codcategoriatitular
				+ ", codsubcategoria=" + codsubcategoria + ", interpreteprincipal=" + interpreteprincipal
				+ ", percentalparticipacaotitular=" + percentalparticipacaotitular + ", titularEnacional="
				+ titularEnacional + ", siglasociedade=" + siglasociedade + ", nomepseudonimo=" + nomepseudonimo
				+ ", codigoecadcoletivo=" + codigoecadcoletivo + ", codSISRCcoletivo=" + codSISRCcoletivo
				+ ", datainiciocontratolicenciamento=" + datainiciocontratolicenciamento
				+ ", datafimcontratolicenciamento=" + datafimcontratolicenciamento
				+ ", percentualecalculadoautomaticamente=" + percentualecalculadoautomaticamente + "]";
	}
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
