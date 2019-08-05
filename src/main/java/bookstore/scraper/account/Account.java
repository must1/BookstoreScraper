package bookstore.scraper.account;

import lombok.*;


import javax.persistence.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nickname;
    private String password;

    public static class AccountBuilder {
        private String nickname;
        private String password;

        public AccountBuilder withNickName(String nickname) {
            this.nickname = nickname;
            return this;
        }

        public AccountBuilder withPassword(String password) {
            this.password = password;
            return this;
        }

        public Account build() {
            Account account = new Account();
            account.password = this.password;
            account.nickname = this.nickname;
            return account;
        }
    }
}
