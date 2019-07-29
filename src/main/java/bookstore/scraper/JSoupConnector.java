package bookstore.scraper;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

@Slf4j
public class JSoupConnector {

    public static Document connect(String URL) {
        Document document = null;
        try {
            document = Jsoup.connect(URL).get();
        } catch (IOException e) {
            log.warn("Cannot connect to URL!");
        }
        return document;
    }
}
