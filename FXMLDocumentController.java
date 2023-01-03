/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package train;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.Initializable;


import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 *
 * @author Madhan Kumar S
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private TableColumn<record, String> atime; // a

    @FXML
    private TableColumn<record, String> tno; //b

    @FXML
    private TextField arrival; // 1

    @FXML
    private TableColumn<record, String> dtime; //c

    @FXML
    private TextField num; //2

    @FXML
    private TextField name;//3

    @FXML
    private TableColumn<record, String> tname; //d

    @FXML
    private Label label;

    @FXML
    private TableColumn<record, String> dest; //e

    @FXML
    private TextField dep; //4
    
    @FXML
    private TextField destination;

    @FXML
    private TableView<record> table;

    
    
    Connection con;
    PreparedStatement ps;
    
    public void Connect()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:1433/madhandatabase","root","root");
        } catch (ClassNotFoundException ex) {
          ex.getMessage();
        } catch (SQLException ex) {
          
        }
    }
   
    


    

    @FXML
    void add(ActionEvent event)  {
        Connect();
        
        String train_name,Destination,arrivaltime,departuretime, train_num;
        
        train_num=num.getText();
        train_name=name.getText();
        Destination=destination.getText();
        departuretime=dep.getText();
        arrivaltime=arrival.getText();
        
        
        
        try {
            
            PreparedStatement ps=con.prepareStatement("insert into train(Tno,Tname,Destination,arrival,departure)values(?,?,?,?,?)");
            
            
            ps.setString(1, train_num);
            ps.setString(2, train_name);
            ps.setString(3, Destination);
            ps.setString(4, departuretime);
            ps.setString(5, arrivaltime);
            
            int status=ps.executeUpdate();
            
            if(status==1){
                Alert a= new Alert(Alert.AlertType.INFORMATION);
                a.setTitle("SUCCESS!!!");
                a.setHeaderText("TRAIN");
                a.setContentText("Record added successfully");
                a.showAndWait();
                table();
                num.setText("");
                name.setText("");
                destination.setText("");
                arrival.setText("");
                dep.setText("");
                
                
            }
            else{
                Alert a= new Alert(Alert.AlertType.ERROR);
                a.setTitle("Fail");
                a.setHeaderText("Train");
                a.setContentText("Record wasnot added");
                a.showAndWait();
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void table() {
        ObservableList<record> R=FXCollections.observableArrayList();
        try{
            ps=con.prepareStatement("select Tno,Tname,Destination,arrival,departure from train");
            ResultSet rs=ps.executeQuery();
            
        while (rs.next())
        {
            record rec = new record();
            rec.setnum(rs.getString("Tno"));
            rec.setname(rs.getString("Tname"));
            rec.setdest(rs.getString("Destination"));
            rec.setarr(rs.getString("arrival"));
            rec.setarr(rs.getString("departure"));
            
            
            R.add(rec);
       }
    
            table.setItems(R);
                tno.setCellValueFactory(f -> f.getValue().num());
                tname.setCellValueFactory(f -> f.getValue().name());
                dest.setCellValueFactory(f -> f.getValue().dest());
                atime.setCellValueFactory(f -> f.getValue().arr());
                dtime.setCellValueFactory(f -> f.getValue().dept());
        }
        catch (SQLException ex)
       {
           Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
       }
    }

   
    

    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Connect();
        table();
    }    
    
}
