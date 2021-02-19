/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import compte.bancaire.Model.Compte;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;

/**
 *
 * @author admin
 */
public class Data {
   public static  Vector<Compte> listeCompte = new Vector<>();
   
   public static void Save (){
        try {
         FileOutputStream fileOut =
         new FileOutputStream("/tmp/Banque.ser");
         ObjectOutputStream out = new ObjectOutputStream(fileOut);
         out.writeObject(listeCompte);
         out.close();
         fileOut.close();
         System.out.printf("Serialized data is saved in /tmp/Banque.ser");
      } catch (IOException i) {
         i.printStackTrace();
      }
   }
  
   public static void Read(){
         try {
         FileInputStream fileIn = new FileInputStream("/tmp/Banque.ser");
         ObjectInputStream in = new ObjectInputStream(fileIn);
         listeCompte = (Vector<Compte>) in.readObject();
         in.close();
         fileIn.close();
      } catch (IOException i) {
         i.printStackTrace();
         return;
      } catch (ClassNotFoundException c) {
         System.out.println("class not found");
         c.printStackTrace();
         return;
      }
   }
   
}
