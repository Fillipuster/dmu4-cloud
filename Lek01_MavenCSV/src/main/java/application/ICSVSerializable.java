package application;

public interface ICSVSerializable<T> {
    public String GetCSV();
    public void LoadCSV(String csv);
}
