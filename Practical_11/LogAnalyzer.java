import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

public class LogAnalyzer {
    public static void main(String[] args) throws IOException {
        Path tempDir = Files.createTempDirectory("logs_sample_");
        Path log1 = tempDir.resolve("server.log");
        Path log2 = tempDir.resolve("app.log");

        Files.write(log1, List.of("INFO System started", "ERROR Disk warning", "INFO Heartbeat ok"));
        Files.write(log2, List.of("ERROR Out of memory", "DEBUG Initialized component", "ERROR Thread hung"));

        String keyword = "ERROR";
        int totalLines = 0;
        int keywordMatches = 0;

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(tempDir, "*.log")) {
            for (Path entry : stream) {
                BasicFileAttributes attrs = Files.readAttributes(entry, BasicFileAttributes.class);
                System.out.println("File: " + entry.getFileName() + " | Size: " + attrs.size() + " bytes");

                List<String> lines = Files.readAllLines(entry);
                totalLines += lines.size();
                for (String line : lines) {
                    if (line.contains(keyword)) {
                        keywordMatches++;
                    }
                }
            }
        }

        System.out.println("Total lines scanned across files: " + totalLines);
        System.out.println("Lines containing '" + keyword + "': " + keywordMatches);

        // Cleanup
        Files.deleteIfExists(log1);
        Files.deleteIfExists(log2);
        Files.deleteIfExists(tempDir);
    }
}
