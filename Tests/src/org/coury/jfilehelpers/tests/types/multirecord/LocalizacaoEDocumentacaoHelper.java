/*
 * LocalizacaoEDocumentacao.java
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
public class LocalizacaoEDocumentacaoHelper implements Serializable {

	/**
	 * 
	 */
	@FieldIgnored
	private static final long serialVersionUID = 3182662020845288756L;
	@FieldFixedLength(8)
	private String campocontrole;
	@FieldFixedLength(13)
	private String codecadtitular;
	@FieldFixedLength(15)
	private String codsisrctitular;
	@FieldFixedLength(11)
	private String cpf;
	@FieldFixedLength(14)
	private String cnpj;
	@FieldFixedLength(7)
	private String orgaoemissor;
	@FieldFixedLength(8)
	@FieldOptional
	private String dataemissaorg;
	@FieldFixedLength(12)
	private String rg;
	@FieldFixedLength(2)
	@FieldOptional
	private String siglaufrg;
	@FieldFixedLength(3)
	@FieldOptional
	private String tipoendereco;
	@FieldFixedLength(3)
	@FieldOptional
	private String tipologradouro;
	@FieldFixedLength(65)
	@FieldOptional
	private String logradouro;
	@FieldFixedLength(5)
	@FieldOptional
	private String numero;
	@FieldFixedLength(15)
	@FieldOptional
	private String complemento;
	@FieldOptional
	@FieldFixedLength(8)
	private String cep;
	@FieldFixedLength(5)
	@FieldOptional
	private String codmunicipio;
	@FieldFixedLength(5)
	@FieldOptional
	private String codbairro;
	@FieldFixedLength(2)
	@FieldOptional
	private String siglauf;
	@FieldFixedLength(2)
	@FieldOptional
	private String siglapais;
	@FieldFixedLength(16)
	@FieldOptional
	private String telefone1;
	@FieldFixedLength(16)
	@FieldOptional
	private String telefone2;
	@FieldFixedLength(16)
	@FieldOptional
	private String fax;
	@FieldFixedLength(65)
	@FieldOptional
	private String referenciacontato;
	@FieldFixedLength(45)
	@FieldOptional
	private String email;
	@FieldFixedLength(45)
	@FieldOptional
	private String url;
	@FieldFixedLength(13)
	@FieldOptional
	private String codomb;
	@FieldFixedLength(1)
	@FieldOptional
	private String tipocpfcnpj;
	
	
	
	public LocalizacaoEDocumentacaoHelper() {


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
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getOrgaoemissor() {
		return orgaoemissor;
	}
	public void setOrgaoemissor(String orgaoemissor) {
		this.orgaoemissor = orgaoemissor;
	}
	public String getDataemissaorg() {
		return dataemissaorg;
	}
	public void setDataemissaorg(String dataemissaorg) {
		this.dataemissaorg = dataemissaorg;
	}
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	public String getSiglaufrg() {
		return siglaufrg;
	}
	public void setSiglaufrg(String siglaufrg) {
		this.siglaufrg = siglaufrg;
	}
	public String getTipoendereco() {
		return tipoendereco;
	}
	public void setTipoendereco(String tipoendereco) {
		this.tipoendereco = tipoendereco;
	}
	public String getTipologradouro() {
		return tipologradouro;
	}
	public void setTipologradouro(String tipologradouro) {
		this.tipologradouro = tipologradouro;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getCodmunicipio() {
		return codmunicipio;
	}
	public void setCodmunicipio(String codmunicipio) {
		this.codmunicipio = codmunicipio;
	}
	public String getCodbairro() {
		return codbairro;
	}
	public void setCodbairro(String codbairro) {
		this.codbairro = codbairro;
	}
	public String getSiglauf() {
		return siglauf;
	}
	public void setSiglauf(String siglauf) {
		this.siglauf = siglauf;
	}
	public String getSiglapais() {
		return siglapais;
	}
	public void setSiglapais(String siglapais) {
		this.siglapais = siglapais;
	}
	public String getTelefone1() {
		return telefone1;
	}
	public void setTelefone1(String telefone1) {
		this.telefone1 = telefone1;
	}
	public String getTelefone2() {
		return telefone2;
	}
	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getReferenciacontato() {
		return referenciacontato;
	}
	public void setReferenciacontato(String referenciacontato) {
		this.referenciacontato = referenciacontato;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getCodomb() {
		return codomb;
	}
	public void setCodomb(String codomb) {
		this.codomb = codomb;
	}
	public String getTipocpfcnpj() {
		return tipocpfcnpj;
	}
	public void setTipocpfcnpj(String tipocpfcnpj) {
		this.tipocpfcnpj = tipocpfcnpj;
	}


	@Override
	public String toString() {
		return "LocalizacaoEDocumentacao [campocontrole=" + campocontrole + ", codecadtitular=" + codecadtitular
				+ ", codsisrctitular=" + codsisrctitular + ", cpf=" + cpf + ", cnpj=" + cnpj + ", orgaoemissor="
				+ orgaoemissor + ", dataemissaorg=" + dataemissaorg + ", rg=" + rg + ", siglaufrg=" + siglaufrg
				+ ", tipoendereco=" + tipoendereco + ", tipologradouro=" + tipologradouro + ", logradouro=" + logradouro
				+ ", numero=" + numero + ", complemento=" + complemento + ", cep=" + cep + ", codmunicipio="
				+ codmunicipio + ", codbairro=" + codbairro + ", siglauf=" + siglauf + ", siglapais=" + siglapais
				+ ", telefone1=" + telefone1 + ", telefone2=" + telefone2 + ", fax=" + fax + ", referenciacontato="
				+ referenciacontato + ", email=" + email + ", url=" + url + ", codomb=" + codomb + ", tipocpfcnpj="
				+ tipocpfcnpj + "]";
	}
	
	
	
	
	
}
