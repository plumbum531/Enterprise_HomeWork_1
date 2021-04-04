import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipOutputStream;

class Main {
    public static void main(String[] args) {
        new Main().application(args);
    }

    void application(String[] args) {
        try {
            System.out.println("Compress type " + args[0]);
            Path inputFileName = Paths.get(args[1]);

            if (args[0].equals("zip")) {
                String fileName = inputFileName.getFileName().toString().
                        replaceFirst("[.][^.]+$", "").concat(".zip");
                File outFile = new File(fileName);
                Compressor zipCompressor = new Compressor(ZipOutputStream::new);
                zipCompressor.compress(inputFileName, outFile);
            } else if (args[0].equals("gzip")) {
                File outFile = new File(inputFileName.getFileName().toString().concat(".gz"));
                Compressor gzipCompressor = new Compressor(GZIPOutputStream::new);
                gzipCompressor.compress(inputFileName, outFile);
            } else {
                System.out.println("Unknown format");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

