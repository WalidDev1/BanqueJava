package compte.bancaire.Model;
import Data.Data;
import compte.bancaire.Interface.IBanque;
import java.io.Serializable;
import java.util.Scanner; 
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Banque implements IBanque , Serializable {
	private String nom ;
	private String adresse;
	private Vector<Compte> comptes = new Vector<>();
	private int capacite ;
	private int count = 0;
	public Data data ;
        
	public Banque(String nom, String adresse, int capacite) {
                comptes = new Vector<Compte>();
		this.setNom(nom);
		this.setAdresse(adresse);
		this.setCapacite(capacite);
	}

	public Banque() {
		// TODO Auto-generated constructor stub
	}

	public String getNom() {
		return nom;
	}
	
	public Vector<Compte> getCompte() {
		return comptes;
	}

	public void setCompte(Vector<Compte> compte) {
		this.comptes = compte;
	}

	public void setNom(String nom) {
		if(nom != ""){
			this.nom = nom;
		}
	}
	
	public String getAdresse() {
		return adresse;
	}
	
	public void setAdresse(String adresse) {
		if(adresse != "") {
		this.adresse = adresse;
		}
	}
	
	public int getCapacite() {
		return capacite;
	}

	public void setCapacite(int capacite) {
		if(capacite >= 0){
		this.capacite = capacite;
		}
	}
	
	public boolean Ajouter(Compte c){
                count = data.listeCompte.lastElement().getNumero() ;
		if(c != null){
			c.setNumero(count + 1);
                        this.comptes.add(c);
                        data.listeCompte.add(c);
			return true ;
		}else{
                JOptionPane.showMessageDialog(new JFrame(), "Champs vides ! ✅");
                data.Save();
		return false;
                }
	}

	public boolean Supprimer(Compte c){
            if(c != null){
                    for(int i =0 ; i <= data.listeCompte.size()  ; i ++) {
                        if(data.listeCompte.get(i).getNumero() == c.getNumero()) {
                            data.listeCompte.remove(i);
                            JOptionPane.showMessageDialog(new JFrame(), "Spprimer ! ✅");
                            data.Save();
                            return true;
                        }
                    }  
            }else{
                JOptionPane.showMessageDialog(new JFrame(), "Champs vides ! ✅");
                return false ;
            }
        return false ;
	}
	
	public boolean Modifier(Compte c){
            if(c != null){
                data.listeCompte.forEach((item) ->{
                    if(item.getNumero() == c.getNumero()){
                        item.setProprietaire(c.getProprietaire());
                        item.setSolde(c.getSolde());
                    }
                });
                 JOptionPane.showMessageDialog(new JFrame(), "Modifier avec succes ✅");
                 data.Save();
            return true;
            }
	return false;
	}

	public Compte Rechercher(int num){
		for (Compte compte : comptes) {
			if(compte.getNumero() == num){
				return compte ;
			}
		}
		return null;
	}

	
	public void info(Scanner sc){
		System.out.println(" ----------------------- Banque "+this.nom+" -----------------------");
		System.out.println(" Adresse : "+this.adresse);
		System.out.println(" Capaciter : "+this.capacite);
		System.out.println(" Voullez vous voir tous les comptes de cette banque (O / N) :");
		if(sc.next().toUpperCase() == "O") {
			for (Compte compte : comptes) {
				compte.Info();
			}
		}
	}

  

    @Override
    public DefaultTableModel Sycronizer() {
        DefaultTableModel modelListeCompte = new DefaultTableModel();
        modelListeCompte.addColumn("numero");
        modelListeCompte.addColumn("proprietaire");
        modelListeCompte.addColumn("solde");
        for (Compte compte : data.listeCompte) {
            modelListeCompte.addRow(new Object[]{compte.getNumero(),compte.getProprietaire(),compte.getSolde()});
        }
        data.Save();
        return modelListeCompte ;
    }

    @Override
    public DefaultTableModel AfficherHisto(Compte c) {
        DefaultTableModel modelisteHistranct = new DefaultTableModel();
        modelisteHistranct.addColumn("id");    
        modelisteHistranct.addColumn("Date");
            modelisteHistranct.addColumn("Montant");
            modelisteHistranct.addColumn("Benificiaire");
            modelisteHistranct.addColumn("Action");
            modelisteHistranct.addColumn("Result");
        for (Compte compte : data.listeCompte) {
            if(compte.getNumero() == c.getNumero()){
                compte.getHistoriqueTransaction().forEach((item) ->{
                modelisteHistranct.addRow(new Object[]{item.getId(),item.getDate().toString(),item.getMontant(),item.getBenificiaire(),item.getAction(),(item.isResult())? "Effectuer":"Erreur"});
                });
            }
            
        }
        data.Save();
        return modelisteHistranct ;
        
    }
	
}
