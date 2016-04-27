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
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;

public class AdminController extends TableController<UserEntity> implements Initializable {

    @FXML
    private TableControl<UserEntity> tableControl;
    private final GenericDao<UserEntity> userDao = DaoManager.getInstance().getUserDao();

    public AdminController() {
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initTable();
    }

    private void initTable() {
        System.out.println("Initializing table...");
        tableControl.setController(this);
        tableControl.setRecordClass(UserEntity.class);
        tableControl.reloadFirstPage();
    }

    @Override
    public TableData loadData(int startIndex, List<TableCriteria> filteredColumns, List<String> sortedColumns, List<TableColumn.SortType> sortingOrders, int maxResult) {
        System.out.println("Loading data...");
        List<UserEntity> listUser = userDao.findAll();
        return new TableData<>(listUser, false, listUser.size());
    }

    @Override
    public void delete(List<UserEntity> records) {
        System.out.println(records.toString());
        for (UserEntity record : records) {
            userDao.delete(userDao.findById(record.getId()));
        }
    }

    @Override
    public boolean isRecordEditable(UserEntity item) {
        System.out.println("isRecordEditable");
        return true;
        /*super.isRecordEditable(item);*/ //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected boolean revertConfirmation(TableControl table, int numberOfChangedRows) {
        System.out.println("revertConfirmation");
        return super.revertConfirmation(table, numberOfChangedRows); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean validate(TableControl<UserEntity> tbl, List<UserEntity> changedRecords) {
        System.out.println("validate");
        return super.validate(tbl, changedRecords); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void displayInvalidErrorMessage(TableControl<UserEntity> tbl) {
        System.out.println("displayInvalidErrorMessage");
        super.displayInvalidErrorMessage(tbl); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void postSave(TableControl.Mode previousMode) {
        System.out.println("postSave");
        super.postSave(previousMode); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void postLoadData() {
        System.out.println("postLoadData");
        super.postLoadData(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void exportToExcel(String title, int maxResult, TableControl<UserEntity> tblView, List<TableCriteria> lstCriteria) {
        System.out.println("exportToExcel");
        //super.exportToExcel(title, maxResult, tblView, lstCriteria); //To change body of generated methods, choose Tools | Templates.
        Platform.runLater(() -> {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Function not supperted yet!");
            alert.showAndWait();
        });
    }

    @Override
    public void doubleClick(UserEntity record) {
        System.out.println("doubleClick");
        super.doubleClick(record); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean canDelete(TableControl<UserEntity> table) {
        System.out.println("canDelete");
        return true;
        /*super.canDelete(table);*/ //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean canEdit(UserEntity selectedRecord) {
        System.out.println("canEdit");
        return false;
        /*super.canEdit(selectedRecord);*/ //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UserEntity preInsert(UserEntity newRecord) {
        System.out.println("preInsert");
        return super.preInsert(newRecord); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<UserEntity> update(List<UserEntity> records) {
        System.out.println("update");
        for (UserEntity record : records) {
            userDao.update(record);
        }
        return records;
        /*super.update(records);*/ //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<UserEntity> insert(List<UserEntity> newRecords) {
        System.out.println("insert");
        for (UserEntity newRecord : newRecords) {
            userDao.create(newRecord);
        }
        return newRecords; //To change body of generated methods, choose Tools | Templates.
    }

}
