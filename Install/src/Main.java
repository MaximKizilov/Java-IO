import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static final String pathToGames = "//home//kizilovms//IdeaProjects//Netology Cod//Java Core//Install//Games//";
    static StringBuilder sb = new StringBuilder();

    static void touch(String fileName) throws IOException {
        File file = new File(pathToGames + fileName);
        sb.append(file.createNewFile() ? "Файл " + fileName + " создан" : "Ошибка создания файла " + fileName).append("\n");
    }

    static void mkdir(String dirName) {
        File dir = new File(pathToGames + dirName);
        sb.append(dir.mkdir() ? "Каталог " + dirName + " создан" : "Ошибка создания директории " + dirName).append("\n");

    }

    public static void main(String[] args) {

        List<String> dir = List.of("src", "res", "savegames", "temp", "src//main", "src//test", "res//drawables", "res//vectors", "res//icons");
        List<String> file = Arrays.asList("temp//temp.txt", "src//main//Main.java", "src//main//Utils.java");
        dir.forEach(Main::mkdir);
        file.forEach(fileName -> {
            try {
                touch(fileName);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        try (FileWriter writer = new FileWriter(pathToGames + "temp//temp.txt")) {
            writer.write(String.valueOf(sb));
            writer.flush();
        } catch (IOException ex) {
            sb.append(ex.getMessage()).append("\n");
        }
    }
}