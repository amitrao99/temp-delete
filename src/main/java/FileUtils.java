import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class FileUtils {
    public static void main(String[] args) {}

    public static byte[] readBytesFromFile(String filepath, Charset charset) throws IOException {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(filepath);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, charset);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            return stringBuilder.toString().getBytes(charset);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            if (fileInputStream != null) {
                fileInputStream.close();
            }
        }
    }

    public static String convertByteArrayToString(byte[] byteArray, Charset charset) {
        return new String(byteArray, charset);
    }

    public static boolean areFilesEqual(String filePath1, String filePath2) throws Exception {

        Path path1 = Paths.get(filePath1);
        Path path2 = Paths.get(filePath2);

        byte[] file1Bytes = Files.readAllBytes(path1);
        byte[] file2Bytes = Files.readAllBytes(path2);

        if (Arrays.equals(file1Bytes, file2Bytes)) {
            return true;
        } else {
            System.out.println("Files are different:");
            System.out.println("File 1:");
            System.out.println(new String(file1Bytes));
            System.out.println("File 2:");
            System.out.println(new String(file2Bytes));
            return false;
        }
    }
}
