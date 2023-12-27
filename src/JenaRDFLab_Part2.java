import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.util.FileManager;
import org.apache.jena.vocabulary.VCARD;

import java.io.InputStream;

public class JenaRDFLab_Part2 {

    static final String inputFileName = " C:\\Users\\IGOR\\Desktop\\WBS\\Laboratoriska1\\lab1.ttl";


    public static void main(String[] args) {

        String personURI = "https://www.facebook.com/teodora.perik";

        Model model = ModelFactory.createDefaultModel();

        InputStream in = FileManager.get().open(inputFileName);

        if(in == null)
            throw new IllegalArgumentException("File: " + inputFileName + " not found.");

        model.read(in, "" , "TURTLE");

        model.write(System.out, "TURTLE");

        Resource teodoraPerikj = model.getResource(personURI);

        String fullName = teodoraPerikj.getProperty(VCARD.FN).getString();
        String country = teodoraPerikj.getProperty(VCARD.Country).getString();
        String locality = teodoraPerikj.getProperty(VCARD.Locality).getString();

        System.out.println(fullName);
        System.out.println(country);
        System.out.println(locality);
    }
}
