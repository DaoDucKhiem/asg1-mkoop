package dictionary;

import java.util.Collections;

/**
 *
 * @author DUC KHIEM
 */
public class DataBase {
    Dictionary dic = new Dictionary();
    String nameFile;

    public DataBase() {
        setDataBase();      
    }
      
    private void setDataBase() {
        dic.setPath("D:\\Project_Dictionary\\data\\E_V.txt");
        dic.readDataFromFile();
    }
    
    public Dictionary getDataBase() {
        return dic;
        
    }
    public void updateDataBase() {
        Collections.sort(dic.getKey());
        dic.writeData();
    }
    
}
