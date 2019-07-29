package bookstore.scraper.urlproperties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties("external.library.url")
public class EmpikUrlProperties {

    private Empik empik = new Empik();

    @Getter
    @Setter
    public static class Empik {

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