package bookstore.scraper.urlproperties;

import bookstore.scraper.enums.CategoryType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Getter
@Setter
@Component
@ConfigurationProperties("external.library.url.merlin")
public class MerlinUrlProperties implements CategoryProperties {

    private String mostPreciseBook;
    private String bestSellers;
    private String concreteBook;
    private String romances;
    private String biographies;
    private String crime;
    private String guides;
    private String fantasy;

    public String getCategory(CategoryType categoryType) {

        switch (Objects.requireNonNull(categoryType)) {
            case CRIME:
                return getCrime();
            case BESTSELLER:
                return getBestSellers();
            case BIOGRAPHY:
                return getBiographies();
            case FANTASY:
                return getFantasy();
            case GUIDES:
                return getGuides();
            case MOST_PRECISE_BOOK:
                return getMostPreciseBook();
            case ROMANCES:
                return getRomances();
            default:
                throw new IllegalArgumentException("Unexpected categoryType: " + categoryType);
        }
    }
}