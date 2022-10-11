package com.jsfcourse.calc;

import java.text.DecimalFormat;
import java.io.Serializable;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.annotation.ManagedProperty;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class CalcBB implements Serializable {
	private String moc;
	private String ilosc;
	private String czas;
	private String result;

	// Resource injected
	@Inject
	@ManagedProperty("#{txtCalcErr}")
	private ResourceBundle txtCalcErr;

	// Resource injected
	@Inject
	@ManagedProperty("#{txtCalc}")
	private ResourceBundle txtCalc;

	// Resource loaded "manually"
	// (here after initialization in @PostConstruct method)
	// (locale explicitly given - here based on the view )
//	@PostConstruct
//	public void postConstruct() {
//		// loading resource (notice the full "file" name)
//		FacesContext context = FacesContext.getCurrentInstance();
//		txtCalc = ResourceBundle.getBundle("resources.textCalc", context.getViewRoot().getLocale());
//		txtCalcErr = ResourceBundle.getBundle("resources.textCalcErr", context.getViewRoot().getLocale());
//	}
	
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

		ctx.addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, txtCalcErr.getString("calcComputationOkInfo"), null));
		ctx.addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_WARN, txtCalc.getString("result") + ": " + result + "kWh", null));

		return null;

	}

}
