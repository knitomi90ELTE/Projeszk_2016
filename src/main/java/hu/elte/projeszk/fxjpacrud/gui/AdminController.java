/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.projeszk.fxjpacrud.gui;


import hu.elte.projeszk.fxjpacrud.db.TableQuery;

import com.panemu.tiwulfx.table.TableControl;
import hu.elte.projeszk.fxjpacrud.entity.UserEntity;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author capri
 */
public class AdminController implements Initializable {

    private TableQuery controller;
    @FXML
    private TableControl<UserEntity> tableControl;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initTable();
    }

    private void initTable() {
        controller = new TableQuery();
        tableControl.setController(controller);
        tableControl.setRecordClass(UserEntity.class);
        tableControl.reloadFirstPage();

    }
    
}
