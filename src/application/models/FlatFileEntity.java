package application.models;

public interface FlatFileEntity {
    public String getKey();
    public void fromCSV(String text);
    public String toCSV();
}
