package com.jsfcourse.calc;

import java.io.Serializable;
import java.text.DecimalFormat;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class CalcBB implements Serializable {
	
	private static final String PAGE_STAY_AT_THE_SAME = null;
	
	private String moc;
	private String ilosc;
	private String czas;
	private String result;
	
	public String getMoc() {
		return moc;
	}

	public void setMoc(String moc) {
		this.moc = moc;
	}

	public String getIlosc() {
		return ilosc;
	}

	public void setIlosc(String ilosc) {
		this.ilosc = ilosc;
	}

	public String getCzas() {
		return czas;
	}

	public void setCzas(String czas) {
		this.czas = czas;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String calc() {
		FacesContext ctx = FacesContext.getCurrentInstance();		

		double moc = Double.parseDouble(this.moc);
		double ilosc = Double.parseDouble(this.ilosc);
		double czas = Double.parseDouble(this.czas);
		
		DecimalFormat f = new DecimalFormat("##.00");

		result = f.format((moc / 1000) * ilosc * (czas / 60));
		
		ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Wynik: " + result, null));
		return PAGE_STAY_AT_THE_SAME; 
	}
}
