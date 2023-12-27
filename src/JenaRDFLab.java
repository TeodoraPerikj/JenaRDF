import org.apache.jena.rdf.model.*;
import org.apache.jena.vocabulary.VCARD;

public class JenaRDFLab {

    public static void main(String[] args) {

        String personURI = "https://www.facebook.com/teodora.perik";
        String fullName = "Teodora Perikj";
        String givenName = "Teodora";
        String familyName = "Perikj";

        Model model = ModelFactory.createDefaultModel();

        Resource teodoraPerikj = model.createResource(personURI)
                .addProperty(VCARD.FN, fullName)
                .addProperty(VCARD.N, model.createResource()
                        .addProperty(VCARD.Given, givenName)
                        .addProperty(VCARD.Family, familyName))
                .addProperty(VCARD.NICKNAME, "Tea")
                .addProperty(VCARD.NICKNAME, "Tedi")
                .addProperty(VCARD.Street, "Ho Shi Min")
                .addProperty(VCARD.Region , "Macedonia")
                .addProperty(VCARD.Locality, "Skopje")
                .addProperty(VCARD.Country, "Macedonia");

        StmtIterator iterator = model.listStatements();

        if(iterator.hasNext()) {
            System.out.println("Printing with model.listStatements():");

            while (iterator.hasNext()) {
                Statement statement = iterator.nextStatement();

                Resource subject = statement.getSubject();
                Property predicate = statement.getPredicate();
                RDFNode object = statement.getObject();

                System.out.print(subject.toString());
                System.out.print(" - " + predicate.toString() + " - ");

                if (object instanceof Resource)
                    System.out.print(object.toString());
                else
                    System.out.print("\"" + object.toString() + "\"");

                System.out.println(".");
            }
        }

        System.out.println("Printing with model.print(), in RDF/XML:");
        model.write(System.out);
        System.out.println();

        System.out.println("Printing with model.print(), in Pretty RDF/XML:");
        model.write(System.out, "RDF/XML-ABBREV");
        System.out.println();

        System.out.println("Printing with model.print(), in N-Triples:");
        model.write(System.out, "N-TRIPLES");
        System.out.println();

        System.out.println("Printing with model.print(), in Turtle:");
        model.write(System.out, "TURTLE");
        System.out.println();
    }
}
