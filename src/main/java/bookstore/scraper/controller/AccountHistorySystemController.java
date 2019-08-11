package bookstore.scraper.controller;

import bookstore.scraper.historysystem.AccountHistory;
import bookstore.scraper.historysystem.HistorySystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountHistorySystemController {

    private final HistorySystemService historySystemService;

    @Autowired
    public AccountHistorySystemController(HistorySystemService historySystemService) {
        this.historySystemService = historySystemService;
    }

    @GetMapping("/history/{id}")
    public List<AccountHistory> getHistoryOfUser(@PathVariable int id) {
        return historySystemService.getHistoryOfUser(id);
    }
}
