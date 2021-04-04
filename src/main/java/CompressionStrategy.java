import java.io.IOException;
import java.io.OutputStream;

interface CompressionStrategy {
    OutputStream compress(OutputStream data) throws IOException;
}
