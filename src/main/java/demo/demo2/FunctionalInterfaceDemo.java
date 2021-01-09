package demo.demo2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.htmlcleaner.HtmlCleaner;

/**
 * Demonstrates:
 *
 *  Function & Predicate (including Chaining & Compose)
 *  Bi-Function as an alternate to Predicate
 *
 *  Consumer & Supplier
 *  Show API too (including primitive functional interfaces) !!
 *
 *  Method References
 *  TODO: Constructor References
 *
 */
public class FunctionalInterfaceDemo {

    public static void main(String[] args) {

        String doc1 = "<html><body>One of the most common uses of <i>streams</i> is to represent queries over data in collections</body></html>";
        String doc2 = "<html><body>Information integration systems provide valuable services to users by integrating information from a number of autonomous, heterogeneous and distributed Web sources</body></html>";
        String doc3 = "<html><body>Solr is the popular, blazing fast open source enterprise search platform from the Apache Lucene</body></html>";
        String doc4 = "<html><body>Java 8 goes one more step ahead and has developed a streams API which lets us think about parallelism</body></html>";

        List<String> documents = new ArrayList<>(Arrays.asList(doc1, doc2, doc3, doc4));

        List<String> targetDocuments = new ArrayList<>();

        for (String doc : documents) {
            //boolean isTargetDoc = filter(doc, d -> d.contains("stream"));

            //BiFunction<String, String, Boolean> biFunction = (d, c) -> d.contains(c);
            // (iii) Method References (ClassName::instanceMethod)
            Function<String, Boolean> function = doc::contains;

            BiFunction<String, String, Boolean> biFunction = String::contains;

            //if (biFunction.apply(doc, "stream")) {
            if (function.apply("stream")) {
                //doc = transform(doc, d -> Indexer.stripHtmlTags(d));
                //doc = transform(doc, d -> Indexer.removeStopwords(d));

                Function<String, String> htmlCleaner = d -> Indexer.stripHtmlTags(d);
                doc = transform(doc, htmlCleaner);

                //Function<String, String> stopwordRemover = d -> Indexer.removeStopwords(d);
                //doc = stopwordRemover.apply(doc);

                // (i) Method References (ClassName::staticMethod)
                Function<String, String> stopwordRemover = Indexer::removeStopwords;
                doc = stopwordRemover.apply(doc);
                // Note: Type of Method reference should be appropriate for the abstract method in fn'al interface

                // Function<String, String> docProcessor = htmlCleaner.andThen(stopwordRemover);
                // doc = transform(doc, docProcessor);

                //System.out.println(doc);

                targetDocuments.add(doc);
            }

        }

        //targetDocuments.forEach(d -> System.out.println(d));

        // (ii) Method References (objectRef::instanceMethod)
        targetDocuments.forEach(System.out::println);

        for (String doc : targetDocuments) {
            try {
                if (doc.length() > 80) {
                    throw new Exception("Oversized document!!!");
                }
            } catch (Exception e) {
                print(() -> e.getMessage() + " ~ " + doc);
            }
        }

    }

    private static boolean errorFlag = true;
    private static void print(Supplier<String> supplier) {
        if (errorFlag) {
            System.out.println(supplier.get());
        }
    }

    static boolean filter(String doc, Predicate<String> filter) {
        return filter.test(doc);
    }

    /*static String transform(String doc, UnaryOperator<String> transformer) {
        return transformer.apply(doc);
    }*/
    static String transform(String doc, Function<String, String> transformer) {
        return transformer.apply(doc);
    }

}

class Indexer {

    private static List<String> stopWords = Arrays.asList("of", "the", "a", "is", "to", "in", "and");

    static String stripHtmlTags(String doc) {
        return new HtmlCleaner().clean(doc).getText().toString();
    }

    static String removeStopwords(String doc) {

        StringBuilder sb = new StringBuilder();
        for (String word : doc.split(" ")) {
            if (!stopWords.contains(word))
                sb.append(word).append(" ");
        }

        return sb.toString();
    }

}

