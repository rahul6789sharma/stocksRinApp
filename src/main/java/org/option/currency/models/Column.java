package org.option.currency.models;

public class Column {

	// private String CE_Chart;
	private String CE_OI = "-";
	private String CE_Change_in_OI = "-";
	private String CE_Volume = "-";
	private String CE_IV = "-";
	private String CE_LTP = "-";
	private String CE_Net_Change = "-";
	private String CE_deta="-";
	private String CE_theta="-";
	private String CE_rho="-";
	
	// private String CE_Bid_Qty;
	// private String CE_Bid_Price;
	// private String CE_Ask_Price;
	// private String CE_Ask_Qty;

	private String Strike_Price = "-";

	// private String PE_Bid_Qty;
	// private String PE_Bid_Price;
	// private String PE_Ask_Price;
	// private String PE_Ask_Qty;
	private String PE_Net_Change = "-";
	private String PE_LTP = "-";
	private String PE_IV = "-";
	private String PE_Volume = "-";
	private String PE_Change_in_OI = "-";
	private String PE_OI = "-";
	private String PE_deta="-";
	private String PE_theta="-";
	private String PE_rho="-";


	public void setValueForNifty(int i, String value){
		if (i == 0) {
			// CE_Chart = value;
		} else if (i == 1) {
			CE_OI = value;
		} else if (i == 2) {
			CE_Change_in_OI = value;
		} else if (i == 3) {
			CE_Volume = value;
		} else if (i == 4) {
			CE_IV = value;
		} else if (i == 5) {
			CE_LTP = value;
		} else if (i == 6) {
			CE_Net_Change=value;
		} else if (i == 7) {
			// CE_Bid_Price = value;
		} else if (i == 8) {
			// CE_Ask_Price = value;
		} else if (i == 9) {
			// CE_Ask_Qty = value;
		} else if (i == 11) {
			Strike_Price = value;
		} else if (i == 12) {
			// PE_Bid_Price = value;
		} else if (i == 13) {
			// PE_Ask_Price = value;
		} else if (i == 14) {
			// PE_Ask_Qty = value;
		}else if (i == 15) {
			// PE_Ask_Qty = value;
		} else if (i == 16) {
			PE_Net_Change = value;
		} else if (i == 17) {
			PE_LTP = value;
		} else if (i == 18) {
			PE_IV = value;
		} else if (i == 19) {
			PE_Volume = value;
		} else if (i == 20) {
			PE_Change_in_OI = value;
		} else if (i == 21) {
			PE_OI = value;
		} else if (i == 22) {
			// PE_Chart = value;
		}
	}
	// private String PE_Chart;

	public void setVal(int i, String value) {
		if (i == 0) {
			// CE_Chart = value;
		} else if (i == 1) {
			CE_OI = value;
		} else if (i == 2) {
			CE_Change_in_OI = value;
		} else if (i == 3) {
			CE_Volume = value;
		} else if (i == 4) {
			CE_IV = value;
		} else if (i == 5) {
			CE_LTP = value;
		} else if (i == 6) {
			// CE_Bid_Qty = value;
		} else if (i == 7) {
			// CE_Bid_Price = value;
		} else if (i == 8) {
			// CE_Ask_Price = value;
		} else if (i == 9) {
			// CE_Ask_Qty = value;
		} else if (i == 10) {
			Strike_Price = value;
		} else if (i == 11) {
			// PE_Bid_Qty = value;
		} else if (i == 12) {
			// PE_Bid_Price = value;
		} else if (i == 13) {
			// PE_Ask_Price = value;
		} else if (i == 14) {
			// PE_Ask_Qty = value;
		} else if (i == 15) {
			PE_LTP = value;
		} else if (i == 16) {
			PE_IV = value;
		} else if (i == 17) {
			PE_Volume = value;
		} else if (i == 18) {
			PE_Change_in_OI = value;
		} else if (i == 19) {
			PE_OI = value;
		} else if (i == 20) {
			// PE_Chart = value;
		}

	}

	public String getCE_OI() {
		return CE_OI;
	}

