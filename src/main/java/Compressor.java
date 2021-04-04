import lombok.AllArgsConstructor;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@AllArgsConstructor
class Compressor {
    private final CompressionStrategy compressionStrategy;

    void compress(Path inputFile, File outputFile) throws IOException {
        try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(outputFile));
             InputStream inputStream = new BufferedInputStream(new FileInputStream(inputFile.toString()))) {
            OutputStream finalOutputStream = compressionStrategy.compress(outputStream);
            if (finalOutputStream instanceof ZipOutputStream) {
                ((ZipOutputStream) finalOutputStream).putNextEntry(new ZipEntry(inputFile.toString()));
            }
            Files.copy(inputFile, finalOutputStream);
            //inputStream.transferTo(finalOutputStream);
            finalOutputStream.close();
        }
    }
}

