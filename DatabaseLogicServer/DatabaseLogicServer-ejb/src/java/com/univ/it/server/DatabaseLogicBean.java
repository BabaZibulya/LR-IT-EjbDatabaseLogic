package com.univ.it.server;

import com.univ.it.table.LocalDataBase;
import com.univ.it.table.Row;
import com.univ.it.table.Table;
import java.io.FileNotFoundException;
import java.util.HashMap;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Startup
@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
public class DatabaseLogicBean implements DatabaseLogicBeanRemote {
    
    private LocalDataBase db;
    private final String PATH_TO_DB = "/home/bondarenko";
    private final String DB_NAME = "test.db";

    public DatabaseLogicBean() throws Exception {
        db = LocalDataBase.readFromFile(PATH_TO_DB + "/" + DB_NAME);
    }
    
    @Override
    @Lock(LockType.WRITE)
    public boolean addTable(Table table) {
        return db.addTable(table);
    }

    @Override
    @Lock(LockType.WRITE)
    public boolean dropTable(String tableName) {
        return db.dropTable(tableName);
    }

    @Override
    @Lock(LockType.WRITE)
    public void addRow(String tableName, Row newRow) throws Exception {
        try {
            db.getTable(tableName).addNewRow(newRow);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Lock(LockType.READ)
    public HashMap<String, Table> getTables() {
        return db.getTables();
    }

    @Override
    @Lock(LockType.READ)
    public Table getTable(String tableName) {
        return db.getTable(tableName);
    }

    @Override
    @Lock(LockType.WRITE)
    public void writeToFile(String pathToFile) throws Exception {
        try {
            db.writeToFile(PATH_TO_DB);
        } catch (FileNotFoundException e) {
            throw new Exception(e.getMessage());
        }
    }
}
