/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.projeszk.fxjpacrud.db;

import hu.elte.projeszk.fxjpacrud.entity.UserEntity;
import com.panemu.tiwulfx.common.TableCriteria;
import com.panemu.tiwulfx.common.TableData;
import com.panemu.tiwulfx.table.TableController;
import java.util.List;
import javafx.scene.control.TableColumn;

/**
 *
 * @author capri
 */
public class TableQuery extends TableController<UserEntity>{
    
    private DefaultDao<UserEntity> tableUserDao;

    public TableQuery() {
        tableUserDao = new DefaultDao<>(UserEntity.class);
    }
    
    @Override
    public TableData<UserEntity> loadData(int i, List<TableCriteria> list, List<String> list1, List<TableColumn.SortType> list2, int i1) {
        List<UserEntity> listUser = tableUserDao.findAll();
        return new TableData<>(listUser, false, listUser.size());
    }

    @Override
    public void delete(List<UserEntity> records) {
        System.out.println(records.toString());
        super.delete(records); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    
}
