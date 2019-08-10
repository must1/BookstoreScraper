package bookstore.scraper.historysystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HistorySystemController {

    private final HistorySystemService historySystemService;

    @Autowired
    public HistorySystemController(HistorySystemService historySystemService) {
        this.historySystemService = historySystemService;
    }

    @GetMapping("/history/{id}")
    public List<AccountHistory> getHistoryOfUser(@PathVariable int id) {
        return historySystemService.getHistoryOfUser(id);
    }
}
