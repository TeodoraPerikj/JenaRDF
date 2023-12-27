import org.apache.jena.rdf.model.*;
import org.apache.jena.util.FileManager;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.RDFS;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class JenaRDFLab_Part3 {

    static final String inputFileName = "C:\\Users\\IGOR\\Desktop\\hifm-dataset.ttl";

    public static void main(String[] args) {


        Model model = ModelFactory.createDefaultModel();

        InputStream in = FileManager.get().open(inputFileName);

        if(in == null)
            throw new IllegalArgumentException("File: " + inputFileName + " not found.");

        model.read(in, "" , "TURTLE");

        StmtIterator iterator = model.listStatements(new SimpleSelector(null, RDFS.label, (RDFNode) null));

        if(iterator.hasNext()){
           System.out.println("Print Names of Drugs:");

            List<String> list = new ArrayList();

            while (iterator.hasNext()){

                list.add(iterator.nextStatement().getObject().toString());
            }

            Collections.sort(list, Comparator.naturalOrder());

            for(String name : list)
                System.out.println(name);
        }

        ResIterator resIterator =  model.listSubjects();

        if(resIterator.hasNext()){
            System.out.println("Print Drugs:");

            while (resIterator.hasNext()) {

                System.out.println(" " + resIterator.nextResource().toString());
            }
        }

        String chosenDrug = "http://purl.org/net/hifm/data#968951";

        Resource drug = model.getResource(chosenDrug);

        StmtIterator iterator1 = model.listStatements(new SimpleSelector(drug, null, (RDFNode) null));

        if(iterator1.hasNext()){
            System.out.println("Print All Relations and Values:");
            System.out.println("Subject " + drug.toString());

            while (iterator1.hasNext()){

                Statement statement = iterator1.nextStatement();
                Property predicate = statement.getPredicate();
                RDFNode object = statement.getObject();

                System.out.println("Property " + predicate.toString());
                System.out.println("Object " + object.toString());

            }
        }

        Property property = model.getProperty("http://purl.org/net/hifm/ontology#similarTo");

        StmtIterator it = model.listStatements(new SimpleSelector(drug, property, (RDFNode) null));

        if(it.hasNext()){
            System.out.println("Print Names of Drugs:");

            List<String> list = new ArrayList();

            while (it.hasNext()){

                list.add(it.nextStatement().getObject().toString());
            }

            Collections.sort(list, Comparator.naturalOrder());

            for(String name : list)
                System.out.println("Name: " + name);
        }

        Property property1 = model.getProperty("http://purl.org/net/hifm/ontology#refPriceWithVAT");

       // model.getResource()

        System.out.println("The price is: " + drug.getProperty(property1).getObject().toString());

        StmtIterator it1 = model.listStatements(new SimpleSelector(null, property1, (RDFNode) null));

        if(it1.hasNext()){
            System.out.println("Print Names of Other Drugs:");

            List<String> namesAndValues = new ArrayList();

            while (it1.hasNext()){

                Statement statement = it1.nextStatement();

                namesAndValues.add(statement.getSubject().toString() + " " + statement.getObject().toString());
            }

            Collections.sort(namesAndValues, Comparator.naturalOrder());

            for(String nameAndValue : namesAndValues)
                System.out.println("Drug name and value: " + nameAndValue);
        }
    }
}
