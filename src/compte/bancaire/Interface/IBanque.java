/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compte.bancaire.Interface;
import compte.bancaire.Model.Compte;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author admin
 */
public interface IBanque {
    
    public boolean Ajouter(Compte c);
    public boolean Supprimer(Compte c);
    public boolean Modifier(Compte c);
    public DefaultTableModel Sycronizer();
    public DefaultTableModel AfficherHisto(Compte c);
    
}
