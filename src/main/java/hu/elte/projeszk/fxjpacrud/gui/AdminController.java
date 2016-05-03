package hu.elte.projeszk.fxjpacrud.gui;

import com.panemu.tiwulfx.common.TableCriteria;
import com.panemu.tiwulfx.common.TableData;
import com.panemu.tiwulfx.table.TableControl;
import com.panemu.tiwulfx.table.TableController;
import hu.elte.projeszk.fxjpacrud.LoginFX;
import hu.elte.projeszk.fxjpacrud.db.DaoManager;
import hu.elte.projeszk.fxjpacrud.db.GenericDao;
import hu.elte.projeszk.fxjpacrud.entity.UserEntity;
import hu.elte.projeszk.fxjpacrud.security.PasswordHash;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;

/**
 *
 * @author Gergő
 */
public class AdminController extends TableController<UserEntity> implements Initializable {

    @FXML
    private TableControl<UserEntity> tableControl;
    private final GenericDao<UserEntity> userDao = DaoManager.getInstance().getUserDao();

    /**
     *
     */
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
        tableControl.setVisibleComponents(false, TableControl.Component.BUTTON_EXPORT);
        tableControl.setVisibleComponents(false, TableControl.Component.BUTTON_PAGINATION);
        tableControl.reloadFirstPage();
    }

    /**
     *
     * @param startIndex
     * @param filteredColumns
     * @param sortedColumns
     * @param sortingOrders
     * @param maxResult
     * @return
     */
    @Override
    public TableData loadData(int startIndex, List<TableCriteria> filteredColumns, List<String> sortedColumns, List<TableColumn.SortType> sortingOrders, int maxResult) {
        System.out.println("Loading data...");
        List<UserEntity> listUser = userDao.findAll();
        return new TableData<>(listUser, false, listUser.size());
    }

    /**
     *
     * @param records
     */
    @Override
    public void delete(List<UserEntity> records) {
        System.out.println("delete " + records.toString());
        for (UserEntity record : records) {
            userDao.delete(userDao.findById(record.getId()));
        }
    }

    /**
     *
     * @param item
     * @return
     */
    @Override
    public boolean isRecordEditable(UserEntity item) {
        return true;
    }

    /**
     *
     * @param previousMode
     */
    @Override
    public void postSave(TableControl.Mode previousMode) {
        System.out.println("postSave");
        super.postSave(previousMode);
        tableControl.refresh();
    }

    /**
     *
     * @param title
     * @param maxResult
     * @param tblView
     * @param lstCriteria
     */
    @Override
    public void exportToExcel(String title, int maxResult, TableControl<UserEntity> tblView, List<TableCriteria> lstCriteria) {
        System.out.println("exportToExcel");
        Platform.runLater(() -> {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Function not supperted yet!");
            alert.showAndWait();
        });
    }

    /**
     *
     * @param table
     * @return
     */
    @Override
    public boolean canDelete(TableControl<UserEntity> table) {
        return true;
    }

    /**
     *
     * @param selectedRecord
     * @return
     */
    @Override
    public boolean canEdit(UserEntity selectedRecord) {
        return true;
    }

    /**
     *
     * @param newRecord
     * @return
     */
    @Override
    public UserEntity preInsert(UserEntity newRecord) {
        System.out.println("preInsert " + newRecord.toString());
        return LoginFX.getInstance().getChangeContent().displayPopupDialog();
    }

    /**
     *
     * @param records
     * @return
     */
    @Override
    public List<UserEntity> update(List<UserEntity> records) {
        System.out.println("update " + records.toString());
        for (UserEntity record : records) {
            try {
                record.setPassword(PasswordHash.createHash(record.getPassword()));
                userDao.update(record);
            } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            }
        }
        return records;
    }

    /**
     *
     * @param newRecords
     * @return
     */
    @Override
    public List<UserEntity> insert(List<UserEntity> newRecords) {
        System.out.println("insert " + newRecords.toString());
        for (UserEntity newRecord : newRecords) {
            if (newRecord.getName() != null && newRecord.getEmail() != null && newRecord.getPassword() != null) {
                try {
                    newRecord.setPassword(PasswordHash.createHash(newRecord.getPassword()));
                    userDao.create(newRecord);
                } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                }
            }
        }
        return newRecords;
    }

}
