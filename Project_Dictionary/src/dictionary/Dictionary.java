package dictionary;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;

/**
 *
 * @author DUC KHIEM
 */
public class Dictionary {

    private String path; //path đọc file
    private TreeMap<String, String> datamap;
    private ArrayList<String> key;

    public TreeMap<String, String> getMap() {
        return datamap;
    }

    public ArrayList<String> getKey() {
        return key;
    }

    public void setKey(ArrayList<String> key) {
        this.key = key;
    }

    public void setMap(TreeMap<String, String> data) {
        this.datamap = data;
    }

    public void setPath(String path) {
        this.path = path;
    }

    /**
     * khi gọi dictionary thì sẽ tạo 1 list để lưu dữ keyword và 1 map để lưu
     * key và nghĩa
     */
    public Dictionary() {
        datamap = new TreeMap<>();
        key = new ArrayList<>();
    }

    /**
     * hàm đọc dữ liệu từ file sử dụng substring để tách phần từ và nghĩa theo
     * cấu trúc của file
     */
    public void readDataFromFile() {
        try {
            File file = new File(path);
            try (BufferedReader readfile = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"))) {
                String line;
                while ((line = readfile.readLine()) != null) {
                    //indexOf(String) trả về vị trí của chuỗi String trong chuỗi hiện thời. -1 nếu không tồn tại chuỗi
                    int index = line.indexOf("<html>");
                    
                    if (index != -1) {
                        String target = line.substring(0, index);
                        String keyWord = target.trim(); // bỏ qua các ký tự khoảng trắng ở đầu và cuỗi chuỗi
                        String meaning = line.substring(index);
                        
                        //thêm từ vào treemap
                        datamap.put(keyWord, meaning);
                        //thêm từ vào arraylist
                        key.add(keyWord);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * hàm ghi dữ liệu ra file duyệt treemap để ghi key và value ra file theo
     * cấu trúc cũ của file
     */
    public void writeData() {
        File file = new File(path);
        try (BufferedWriter writefile = new BufferedWriter( new OutputStreamWriter(new FileOutputStream(file), "UTF8") )) {
            // lấy một tập hợp các ánh xạ entry
            Set set = datamap.entrySet();
            // lấy một iterator
            Iterator i = set.iterator();
            // duyệt treemap bằng iterator
            while (i.hasNext()) {
                Map.Entry me = (Map.Entry) i.next();
                writefile.write((String) me.getKey());
                writefile.write((String) me.getValue());
                writefile.newLine();
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    /**
     * tìm kiếm nhị phân dùng trong các hàm tìm kiếm keyWord
     *
     * @param word
     * @param left
     * @param right
     * @return vị trí của từ trong arraylist hoặc -1 nếu không có từ đó
     */
    int benarySearch(String word, int left, int right) {
        if (key.get(left).compareTo(key.get(right)) > 0) {
            return -1;
        } else {
            int mid = (left + right) / 2;
            if (key.get(mid).compareTo(word) > 0) {
                return benarySearch(word, left, mid - 1);
            } else if (key.get(mid).compareTo(word) < 0) {
                return benarySearch(word, mid + 1, right);
            } else {
                return mid;
            }
        }
    }

    /**
     * dùng trong các hàm remove, add và replace
     *
     * @param word
     * @return từ cần tim nếu có không thì null
     */
    public String Search(String word) {
        int high = key.size() - 1;
        int low = 0;
        int index = benarySearch(word, low, high);
        if (index < 0) {
            return "";
        } else {
            return key.get(index);
        }
    }

    /*
    * hàm tìm kiếm từ trực tiếp từ map không thông qua arraylist sử dụng trong search ở controller
    * @param word
    *return 1 chuỗi string là nghĩa hoặc là thông báo không tồn tại
     */
    public String SearchInMap(String word) {
        String html = datamap.get(word);
        if (html == null) {
            html = "<html>this word didn't exist in dictionary</html>";
        }
        return html;
    }

    /**
     * random từ để hiển thị trên webview khi run
     *
     * @return từ random
     */
    public String WordRandom() {
        Random random = new Random();
        int index = random.nextInt(key.size() - 1);
        return key.get(index);
    }

    /**
     * xóa từ khỏi file
     *
     * @param word
     * @return đúng nếu xóa được, sai nếu từ đó không có trong danh sách
     */
    public boolean removeWord(String word) {
        if (Search(word).equals("")) {
            return false;
        } else {
            key.remove(word);
            datamap.remove(word);
            return true;
        }
    }

    /**
     * hàm thay đổi từ
     *
     * @param Word
     * @param newKey
     * @return đúng nếu sửa được sai nếu từ đó không có trong từ điển
     */
    public boolean replaceWord(String Word, String newKey) {
        if (Search(Word).equals("")) {
            return false;
        } else {
            String meaning = datamap.get(Word);
            key.remove(Word); // nếu lấy được vị trí thì nên dùng replace
            datamap.remove(Word);
            key.add(newKey);
            datamap.put(newKey, meaning);
            return true;
        }
    }

    /**
     * hàm sửa nghĩa của từ
     *
     * @param Word
     * @param newExplain
     * @return đúng nếu sửa được sai nếu từ đó không có trong từ điển
     */
    public boolean replaceExplain(String Word, String newExplain) {
        if (Search(Word).equals("")) {
            return false;
        } else {
            datamap.replace(Word, newExplain);
            return true;
        }
    }

    /**
     * hàm này là ghép nghĩa theo đúng cấu trúc của file
     *
     * @param noun
     * @param verb
     * @param adjective
     * @param other
     * @return chuỗi string sau khi ghép
     */
    public String setExlain(String noun, String verb, String adjective, String other) {
        if (!noun.equals("")) {
            noun = "<ul><li><b><i> danh từ</i></b><ul><li><font color='#cc0000'><b>" + noun + "</b></font></li></ul></li></ul>";
        }
        if (!verb.equals("")) {
            verb = "<ul><li><b><i> động từ</i></b><ul><li><font color='#cc0000'><b>" + verb + "</b></font></li></ul></li></ul>";
        }
        if (!adjective.equals("")) {
            adjective = "<ul><li><b><i> tính từ</i></b><ul><li><font color='#cc0000'><b>" + adjective + "</b></font><ul></li></ul></ul></li></ul>";
        }
        if (!other.equals("")) {
            other = "<ul><li><b><i> khác</i></b><ul><li><font color='#cc0000'><b>" + other + "</b></font></li></ul></li></ul>";
        }
        return noun + verb + adjective + other;
    }

    /**
     * hàm thêm từ vào list và map
     *
     * @param newWord
     * @param explain
     * @return đúng nếu thêm được sai nếu từ đó đã tồn tại trong từ điển
     */
    public boolean AddWord(String newWord, String explain) {
        if (!Search(newWord).equals("")) {
            return false;
        } else {
            key.add(newWord);
            datamap.put(newWord, "<html>" + explain + "</html>");
            return true;
        }
    }

    /**
     * hàm hiển thị danh sách từ
     */
    public void show() {
        //lấy các ánh xạ entry
        Set set = datamap.entrySet();
        // lấy một iterator
        Iterator i = set.iterator();
        // duyệt treemap để in các phần tử
        while (i.hasNext()) {
            Map.Entry me = (Map.Entry) i.next();
            System.out.print(me.getKey() + " : ");
            System.out.println(me.getValue());
        }
    }
}
