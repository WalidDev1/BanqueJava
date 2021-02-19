package compte.bancaire.Model;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction implements Serializable{

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMontant() {
        return montant;
    }

    public void setMontant(String montant) {
        this.montant = montant;
    }

    public String getBenificiaire() {
        return benificiaire;
    }

    public void setBenificiaire(String benificiaire) {
        this.benificiaire = benificiaire;
    }

    public String getExpediteur() {
        return expediteur;
    }

    public void setExpediteur(String expediteur) {
        this.expediteur = expediteur;
    }
	
        private int id;
	private Date date;
	private String montant;
	private boolean result;
	private String benificiaire;
	private String expediteur;
	private String action;
	 
	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Transaction(Date date, String montant, boolean result, String benificiaire, String expediteur, String action) {
		this.date = date;
		this.montant = montant;
		this.result = result;
		this.benificiaire = benificiaire;
		this.expediteur = expediteur;
		this.action = action;
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public boolean isResult() {
		return result;
	}
	
	public void setResult(boolean result) {
		this.result = result;
	}
	
	public void info(){
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		if(result)
		System.out.println(" ------------------ Transactionction du "+formatter.format(date)+" ✅ ------------------");
		else
		System.out.println(" ------------------ Transactionction du "+formatter.format(date)+" 🚫 ------------------");
		if(benificiaire != "")
		System.out.println(" Beneficiaire : "+benificiaire);
		System.out.println(" Action : "+action);
		System.out.println(" Expediteur : "+expediteur);
		System.out.println(" Montant : "+montant);
	}
	
	
	
}
