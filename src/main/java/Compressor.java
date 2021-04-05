import lombok.AllArgsConstructor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@AllArgsConstructor
class Compressor {
    private final CompressionStrategy compressionStrategy;

    void compress(String inputFile, File outputFile) throws IOException {
        try (OutputStream outputStream = new FileOutputStream(outputFile)) {
            OutputStream finalOutputStream = compressionStrategy.compress(outputStream);
            if (finalOutputStream instanceof ZipOutputStream) {
                ((ZipOutputStream) finalOutputStream).putNextEntry(new ZipEntry(inputFile));
            }
            Files.copy(Paths.get(inputFile), finalOutputStream);
            finalOutputStream.close();
        }
    }
}

