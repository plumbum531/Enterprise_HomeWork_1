import java.io.File;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipOutputStream;

class Main {
    public static void main(String[] args) {
        if (args.length == 2) {
            new Main().application(args);
        } else {
            System.out.println("Incorrect number of arguments");
        }
    }

    void application(String[] args) {
        try {
            String fileName = args[1];
            String compressType = args[0];

            System.out.println("Compress type: " + args[0]);
            if (compressType.equals("zip")) {
                File outFile = new File(fileName.replaceFirst("[.][^.]+$", "").concat(".zip"));
                Compressor zipCompressor = new Compressor(ZipOutputStream::new);
                zipCompressor.compress(fileName, outFile);
            } else if (compressType.equals("gzip")) {
                File outFile = new File(fileName.concat(".gz"));
                Compressor gzipCompressor = new Compressor(GZIPOutputStream::new);
                gzipCompressor.compress(fileName, outFile);
            } else {
                System.out.println("Unknown format");
            }
        } catch (IOException e) {
            System.out.println("IO Exception " + e.getLocalizedMessage());
        }
    }
}

