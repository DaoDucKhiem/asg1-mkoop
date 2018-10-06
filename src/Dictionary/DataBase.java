package Dictionary;

import java.util.Collections;

/**
 *
 * @author DUC KHIEM
 */
public class DataBase {
    Dictionary dic = new Dictionary();
    String nameFile;

    public DataBase() {
        setData();      
    }
      
    private void setData() {
        dic.setPath("E_V.txt");
        dic.readDataFromFile();
    }
    
    public Dictionary getData() {
        return dic;
        
    }
    public void updateData() {
        Collections.sort(dic.getKey());
        dic.writeData();
    }
    
}
