package com.univ.it.server;

import com.univ.it.table.Row;
import com.univ.it.table.Table;
import java.util.HashMap;
import javax.ejb.Remote;

@Remote
public interface DatabaseLogicBeanRemote {
    boolean addTable(Table table) throws Exception;
    boolean dropTable(String tableName) throws Exception;
    void addRow(String tableName, Row newRow) throws Exception;
    HashMap<String, Table> getTables() throws Exception;
    Table getTable(String tableName) throws Exception;
    void writeToFile(String pathToFile) throws Exception;
}
