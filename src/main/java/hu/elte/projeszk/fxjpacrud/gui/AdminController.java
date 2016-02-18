/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.projeszk.fxjpacrud.gui;


import com.panemu.tiwulfx.common.TableCriteria;
import com.panemu.tiwulfx.common.TableData;

import com.panemu.tiwulfx.table.TableControl;
import com.panemu.tiwulfx.table.TableController;
import hu.elte.projeszk.fxjpacrud.db.DaoManager;
import hu.elte.projeszk.fxjpacrud.db.GenericDao;
import hu.elte.projeszk.fxjpacrud.entity.UserEntity;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;

/**
 * FXML Controller class
 *
 * @author capri
 */
public class AdminController extends TableController<UserEntity> implements Initializable {

    @FXML
    private TableControl<UserEntity> tableControl;
    private GenericDao<UserEntity> userDao = DaoManager.getInstance().getUserDao();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initTable();
    }

    private void initTable() {
        tableControl.setController(this);
        tableControl.setRecordClass(UserEntity.class);
        tableControl.reloadFirstPage();

    }

    @Override
    public TableData loadData(int startIndex, List<TableCriteria> filteredColumns, List<String> sortedColumns, List<TableColumn.SortType> sortingOrders, int maxResult) {
        List<UserEntity> listUser = userDao.findAll();
        return new TableData<>(listUser, false, listUser.size());
    }
    
    @Override
    public void delete(List<UserEntity> records) {
        System.out.println(records.toString());
        super.delete(records);       
        for (UserEntity record : records) {
            userDao.delete(userDao.findById(record.getId()));
        }
 
    }
    
    
    
}
