package com.jsfcourse.calc;

import java.text.DecimalFormat;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class CalcBB {
	private String moc;
	private String ilosc;
	private String czas;
	private String result;

	@Inject
	FacesContext ctx;

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

	public boolean doTheMath() {
		try {
			double moc = Double.parseDouble(this.moc);
			double ilosc = Double.parseDouble(this.ilosc);
			double czas = Double.parseDouble(this.czas);
			
			DecimalFormat f = new DecimalFormat("##.00");

			result = f.format((moc / 1000) * ilosc * (czas / 60));

			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Operacja wykonana poprawnie", null));
			return true;
		} catch (Exception e) {
			ctx.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błąd podczas przetwarzania parametrów", null));
			return false;
		}
	}

	// Go to "showresult" if ok
	public String calc() {
		if (doTheMath()) {
			return "showresult";
		}
		return null;
	}

	// Put result in messages on AJAX call
	public String calc_AJAX() {
		if (doTheMath()) {
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Wynik: " + result, null));
		}
		return null;
	}

	public String info() {
		return "info";
	}
}