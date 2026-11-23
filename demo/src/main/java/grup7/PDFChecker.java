package grup7;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;

public class PDFChecker {

    public static boolean comprovarPassword(String ruta, String password) {
        try (PDDocument document = Loader.loadPDF(new File(ruta), password)) {
            return true;
        } catch (InvalidPasswordException e) {
            return false;
        } catch (IOException e) {
            System.out.println("Error en obrir el PDF '" + ruta + "': " + e.getMessage());
            return false;
        }
    }
}
