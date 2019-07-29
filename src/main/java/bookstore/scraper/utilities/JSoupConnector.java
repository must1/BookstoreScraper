package bookstore.scraper.utilities;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

@Slf4j
@UtilityClass
public class JSoupConnector {

    public static Document connect(String url) {
        Document document = null;
        try {
            document = Jsoup.connect(url).get();
        } catch (IOException e) {
            log.warn("Cannot connect to" + url);
        }
        return document;
    }
}