	public void setCE_OI(String cE_OI) {
		CE_OI = cE_OI;
	}

	public String getCE_Change_in_OI() {
		return CE_Change_in_OI;
	}

	public void setCE_Change_in_OI(String cE_Change_in_OI) {
		CE_Change_in_OI = cE_Change_in_OI;
	}

	public String getCE_Volume() {
		return CE_Volume;
	}

	public void setCE_Volume(String cE_Volume) {
		CE_Volume = cE_Volume;
	}

	public String getCE_IV() {
		return CE_IV;
	}

	public void setCE_IV(String cE_IV) {
		CE_IV = cE_IV;
	}

	public String getCE_LTP() {
		return CE_LTP;
	}

	public void setCE_LTP(String cE_LTP) {
		CE_LTP = cE_LTP;
	}

	public String getStrike_Price() {
		return Strike_Price;
	}

	public void setStrike_Price(String strike_Price) {
		Strike_Price = strike_Price;
	}

	public String getPE_LTP() {
		return PE_LTP;
	}

	public void setPE_LTP(String pE_LTP) {
		PE_LTP = pE_LTP;
	}

	public String getPE_IV() {
		return PE_IV;
	}

	public void setPE_IV(String pE_IV) {
		PE_IV = pE_IV;
	}

	public String getPE_Volume() {
		return PE_Volume;
	}

	public void setPE_Volume(String pE_Volume) {
		PE_Volume = pE_Volume;
	}

	public String getPE_Change_in_OI() {
		return PE_Change_in_OI;
	}

	public void setPE_Change_in_OI(String pE_Change_in_OI) {
		PE_Change_in_OI = pE_Change_in_OI;
	}

	public String getPE_OI() {
		return PE_OI;
	}

	public void setPE_OI(String pE_OI) {
		PE_OI = pE_OI;
	}

	public String getCE_deta() {
		return CE_deta;
	}

	public void setCE_deta(String cE_deta) {
		CE_deta = cE_deta;
	}

	public String getCE_theta() {
		return CE_theta;
	}

	public void setCE_theta(String cE_theta) {
		CE_theta = cE_theta;
	}

	public String getCE_rho() {
		return CE_rho;
	}

	public void setCE_rho(String cE_rho) {
		CE_rho = cE_rho;
	}

	public String getPE_deta() {
		return PE_deta;
	}

	public void setPE_deta(String pE_deta) {
		PE_deta = pE_deta;
	}

	public String getPE_theta() {
		return PE_theta;
	}

	public void setPE_theta(String pE_theta) {
		PE_theta = pE_theta;
	}

	public String getPE_rho() {
		return PE_rho;
	}

	public void setPE_rho(String pE_rho) {
		PE_rho = pE_rho;
	}

	
	public String getCE_Net_Change() {
		return CE_Net_Change;
	}

	public void setCE_Net_Change(String cE_Net_Change) {
		CE_Net_Change = cE_Net_Change;
	}

	public String getPE_Net_Change() {
		return PE_Net_Change;
	}

	public void setPE_Net_Change(String pE_Net_Change) {
		PE_Net_Change = pE_Net_Change;
	}

	@Override
	public String toString() {
		return "Column [CE_OI=" + CE_OI + " CE_Change_in_OI=" + CE_Change_in_OI + " CE_Volume=" + CE_Volume
				+ " CE_IV=" + CE_IV + " CE_LTP=" + CE_LTP + " CE_Net_Change=" + CE_Net_Change + " CE_deta="
				+ CE_deta + " CE_theta=" + CE_theta + " CE_rho=" + CE_rho + " Strike_Price=" + Strike_Price
				+ " PE_Net_Change=" + PE_Net_Change + " PE_LTP=" + PE_LTP + " PE_IV=" + PE_IV + " PE_Volume="
				+ PE_Volume + " PE_Change_in_OI=" + PE_Change_in_OI + " PE_OI=" + PE_OI + " PE_deta=" + PE_deta
				+ " PE_theta=" + PE_theta + " PE_rho=" + PE_rho + "]";
	}


}