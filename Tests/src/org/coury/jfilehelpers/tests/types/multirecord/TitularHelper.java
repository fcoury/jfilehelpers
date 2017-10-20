/*
 * TitularHelper.java
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

@FixedLengthRecord(fixedMode = FixedMode.AllowLessChars)
public class TitularHelper implements Serializable{

	/**
	 * 
	 */
	@FieldIgnored
	private static final long serialVersionUID = 2794738679598309411L;
	@FieldFixedLength(8)
	private String campodecontrole;
	@FieldFixedLength(13)
	private String codecad;
	@FieldFixedLength(15)
	private String codisrc;
	@FieldFixedLength(1)
	private String tipopessoa;
	@FieldFixedLength(45)
	private String nome;
	@FieldFixedLength(1)
	@FieldOptional
	private String enacional;
	@FieldFixedLength(8)
	private String dtnascimento;
	@FieldFixedLength(8)
	private String dtfalecimento;
	@FieldFixedLength(45)
	private String nomefantasia;
	@FieldFixedLength(45)
	private String profissao;
	@FieldFixedLength(45)
	private String nomepai;
	@FieldFixedLength(45)
	private String nomemae;
	@FieldFixedLength(2)
	private String estadocivil;
	@FieldFixedLength(1)
	private String sexo;
	@FieldFixedLength(2)
	private Integer qtdfilhos;
	@FieldFixedLength(45)
	private String nomeconjugue;
	@FieldFixedLength(2)
	@FieldOptional
	private String naturalidade;
	@FieldFixedLength(2)
	@FieldOptional
	private String nacionalidade;
	@FieldFixedLength(1)
	@FieldOptional
	private String temsucessor;
	@FieldFixedLength(2)
	@FieldOptional
	private String qualitycode;
	@FieldFixedLength(11)
	@FieldOptional
	private String codigocae;
	@FieldOptional
	@FieldFixedLength(3)
	private String siglaifpi;
	@FieldFixedLength(2)
	@FieldOptional
	private String radicalifpi;
	
	
	
	
	public TitularHelper() {

	}
	
	

	
	

	
	
	
	public String getCampodecontrole() {
		return campodecontrole;
	}

	public void setCampodecontrole(String campodecontrole) {
		this.campodecontrole = campodecontrole;
	}

	public String getCodecad() {
		return codecad;
	}

	public void setCodecad(String codecad) {
		this.codecad = codecad;
	}

	public String getCodisrc() {
		return codisrc;
	}

	public void setCodisrc(String codisrc) {
		this.codisrc = codisrc;
	}


	public String getEstadocivil() {
		return estadocivil;
	}

	public void setEstadocivil(String estadocivil) {
		this.estadocivil = estadocivil;
	}

	public String getTipopessoa() {
		return tipopessoa;
	}

	public void setTipopessoa(String tipopessoa) {
		this.tipopessoa = tipopessoa;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEnacional() {
		return enacional;
	}

	public void setEnacional(String enacional) {
		this.enacional = enacional;
	}

	public String getDtnascimento() {
		return dtnascimento;
	}

	public void setDtnascimento(String dtnascimento) {
		this.dtnascimento = dtnascimento;
	}

	public String getDtfalecimento() {
		return dtfalecimento;
	}

	public void setDtfalecimento(String dtfalecimento) {
		this.dtfalecimento = dtfalecimento;
	}

	public String getNomefantasia() {
		return nomefantasia;
	}

	public void setNomefantasia(String nomefantasia) {
		this.nomefantasia = nomefantasia;
	}

	public String getProfissao() {
		return profissao;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

	public String getNomepai() {
		return nomepai;
	}

	public void setNomepai(String nomepai) {
		this.nomepai = nomepai;
	}

	public String getNomemae() {
		return nomemae;
	}

	public void setNomemae(String nomemae) {
		this.nomemae = nomemae;
	}

	public String getestadocivil() {
		return estadocivil;
	}

	public void setestadocivil(String estadocivil) {
		this.estadocivil = estadocivil;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Integer getQtdfilhos() {
		return qtdfilhos;
	}

	public void setQtdfilhos(Integer qtdfilhos) {
		this.qtdfilhos = qtdfilhos;
	}

	public String getNomeconjugue() {
		return nomeconjugue;
	}

	public void setNomeconjugue(String nomeconjugue) {
		this.nomeconjugue = nomeconjugue;
	}

	public String getNaturalidade() {
		return naturalidade;
	}

	public void setNaturalidade(String naturalidade) {
		this.naturalidade = naturalidade;
	}

	public String getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	public String getTemsucessor() {
		return temsucessor;
	}

	public void setTemsucessor(String temsucessor) {
		this.temsucessor = temsucessor;
	}

	public String getQualitycode() {
		return qualitycode;
	}

	public void setQualitycode(String qualitycode) {
		this.qualitycode = qualitycode;
	}

	public String getCodigocae() {
		return codigocae;
	}

	public void setCodigocae(String codigocae) {
		this.codigocae = codigocae;
	}

	public String getSiglaifpi() {
		return siglaifpi;
	}

	public void setSiglaifpi(String siglaifpi) {
		this.siglaifpi = siglaifpi;
	}


	public String getRadicalifpi() {
		return radicalifpi;
	}

	public void setRadicalifpi(String radicalifpi) {
		this.radicalifpi = radicalifpi;
	}









	@Override
	public String toString() {
		return "TitularHelper [campodecontrole=" + campodecontrole + ", codecad=" + codecad + ", codisrc=" + codisrc
				+ ", tipopessoa=" + tipopessoa + ", nome=" + nome + ", enacional=" + enacional + ", dtnascimento="
				+ dtnascimento + ", dtfalecimento=" + dtfalecimento + ", nomefantasia=" + nomefantasia + ", profissao="
				+ profissao + ", nomepai=" + nomepai + ", nomemae=" + nomemae + ", estadocivil=" + estadocivil
				+ ", sexo=" + sexo + ", qtdfilhos=" + qtdfilhos + ", nomeconjugue=" + nomeconjugue + ", naturalidade="
				+ naturalidade + ", nacionalidade=" + nacionalidade + ", temsucessor=" + temsucessor + ", qualitycode="
				+ qualitycode + ", codigocae=" + codigocae + ", siglaifpi=" + siglaifpi + ", radicalifpi=" + radicalifpi
				+ "]";
	}




	
	
}
