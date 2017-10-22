/*
 * Fonograma.java
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
public class Fonograma implements Serializable{

	/**
	 * 
	 */
	@FieldIgnored
	private static final long serialVersionUID = 3710411085521514407L;
	@FieldFixedLength(8)
	private String campocontrole;
	@FieldFixedLength(9)
	private String codecaefonograma;
	@FieldFixedLength((15))
	private String codSISRCfonograma;
	@FieldFixedLength(13)
	private String codecadobra;
	@FieldFixedLength(15)
	private String codSISRCobra;
	@FieldFixedLength(9)
	private String codecadpoutpourrit;
	@FieldFixedLength(15)
	private String codSISRCpoutpourrit;
	@FieldFixedLength(8)
	private String codgra;
	@FieldFixedLength(12)
	private String codSISRC;
	@FieldFixedLength(8)
	private String datagravacaooriginal;
	@FieldFixedLength(8)
	private String datalancamento;
	@FieldFixedLength(8)
	private String dataEmissaogra;
	@FieldFixedLength(1)
	private String einstrumental;
	@FieldFixedLength(6)
	private String duracao;
	@FieldFixedLength(12)
	private String codigocrowley;
	@FieldFixedLength(1)
	private String enacional;
	@FieldFixedLength(2)
	private String sigladopaisorigem;
	@FieldFixedLength(10)
	private String codigogeneromusical;
	@FieldFixedLength(3)
	private String classificacaofonograma;
	@FieldFixedLength(6)
	private String codigodoselo;
	@FieldFixedLength(60)
	private String nomecoletivofonograma;
	@FieldFixedLength(10)
	private String tipocoletivofonograma;
	@FieldFixedLength(1)
	private String coletivoenacional;
	@FieldFixedLength(2)
	private String siglapaiscoletivo;
	@FieldFixedLength(30)
	private String nomegenero;
	@FieldFixedLength(2)
	private String siglapaispublicacao;
	@FieldFixedLength(6)
	private String tipomidia;
	@FieldFixedLength(1)
	private String epublicacaosimutanea;
	@FieldFixedLength(3)
	@FieldOptional
	private String codigoarranjo;
	@FieldFixedLength(50)
	@FieldOptional
	private String desccomplementoarranjo;
	@FieldFixedLength(3)
	@FieldOptional
	private String codigopacote;
	
	
	
	
	
	
	
	public Fonograma() {


	}
	
	
	
	
	public String getCampocontrole() {
		return campocontrole;
	}
	public void setCampocontrole(String campocontrole) {
		this.campocontrole = campocontrole;
	}
	public String getCodecaefonograma() {
		return codecaefonograma;
	}
	public void setCodecaefonograma(String codecaefonograma) {
		this.codecaefonograma = codecaefonograma;
	}
	public String getCodSISRCfonograma() {
		return codSISRCfonograma;
	}
	public void setCodSISRCfonograma(String codSISRCfonograma) {
		this.codSISRCfonograma = codSISRCfonograma;
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
	public String getCodgra() {
		return codgra;
	}
	public void setCodgra(String codgra) {
		this.codgra = codgra;
	}
	public String getCodSISRC() {
		return codSISRC;
	}
	public void setCodSISRC(String codSISRC) {
		this.codSISRC = codSISRC;
	}
	public String getDatagravacaooriginal() {
		return datagravacaooriginal;
	}
	public void setDatagravacaooriginal(String datagravacaooriginal) {
		this.datagravacaooriginal = datagravacaooriginal;
	}
	public String getDatalancamento() {
		return datalancamento;
	}
	public void setDatalancamento(String datalancamento) {
		this.datalancamento = datalancamento;
	}
	public String getDataEmissaogra() {
		return dataEmissaogra;
	}
	public void setDataEmissaogra(String dataEmissaogra) {
		this.dataEmissaogra = dataEmissaogra;
	}
	public String getEinstrumental() {
		return einstrumental;
	}
	public void setEinstrumental(String einstrumental) {
		this.einstrumental = einstrumental;
	}
	public String getDuracao() {
		return duracao;
	}
	public void setDuracao(String duracao) {
		this.duracao = duracao;
	}
	public String getCodigocrowley() {
		return codigocrowley;
	}
	public void setCodigocrowley(String codigocrowley) {
		this.codigocrowley = codigocrowley;
	}
	public String getEnacional() {
		return enacional;
	}
	public void setEnacional(String enacional) {
		this.enacional = enacional;
	}
	public String getSigladopaisorigem() {
		return sigladopaisorigem;
	}
	public void setSigladopaisorigem(String sigladopaisorigem) {
		this.sigladopaisorigem = sigladopaisorigem;
	}
	public String getCodigogeneromusical() {
		return codigogeneromusical;
	}
	public void setCodigogeneromusical(String codigogeneromusical) {
		this.codigogeneromusical = codigogeneromusical;
	}
	public String getClassificacaofonograma() {
		return classificacaofonograma;
	}
	public void setClassificacaofonograma(String classificacaofonograma) {
		this.classificacaofonograma = classificacaofonograma;
	}
	public String getCodigodoselo() {
		return codigodoselo;
	}
	public void setCodigodoselo(String codigodoselo) {
		this.codigodoselo = codigodoselo;
	}
	public String getNomecoletivofonograma() {
		return nomecoletivofonograma;
	}
	public void setNomecoletivofonograma(String nomecoletivofonograma) {
		this.nomecoletivofonograma = nomecoletivofonograma;
	}
	public String getTipocoletivofonograma() {
		return tipocoletivofonograma;
	}
	public void setTipocoletivofonograma(String tipocoletivofonograma) {
		this.tipocoletivofonograma = tipocoletivofonograma;
	}
	public String getColetivoenacional() {
		return coletivoenacional;
	}
	public void setColetivoenacional(String coletivoenacional) {
		this.coletivoenacional = coletivoenacional;
	}
	public String getSiglapaiscoletivo() {
		return siglapaiscoletivo;
	}
	public void setSiglapaiscoletivo(String siglapaiscoletivo) {
		this.siglapaiscoletivo = siglapaiscoletivo;
	}
	public String getNomegenero() {
		return nomegenero;
	}
	public void setNomegenero(String nomegenero) {
		this.nomegenero = nomegenero;
	}
	public String getSiglapaispublicacao() {
		return siglapaispublicacao;
	}
	public void setSiglapaispublicacao(String siglapaispublicacao) {
		this.siglapaispublicacao = siglapaispublicacao;
	}
	public String getTipomidia() {
		return tipomidia;
	}
	public void setTipomidia(String tipomidia) {
		this.tipomidia = tipomidia;
	}
	public String getEpublicacaosimutanea() {
		return epublicacaosimutanea;
	}
	public void setEpublicacaosimutanea(String epublicacaosimutanea) {
		this.epublicacaosimutanea = epublicacaosimutanea;
	}
	public String getCodigoarranjo() {
		return codigoarranjo;
	}
	public void setCodigoarranjo(String codigoarranjo) {
		this.codigoarranjo = codigoarranjo;
	}
	public String getDesccomplementoarranjo() {
		return desccomplementoarranjo;
	}
	public void setDesccomplementoarranjo(String desccomplementoarranjo) {
		this.desccomplementoarranjo = desccomplementoarranjo;
	}
	public String getCodigopacote() {
		return codigopacote;
	}
	public void setCodigopacote(String codigopacote) {
		this.codigopacote = codigopacote;
	}




	@Override
	public String toString() {
		return "Fonograma [campocontrole=" + campocontrole + ", codecaefonograma=" + codecaefonograma
				+ ", codSISRCfonograma=" + codSISRCfonograma + ", codecadobra=" + codecadobra + ", codSISRCobra="
				+ codSISRCobra + ", codecadpoutpourrit=" + codecadpoutpourrit + ", codSISRCpoutpourrit="
				+ codSISRCpoutpourrit + ", codgra=" + codgra + ", codSISRC=" + codSISRC + ", datagravacaooriginal="
				+ datagravacaooriginal + ", datalancamento=" + datalancamento + ", dataEmissaogra=" + dataEmissaogra
				+ ", einstrumental=" + einstrumental + ", duracao=" + duracao + ", codigocrowley=" + codigocrowley
				+ ", enacional=" + enacional + ", sigladopaisorigem=" + sigladopaisorigem + ", codigogeneromusical="
				+ codigogeneromusical + ", classificacaofonograma=" + classificacaofonograma + ", codigodoselo="
				+ codigodoselo + ", nomecoletivofonograma=" + nomecoletivofonograma + ", tipocoletivofonograma="
				+ tipocoletivofonograma + ", coletivoenacional=" + coletivoenacional + ", siglapaiscoletivo="
				+ siglapaiscoletivo + ", nomegenero=" + nomegenero + ", siglapaispublicacao=" + siglapaispublicacao
				+ ", tipomidia=" + tipomidia + ", epublicacaosimutanea=" + epublicacaosimutanea + ", codigoarranjo="
				+ codigoarranjo + ", desccomplementoarranjo=" + desccomplementoarranjo + ", codigopacote="
				+ codigopacote + "]";
	}
	
	
	
	
	
	
	
	
}
