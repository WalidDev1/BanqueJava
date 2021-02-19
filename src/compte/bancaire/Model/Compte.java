package compte.bancaire.Model;
import java.io.Serializable;
import java.util.Date;
import java.util.Vector;

public class Compte implements Serializable{
	
	private int numero ;
	private double solde;
	private String proprietaire;
	private Vector<Transaction> historiqueTransaction = new Vector<>();
	
	public Vector<Transaction> getHistoriqueTransaction() {
		return  historiqueTransaction ;
	}

	public void setHistoriqueTransaction(Vector<Transaction> historiqueTransaction) {
		this.historiqueTransaction = historiqueTransaction;
	}
	
	public void setHistoriqueTransaction(Transaction TransactionAdd) {
                TransactionAdd.setId(historiqueTransaction.size()+1);
                historiqueTransaction.add(TransactionAdd);
	}
	
	public Compte(double solde, String proprietaire) {
		this.solde = solde;
		this.proprietaire = proprietaire;
	}

	public Compte() {
		// TODO Auto-generated constructor stub
	}

	public int getNumero() {
		return numero;
	}
	
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	public double getSolde() {
		return solde;
	}
	
	public void setSolde(double solde) {
		this.solde = solde;
	}
	
	public String getProprietaire() {
		return proprietaire;
	}
	
	public void setProprietaire(String proprietaire) {
		this.proprietaire = proprietaire;
	}
	
	public boolean Deposer(double val) {
		boolean result = false;
		if(val > 0) { 
			this.solde += val;
			result = true ;
		}
		this.setHistoriqueTransaction(new Transaction(new Date(),"+ "+val,result," -- ",this.getProprietaire(),"Depot"));
		return result;
	}
	
	public boolean Retirer(double val){
		boolean result = false;
		if(val > 0 &&  val <= this.solde){
			this.solde -= val;
			result = true ;
		}
		this.setHistoriqueTransaction(new Transaction(new Date(),"- "+val,result," -- ",this.getProprietaire(),"Retrait"));
		return result;
	}
	
	public boolean Versement(Compte cDestinataire , double val){
		boolean result = false;
		if(this != null && cDestinataire != null){
			if(this.Retirer(val)) {
			cDestinataire.Deposer(val);
			result = true;
			}else {
			System.out.println("Solde insuffisant pour effectuer se versement 🚫");
			}
		}
		this.setHistoriqueTransaction(new Transaction(new Date(),"+ "+val,result,cDestinataire.getProprietaire(),this.getProprietaire(),"Virement"));
		return result;
	}

	public void Info(){
		System.out.println("Compte N: "+this.numero);
		System.out.println("Proprietaire : "+this.proprietaire);
		System.out.println("solde : "+this.solde);
	}
}
