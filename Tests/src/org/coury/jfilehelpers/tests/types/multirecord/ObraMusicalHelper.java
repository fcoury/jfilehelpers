/*
 * ObraMusicalHelper.java
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

@FixedLengthRecord(fixedMode= FixedMode.AllowVariableLength)
public class ObraMusicalHelper implements Serializable{

	
	/**
	 * 
	 */
	@FieldIgnored
	private static final long serialVersionUID = 3954924249950397283L;
	@FieldFixedLength(8)
	private String campodecontrole;
	@FieldFixedLength(13)
	private String codecadobra;
	@FieldFixedLength(15)
	private String codsisrc;
	@FieldFixedLength(95)
	private String tituloprincipal;
	@FieldFixedLength(1)
	@FieldOptional
	private String enacional;
	@FieldFixedLength(1)
	@FieldOptional
	private String einstrumental;
	@FieldFixedLength(1)
	@FieldOptional
	private String edominiopublico;
	@FieldFixedLength(8)
	@FieldOptional
	private String dtregistroobra;
	@FieldOptional
	@FieldFixedLength(8)
	private String dtemissao;
	@FieldOptional
	@FieldFixedLength(8)
	private String dtcriacao;
	@FieldOptional
	@FieldFixedLength(11)
	private String codiswc;
	@FieldOptional
	@FieldFixedLength(6)
	private String duracao;
	@FieldOptional
	@FieldFixedLength(2)
	private String siglapais;
	@FieldOptional
	@FieldFixedLength(10)
	private String codgeneromusical;
	@FieldOptional
	@FieldFixedLength(1)
	private String temregravarsoval;
	@FieldOptional
	@FieldFixedLength(1)
	private String ehomonima;
	@FieldFixedLength(1)
	@FieldOptional
	private String eobraderivada;
	@FieldOptional
	@FieldFixedLength(13)
	private String codecadobraoriginal;
	@FieldOptional
	@FieldFixedLength(15)
	private String codsocietarioobraoriginal;
	@FieldOptional
	@FieldFixedLength(2)
	private String siglaidioma;
	@FieldOptional
	@FieldFixedLength(1)
	private String eobracomposta;
	@FieldFixedLength(3)
	@FieldOptional
	private String codtipoobracomposta;
	@FieldOptional
	@FieldFixedLength(22)
	private String sociedaderesponsavel;
	@FieldOptional
	@FieldFixedLength(22)
	private String sociedaderesponsavelcadastro;
	@FieldOptional
	@FieldFixedLength(2)
	private String situacaocadastral;
	@FieldOptional
	@FieldFixedLength(1)
	private String ebloqueada;
	@FieldOptional
	@FieldFixedLength(30)
	private String nomegenero;

	
	public ObraMusicalHelper() {
	
		
	}
	
	
	public String getCampodecontrole() {
		return campodecontrole;
	}
	public void setCampodecontrole(String campodecontrole) {
		this.campodecontrole = campodecontrole;
	}
	public String getCodecadobra() {
		return codecadobra;
	}
	public void setCodecadobra(String codecadobra) {
		this.codecadobra = codecadobra;
	}
	public String getTituloprincipal() {
		return tituloprincipal;
	}
	public void setTituloprincipal(String tituloprincipal) {
		this.tituloprincipal = tituloprincipal;
	}
	public String getEnacional() {
		return enacional;
	}
	public void setEnacional(String enacional) {
		this.enacional = enacional;
	}
	public String getEinstrumental() {
		return einstrumental;
	}
	public void setEinstrumental(String einstrumental) {
		this.einstrumental = einstrumental;
	}
	public String getEdominiopublico() {
		return edominiopublico;
	}
	public void setEdominiopublico(String edominiopublico) {
		this.edominiopublico = edominiopublico;
	}
	public String getDtregistroobra() {
		return dtregistroobra;
	}
	public void setDtregistroobra(String dtregistroobra) {
		this.dtregistroobra = dtregistroobra;
	}
	public String getDtemissao() {
		return dtemissao;
	}
	public void setDtemissao(String dtemissao) {
		this.dtemissao = dtemissao;
	}
	public String getDtcriacao() {
		return dtcriacao;
	}
	public void setDtcriacao(String dtcriacao) {
		this.dtcriacao = dtcriacao;
	}
	public String getCodiswc() {
		return codiswc;
	}
	public void setCodiswc(String codiswc) {
		this.codiswc = codiswc;
	}
	public String getDuracao() {
		return duracao;
	}
	public void setDuracao(String duracao) {
		this.duracao = duracao;
	}
	public String getSiglapais() {
		return siglapais;
	}
	public void setSiglapais(String siglapais) {
		this.siglapais = siglapais;
	}
	public String getCodgeneromusical() {
		return codgeneromusical;
	}
	public void setCodgeneromusical(String codgeneromusical) {
		this.codgeneromusical = codgeneromusical;
	}
	public String getTemregravarsoval() {
		return temregravarsoval;
	}
	public void setTemregravarsoval(String temregravarsoval) {
		this.temregravarsoval = temregravarsoval;
	}
	public String getEhomonima() {
		return ehomonima;
	}
	public void setEhomonima(String ehomonima) {
		this.ehomonima = ehomonima;
	}
	public String getEobraderivada() {
		return eobraderivada;
	}
	public void setEobraderivada(String eobraderivada) {
		this.eobraderivada = eobraderivada;
	}
	public String getCodecadobraoriginal() {
		return codecadobraoriginal;
	}
	public void setCodecadobraoriginal(String codecadobraoriginal) {
		this.codecadobraoriginal = codecadobraoriginal;
	}
	public String getCodsocietarioobraoriginal() {
		return codsocietarioobraoriginal;
	}
	public void setCodsocietarioobraoriginal(String codsocietarioobraoriginal) {
		this.codsocietarioobraoriginal = codsocietarioobraoriginal;
	}
	public String getSiglaidioma() {
		return siglaidioma;
	}
	public void setSiglaidioma(String siglaidioma) {
		this.siglaidioma = siglaidioma;
	}
	public String getEobracomposta() {
		return eobracomposta;
	}
	public void setEobracomposta(String eobracomposta) {
		this.eobracomposta = eobracomposta;
	}
	public String getCodtipoobracomposta() {
		return codtipoobracomposta;
	}
	public void setCodtipoobracomposta(String codtipoobracomposta) {
		this.codtipoobracomposta = codtipoobracomposta;
	}
	public String getSociedaderesponsavel() {
		return sociedaderesponsavel;
	}
	public void setSociedaderesponsavel(String sociedaderesponsavel) {
		this.sociedaderesponsavel = sociedaderesponsavel;
	}
	public String getSociedaderesponsavelcadastro() {
		return sociedaderesponsavelcadastro;
	}
	public void setSociedaderesponsavelcadastro(String sociedaderesponsavelcadastro) {
		this.sociedaderesponsavelcadastro = sociedaderesponsavelcadastro;
	}
	public String getSituacaocadastral() {
		return situacaocadastral;
	}
	public void setSituacaocadastral(String situacaocadastral) {
		this.situacaocadastral = situacaocadastral;
	}
	public String getEbloqueada() {
		return ebloqueada;
	}
	public void setEbloqueada(String ebloqueada) {
		this.ebloqueada = ebloqueada;
	}


	public String getCodsisrc() {
		return codsisrc;
	}


	public void setCodsisrc(String codsisrc) {
		this.codsisrc = codsisrc;
	}


	public String getNomegenero() {
		return nomegenero;
	}


	public void setNomegenero(String nomegenero) {
		this.nomegenero = nomegenero;
	}


	@Override
	public String toString() {
		return "ObraMusicalHelper [campodecontrole=" + campodecontrole + ", codecadobra=" + codecadobra + ", codsisrc="
				+ codsisrc + ", tituloprincipal=" + tituloprincipal + ", enacional=" + enacional + ", einstrumental="
				+ einstrumental + ", edominiopublico=" + edominiopublico + ", dtregistroobra=" + dtregistroobra
				+ ", dtemissao=" + dtemissao + ", dtcriacao=" + dtcriacao + ", codiswc=" + codiswc + ", duracao="
				+ duracao + ", siglapais=" + siglapais + ", codgeneromusical=" + codgeneromusical
				+ ", temregravarsoval=" + temregravarsoval + ", ehomonima=" + ehomonima + ", eobraderivada="
				+ eobraderivada + ", codecadobraoriginal=" + codecadobraoriginal + ", codsocietarioobraoriginal="
				+ codsocietarioobraoriginal + ", siglaidioma=" + siglaidioma + ", eobracomposta=" + eobracomposta
				+ ", codtipoobracomposta=" + codtipoobracomposta + ", sociedaderesponsavel=" + sociedaderesponsavel
				+ ", sociedaderesponsavelcadastro=" + sociedaderesponsavelcadastro + ", situacaocadastral="
				+ situacaocadastral + ", ebloqueada=" + ebloqueada + ", nomegenero=" + nomegenero + "]";
	}

	
	
	


}
