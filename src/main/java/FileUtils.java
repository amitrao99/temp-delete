import java.io.*;
import java.nio.charset.Charset;

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
}
