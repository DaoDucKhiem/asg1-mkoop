package Dictionary;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

/**
 *
 * @author DUC KHIEM
 */
public class Dictionary {

    private String path;
    private TreeMap<String, String> data;
    private ArrayList<String> key;
    Scanner sc = new Scanner(System.in);

    public TreeMap<String, String> getData() {
        return data;
    }

    public ArrayList<String> getKey() {
        return key;
    }

    public void setKey(ArrayList<String> key) {
        this.key = key;
    }

    public void setData(TreeMap<String, String> data) {
        this.data = data;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Dictionary() {
        data = new TreeMap<>();
        key = new ArrayList();
    }

    public void readDataFromFile() {
        try (BufferedReader readfile = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = readfile.readLine()) != null) {
                int index = line.indexOf("<html>");

                if (index != -1) {
                    String target = line.substring(0, index);
                    String keyWord = target.trim();
                    String meaning = line.substring(index);

                    data.put(keyWord, meaning);
                    key.add(keyWord);
                }
            }
            readfile.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void writeData() {
        try (BufferedWriter writefile = new BufferedWriter(new FileWriter(path))) {
            // Lay mot tap hop cac entry
            Set set = data.entrySet();
            // Lay mot iterator
            Iterator i = set.iterator();
            // Hien thi cac phan tu
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

    int benarySearch(String word, int left, int right) {
        if (key.get(left).compareTo(key.get(right)) > 0) {
            return -1;
        } else {
            int mid = (left + right) / 2;
            if (key.get(mid).compareTo(key.get(left)) > 0) {
                return benarySearch(word, left, mid - 1);
            } else if (key.get(mid).compareTo(key.get(right)) < 0) {
                return benarySearch(word, mid + 1, right);
            } else {
                return mid;
            }
        }
    }

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
    
    public void searchWord(String Word) {
        if(Search(Word).equals("")) System.out.println("this word isn't in dictionary");
        else System.out.println(data.get(Word));
    }

    public void removeWord(String word) {
        if (Search(word).equals("")) {
            System.out.println("this word isn't in dictionary");
        } else {
            data.remove(word);
        }
    }

    public void replaceWord(String Word) {
        if (Search(Word).equals("")) {
            System.out.println("this word isn't in dictionary");
        } else {
            String meaning = data.get(Word);
            data.remove(Word);
            String newKey = sc.nextLine();
            data.put(newKey, meaning);
        }
    }

    public void replaceExlain(String Word) {
        if (Search(Word).equals("")) {
            System.out.println("this word isn't in dictionary");
        } else {
            System.out.print("nhap nghia: ");
            String explain = "<html>" + sc.nextLine() + "</html>";
            data.replace(Word, explain);
        }
    }

    public void addWord() {
        String newWord = sc.nextLine();
        if (Search(newWord).equals("")) {
            System.out.println("this word is exist in dictionary");
        } else {
            System.out.print("nhap nghia: ");
            String explain = "<html>" + sc.nextLine() + "</html>";
            data.put(newWord, explain);
        }
    }

    public void show() {
        Set set = data.entrySet();
        // Lay mot iterator
        Iterator i = set.iterator();
        // Hien thi cac phan tu
        while (i.hasNext()) {
            Map.Entry me = (Map.Entry) i.next();
            System.out.print(me.getKey() + " : ");
            System.out.println(me.getValue());
        }
    }
}
