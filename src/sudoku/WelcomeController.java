/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import static sudoku.Intermediate.*;

/**
 * FXML Controller class for the welcome page.
 *
 * @author Nafis
 */
public class WelcomeController implements Initializable
{       
    /**
     * Loads the puzzles selection scene.
     * @param event Mouse click
     * @throws Exception when file is not loaded correctly.
     * @author Ishmam
     */
    @FXML
    private void clickpuzzles(ActionEvent event) throws Exception
    {
        Parent puzzlesParent = FXMLLoader.load(getClass().getResource("Puzzles.fxml"));
        Scene puzzlesScene = new Scene(puzzlesParent);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(puzzlesScene);
        window.show();
    }
    
    /**
     * Loads the game help scene.
     * @param event Mouse click
     * @throws Exception Exception when file is not loaded correctly.
     * @author Tauseef
     */
    @FXML
    private void clickGraph(ActionEvent event) throws Exception
    {        
        growthstatsevent = event;
        Parent puzzlesParent = FXMLLoader.load(getClass().getResource("GrowthStats.fxml"));                
//        Scene puzzlesScene = new Scene(puzzlesParent);
        
//        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
//        window.setScene(puzzlesScene);
//        window.show();
        
    }
    
    @FXML
    private void clickHowToPlay(ActionEvent event) throws Exception
    {
        Parent puzzlesParent = FXMLLoader.load(getClass().getResource("HowToPlay.fxml"));
        Scene puzzlesScene = new Scene(puzzlesParent);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(puzzlesScene);
        window.show();
    }
    
    /**
     * Exits the application
     * @param event Mouse click
     * @author Tauseef
     */
    @FXML
    private void clickexit(ActionEvent event)
    {
        Platform.exit();
    }
    
    /**
     * Loads the puzzles in memory from .csv file.
     * @throws FileNotFoundException when file is not in directory.
     * @author Nafis
     */
    private void load_levels() throws FileNotFoundException
    {
        //load easy levels
        Scanner sc = new Scanner(new File("src\\Levels\\levels.csv"));
        sc.useDelimiter(",|\r\n"); 
        
        int i = 0, j = 0, k = 0, num;
        String s;
        while(sc.hasNext()) 
        {
            s = sc.next();
            num = Integer.parseInt(s);            
            alllevels[i][j][k] = num;
            
            k++;
            if(k > 8)
            {
                k = 0;
                j++;
            }
            if(j > 8)
            {
                j = 0;
                i++;
            }
            
        }
        sc.close();
        
        //load normal levels
        sc = new Scanner(new File("src\\Levels\\levels_med.csv"));
        sc.useDelimiter(",|\r\n"); 
        while(sc.hasNext()) 
        {
            s = sc.next();
            num = Integer.parseInt(s);            
            alllevels[i][j][k] = num;
            
            k++;
            if(k > 8)
            {
                k = 0;
                j++;
            }
            if(j > 8)
            {
                j = 0;
                i++;
            }
            
        }
        sc.close();
        
        //load hard levels
        sc = new Scanner(new File("src\\Levels\\levels_hard.csv"));
        sc.useDelimiter(",|\r\n"); 
        while(sc.hasNext()) 
        {
            s = sc.next();
            num = Integer.parseInt(s);            
            alllevels[i][j][k] = num;
            
            k++;
            if(k > 8)
            {
                k = 0;
                j++;
            }
            if(j > 8)
            {
                j = 0;
                i++;
            }
            
        }
        sc.close();
    }
    
    
    /**
     * Loads the puzzle solve timings in memory from .txt file.
     * @throws FileNotFoundException when file is not in directory.
     * @author Nafis
     */
    private void load_times() throws FileNotFoundException //Load user time
    {                
        Scanner sc = new Scanner(new File("src\\User Data\\bestTime.txt"));
        sc.useDelimiter("\r\n");
        
        int i = 0;
        String s;
        while(sc.hasNext())
        {            
            s = sc.next();            
            alltimes[i] = Integer.parseInt(s);  
            i++;
        }
        sc.close();
    }
    
    /**
     * Initializes the controller class, sets the difficulty level and invokes the loading of levels and timings.
     * @param url
     * @param rb 
     * @author Nafis
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        mode = 0;
        if(all_loaded == false)
        {
            try
            {
                load_levels();
                load_times();
            }
            catch(FileNotFoundException ex)
            {
                Logger.getLogger(WelcomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
            all_loaded = true;                       
        }
        
    }    
    
}
