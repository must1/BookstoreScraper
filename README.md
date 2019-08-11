# BookstoreScraper

[![Build Status](https://travis-ci.org/must1/BookstoreScraper.svg?branch=master)](https://travis-ci.org/must1/BookstoreScraper)[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=com.bookstore.scraper%3ABookstoreScraper&metric=sqale_rating)](https://sonarcloud.io/dashboard?id=com.bookstore.scraper%3ABookstoreScraper)

BookstoreScraper is application based on Spring Boot.
It allows to scrap data from two biggest polish online bookstores - EMPIK and MERLIN. You can scrap books within 3 options:
- bestsellers
- most precise book (you simply give title and it looks for most precise book)
- categorized book (currently 5 categories of book are available:  BIOGRAPHY, CRIME, GUIDES, FANTASY, ROMANCES)

There is ranking option. It is comparing books from each bookstore and if title repeats the book is higher in the ranking.

There is provided simply security so if you want to use the application you have to create account and each action is saved into DB withtin your account ID.

Application uses:
- Java
- Maven
- Spring Boot
- Spring Data
- Spring Security
- Mockito
- JUnit
- SonarCloud (according to SonarCloud ~70 % code coverage)
- Travis (for CI)
- PostgresSQL
- Lombok
