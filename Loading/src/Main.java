import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;


public class Main {
    public static final String pathToZip = "//home//kizilovms//IdeaProjects//Netology Cod//Java Core//Install//Games//savegames//zip.zip";
    public static final String pathToUnZip = "//home//kizilovms//IdeaProjects//Netology Cod//Java Core//Install//Games//savegames//";
    GameProgress gameProgress = null;

    public static void main(String[] args) {
        openZip(pathToZip, pathToUnZip);
        System.out.println(openProgress(pathToUnZip).toString());

    }

    private static void openZip(String pathToFile, String pathToDir) {
        try (ZipInputStream zin = new ZipInputStream(new FileInputStream(pathToFile))) {
            ZipEntry zipEntry;
            String name;
            while ((zipEntry = zin.getNextEntry()) != null) {
                name = zipEntry.getName();
                FileOutputStream fout = new FileOutputStream(pathToUnZip + name);
                for (int c = zin.read(); c != -1; c = zin.read()) {
                    fout.write(c);
                }
                fout.flush();
                zin.closeEntry();
                fout.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static GameProgress openProgress(String pathSave) {
        try (FileInputStream fis = new FileInputStream(pathSave + "save3.dat");
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            return (GameProgress) ois.readObject();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
