
import compte.bancaire.Model.Banque;
import compte.bancaire.Model.Compte;
import compte.bancaire.Model.Transaction;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.ExecutionException;
import javafx.scene.control.SplitPane;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

public class Main {

            public static Banque banque = new Banque();
            public static JFrame Jbanque = new JFrame("Banque");
            
            
            public static JTextField proprioField = new JTextField();
            public static JTextField soldeField = new JTextField();
            // 
            public static JTable listeCompte = new JTable()  {
                public boolean isCellEditable(int row, int column)
                {
                  return false;//This causes all cells to be not editable
                }
            };
            public static DefaultTableModel modelListeCompte = new DefaultTableModel();
            //
            public static JTable listeHistranct = new JTable()  {
                public boolean isCellEditable(int row, int column)
                {
                  return false;//This causes all cells to be not editable
                }
             };
            public static DefaultTableModel modelisteHistranct = new DefaultTableModel();
            
	public static void main(String[] args) {
            banque.data.Read();
            Jbanque.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            Jbanque.setExtendedState(JFrame.MAXIMIZED_BOTH); 
            Jbanque.getContentPane().setBackground(Color.white);
            // Configuration model Liste Compte 
            JScrollPane tableContainer = new JScrollPane(listeCompte);
            ActualiserListeCompte();
            listeCompte.setSize(30, 50);
            
            //Configuration model Liste historique des tranaction 
            
            JScrollPane tableContainerHisto = new JScrollPane(listeHistranct);
            
            
            JPanel panel = new JPanel();
            GridBagLayout layout = new GridBagLayout();
            panel.setLayout(layout);
            GridBagConstraints gbc = new GridBagConstraints();

            // Put constraints on different buttons
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.gridx = 0;
            gbc.gridy = 0;
            JLabel labListeCompte = new JLabel("Liste des compte");
            labListeCompte.setFont(new Font("", Font.BOLD, 20));
            panel.add(labListeCompte, gbc);
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.gridx = 0;
            gbc.gridy = 1;
            panel.add(tableContainer, gbc);

            gbc.gridx = 1;
            gbc.gridy = 1;
            panel.add(new Panel(), gbc);

            gbc.gridx = 2;
            gbc.gridy = 0;
            JLabel labHistoTransact = new JLabel("Historique du compte");
            labHistoTransact.setFont(new Font("", Font.BOLD, 20));
            panel.add(labHistoTransact, gbc);
            gbc.gridx = 2;
            gbc.gridy = 1;
            panel.add(tableContainerHisto, gbc);


            gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.gridwidth = 3;
    //        panel.setBackground(Color.red);
            JPanel panelForm = new JPanel();
            panel.add(panelForm, gbc);
            panelForm.setLayout(layout);
            
            
            gbc.gridx = 0;
            gbc.gridy = 0;
            
            
            soldeField.setPreferredSize(new Dimension(400, 50));
            soldeField.setBorder(BorderFactory.createTitledBorder("Solde"));
            panelForm.add(soldeField , gbc);
            
            gbc.gridx = 0;
            gbc.gridy = 1;
            proprioField.setPreferredSize(new Dimension(400, 50));
            proprioField.setBorder(BorderFactory.createTitledBorder("Proprietaire"));
            panelForm.add(proprioField, gbc);
            
            
            gbc.gridx = 0;
            gbc.gridy = 2;
            JPanel panelOptionCRUD = new JPanel(new FlowLayout());
            panelForm.add(panelOptionCRUD, gbc);
            
            Button btnAdd = new Button("Ajouter ");
            Button btnEdit = new Button("Modifier");
            Button btnRemove = new Button("Supprimer");
            btnEdit.setEnabled(false);
            btnRemove.setEnabled(false);
            panelOptionCRUD.add(btnAdd);
            panelOptionCRUD.add(btnEdit);
            panelOptionCRUD.add(btnRemove);
            
            gbc.gridx = 0;
            gbc.gridy = 3;
            JPanel panelOptionHisto = new JPanel(new FlowLayout());
            panelForm.add(panelOptionHisto, gbc);
            
            Button btnDeposer = new Button("Deposer");
            btnDeposer.setEnabled(false);
            Button btnVerser = new Button("Verser");
            btnVerser.setEnabled(false);
            Button btnRetirer = new Button("Retirer");
            btnRetirer.setEnabled(false);
            
            panelOptionHisto.add(btnDeposer);
            panelOptionHisto.add(btnVerser);
            panelOptionHisto.add(btnRetirer);
            
            Jbanque.getContentPane().add(panel);
            Jbanque.setVisible(true);

            btnAdd.addActionListener((e) -> {
                if(!soldeField.getText().isEmpty() && !proprioField.getText().isEmpty()){
                    banque.Ajouter(new Compte(Double.parseDouble(soldeField.getText().toString()) , proprioField.getText().toString()));
                    ActualiserListeCompte();
                    ViderChamps();
                }else{
                    JOptionPane.showMessageDialog(new JFrame(), "Champs vides ! ✅");
                }
                
            });
            
            btnEdit.addActionListener((e) -> {
                if(!soldeField.getText().isEmpty() && !proprioField.getText().isEmpty()){
                    Compte editCompte = new Compte(Double.parseDouble(soldeField.getText()), proprioField.getText().toString());
                    editCompte.setNumero(SelectedRow().getNumero());
                    banque.Modifier(editCompte);
                    ActualiserListeCompte();
                    ViderChamps();
                    }else{
                    JOptionPane.showMessageDialog(new JFrame(), "Champs vides ! ✅");
                }
                });

            btnRemove.addActionListener((e) -> {
                if(!soldeField.getText().isEmpty() && !proprioField.getText().isEmpty()){
                    Compte removeCompte = new Compte(Double.parseDouble(soldeField.getText()), proprioField.getText().toString());
                    removeCompte.setNumero(SelectedRow().getNumero());
                    banque.Supprimer(removeCompte);
                    ActualiserListeCompte();
                    ViderChamps();
                }else{
                    JOptionPane.showMessageDialog(new JFrame(), "Champs vides ! ✅");
                }
                
            });
            
            listeCompte.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    ViderChamps();
                   if (e.getClickCount() == 1) { // check if a  click
                    soldeField.setText(""+SelectedRow().getSolde());
                    proprioField.setText(SelectedRow().getProprietaire());
                    btnRetirer.setEnabled(true);
                    btnDeposer.setEnabled(true);
                    btnVerser.setEnabled(true);
            
                    btnEdit.setEnabled(true);
                    btnRemove.setEnabled(true);
                    ActualiserListeHisto(SelectedRow());
                   }else{
                       
                    btnEdit.setEnabled(false);
                    btnRemove.setEnabled(false);
                    btnRetirer.setEnabled(false);
                    btnDeposer.setEnabled(false);
                    btnVerser.setEnabled(false);
                   }
                }
            });
            
            btnDeposer.addActionListener((e) -> {
                TransactForm(SelectedRow() , 1);
                ActualiserListeCompte();
                
            });
            
            btnVerser.addActionListener((e) -> {
                TransactForm(SelectedRow() , 2);
                ActualiserListeCompte();
                
            });
            
            btnRetirer.addActionListener((e) -> {
                TransactForm(SelectedRow() , 3);
                ActualiserListeCompte();
                
            });
              
	}
	
       
        
        public static Compte SelectedRow(){
            Compte returnCompte = new Compte();
            for (Compte item : banque.data.listeCompte) {
                System.out.println(listeCompte.getValueAt(listeCompte.getSelectedRow(), 0).toString());
                if(item.getNumero() == Integer.parseInt(listeCompte.getValueAt(listeCompte.getSelectedRow(), 0).toString())){
                
               returnCompte = item ;
               break ;
            }
            }
            return returnCompte ;
        }
        
        public static void ViderChamps(){
            soldeField.setText("");
            proprioField.setText("");
        }

        
        public static void ActualiserListeCompte(){
            listeCompte.setModel(banque.Sycronizer());
        }
        
        public static void ActualiserListeHisto(Compte compteAction){
            listeHistranct.setModel(banque.AfficherHisto(compteAction));
        }
        
        public static void TransactForm(Compte compteAction , int action){
        JTextField Destinataire = new JTextField();
         
        JTextField Montant = new JTextField();
        JPanel panel = new JPanel(new GridLayout(0, 1));
        
        if(action == 2){
            panel.add(new JLabel("Destinataire :"));
            panel.add(Destinataire);
        } 
        
        panel.add(new JLabel("Montant :"));
        panel.add(Montant);
        int result = JOptionPane.showConfirmDialog(null, panel, "Test",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            switch(action){
                case 1 :
                    compteAction.Deposer(Double.parseDouble(Montant.getText().toString()));
                    break ;
                case 2 :
                    Compte destinataireC = new Compte();
                    for (Compte item : banque.data.listeCompte) {
                        if(item.getProprietaire().equalsIgnoreCase(Destinataire.getText())){
                                destinataireC = item ;
                                break;
                        }
                    }
                    
                    compteAction.Versement(destinataireC, Double.parseDouble(Montant.getText().toString())) ;
                    break;
                case 3 :
                    compteAction.Retirer(Double.parseDouble(Montant.getText().toString()));
                default :
            }
            
            ActualiserListeCompte();
            ActualiserListeHisto(compteAction);
            banque.data.Save();
        } else {
            System.out.println("Cancelled");
        }
    }


}
