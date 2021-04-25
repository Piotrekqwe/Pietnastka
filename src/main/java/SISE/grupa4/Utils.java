package SISE.grupa4;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.Arrays;

public class Utils {
    private Utils() {
    }

    public static String readFromFile(String filePath) throws IOException {
        return Files.readString(Paths.get(filePath));
    }

    public static int[] toIntArray(String data) {
        String str = data.replace(System.getProperty("line.separator"), " ");
        return Arrays.stream(str.split(" ")).mapToInt(Integer::parseInt).toArray();
    }


    public static Node getData(String filePath) {
        String str = "";
        try {
            str = readFromFile(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        int[] arr = toIntArray(str);
        int[] configuration = Arrays.copyOfRange(arr, 2, arr.length);

        return new Node(configuration, arr[1], PuzzleService.getPosition(configuration), "", 0);
    }

    private static final DecimalFormat df3 = new DecimalFormat("#.###");
    public static void saveResult(Metadata metadata, String resultFile, String metadataFile) {
        try {
            Files.write(Path.of(resultFile), (
                    metadata.node.getDepth() + "\n"
                    + metadata.node.getPath()
            ).getBytes(StandardCharsets.UTF_8));

            Files.write(Path.of(metadataFile), (
                    metadata.node.getDepth() + "\n"
                    + metadata.metStates + "\n"
                    + metadata.processedStates + "\n"
                    + metadata.maxDepth + "\n"
                    + df3.format(metadata.time)
            ).getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
