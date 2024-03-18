import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {

    private static final String PATHSAVEGAMES = "//home//kizilovms//IdeaProjects//Netology Cod//Java Core//Install//Games//savegames//";

    public static void main(String[] args) {

        GameProgress gameProgress1 = new GameProgress(1, 1, 1, 1.0);
        GameProgress gameProgress2 = new GameProgress(2, 2, 2, 2.0);
        GameProgress gameProgress3 = new GameProgress(3, 3, 3, 3.0);
        saveGame(PATHSAVEGAMES + "save1.dat", gameProgress1);
        saveGame(PATHSAVEGAMES + "save2.dat", gameProgress2);
        saveGame(PATHSAVEGAMES + "save3.dat", gameProgress3);
        zipFiles(PATHSAVEGAMES + "zip.zip", new File(PATHSAVEGAMES).listFiles());
    }

    static void saveGame(String path, GameProgress gameProgress) {

        try (FileOutputStream fos = new FileOutputStream(path);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
// запишем экземпляр класса в файл
            oos.writeObject(gameProgress);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    static void zipFiles(String path, File[] files) {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(path))) {
            for (File f : files) {
                FileInputStream fis = new FileInputStream(f);
                ZipEntry entry = new ZipEntry(f.toString().substring(f.toString().lastIndexOf('s')));
                zout.putNextEntry(entry);
                byte[] buffer = new byte[fis.available()];
                fis.read(buffer);
                zout.write(buffer);
                zout.closeEntry();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    static void rmdir() {
        File dir = new File(PATHSAVEGAMES);
        if (dir.isDirectory()) {
            for (File rm : dir.listFiles()) {
                if (!rm.getName().equals("zip.zip")) {
                    rm.delete();
                }
            }
        }
    }
}
