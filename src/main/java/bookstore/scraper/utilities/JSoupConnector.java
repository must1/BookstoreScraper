package bookstore.scraper.utilities;

import lombok.experimental.UtilityClass;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

@UtilityClass
public class JSoupConnector {

    public static Document connect(String url) {
        try {
            return Jsoup.connect(url).get();
        } catch (IOException e) {
            throw new IllegalArgumentException("Cannot connect to" + url);
        }
    }
}
