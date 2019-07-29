package bookstore.scraper.urlproperties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties("external.library.url")
public class MerlinUrlProperties {

    private Merlin merlin = new Merlin();

    @Getter
    @Setter
    public static class Merlin {

        private String mostPreciseBook;
        private String bestSellers;
        private String concreteBook;
        private String romances;
        private String biographies;
        private String crime;
        private String guides;
        private String fantasy;
    }
}