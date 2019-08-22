package application;

import java.io.*;
import java.util.Collection;

public class CSVFile<T extends ICSVSerializable> {

    private String fileName;

    public CSVFile(String fileName) {
        this.fileName = fileName;
    }

    public boolean write(T obj) {
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            fileWriter.write(obj.GetCSV());
            fileWriter.close();

            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public boolean read(Collection<T> collection) {
        collection.clear();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine();
            while (line != null) {
                //collection.add(new T())
                line = reader.readLine();
            }

            return true;
        } catch (IOException e) {
            return false;
        }
    }

}
