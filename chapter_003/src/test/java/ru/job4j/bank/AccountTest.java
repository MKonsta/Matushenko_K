package ru.job4j.bank;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

import java.util.*;

public class AccountTest {
    @Test
    public void testAddUser() {
        Map<User, List<Account>> expect = new HashMap<>();
        User user = new User("Petrov", "25678");
        List<Account> list = new ArrayList<>();
        expect.put(user, list);
        Execute result = new Execute();
        result.addUser(new User("Petrov", "25678"));
        assertThat(expect, is(result.users));
    }

    @Test
    public void testDeleteUser() {
        Map<User, List<Account>> expect = new HashMap<>();
        User user = new User("Petrov", "25678");
        List<Account> list = new ArrayList<>();
        expect.put(user, list);
        Execute result = new Execute();
        result.addUser(new User("Petrov", "25678"));
        result.addUser(new User("Smirnov", "67805"));
        result.deleteUser(new User("Smirnov", "67805"));
        assertThat(expect, is(result.users));
    }

    @Test
    public void testAddAccountToUser() {
        Map<User, List<Account>> expect = new HashMap<>();
        User user = new User("Petrov", "25678");
        List<Account> list = new ArrayList<>();
        list.add(new Account(345.7, "2346"));
        expect.put(user, list);
        Execute result = new Execute();
        result.addUser(new User("Petrov", "25678"));
        result.addAccountToUser("25678", new Account(345.7, "2346"));
        assertThat(expect, is(result.users));
    }

    @Test
    public void testDeleteAccountFromUser() {
        Map<User, List<Account>> expect = new HashMap<>();
        User user = new User("Petrov", "25678");
        List<Account> list = new ArrayList<>();
        list.add(new Account(345.7, "2346"));
        expect.put(user, list);
        Execute result = new Execute();
        result.addUser(new User("Petrov", "25678"));
        result.addAccountToUser("25678", new Account(1000, "7295"));
        result.addAccountToUser("25678", new Account(345.7, "2346"));
        result.deleteAccountFromUser("25678", new Account(1000, "7295"));
        assertThat(expect, is(result.users));
    }

    @Test
    public void testGetUserAccounts() {
        List<Account> expect = new ArrayList<>();
        expect.addAll(Arrays.asList(new Account(534, "729"), new Account(766, "324")));
        Execute result = new Execute();
        result.addUser(new User("Ivan", "345"));
        result.addUser(new User("Boris", "578"));
        result.addAccountToUser("345", new Account(534, "729"));
        result.addAccountToUser("345", new Account(766, "324"));
        assertThat(expect, is(result.getUserAccounts("345")));
    }

    //Тестируем TransferMoney() с одного акка одного юзера на другой акк другого юзера
    @Test
    public void testTransferMoneyFromOneUserToOther() {
        Map<User, List<Account>> expect = new HashMap<>();
        User oneUser = new User("Petr", "4567");
        List<Account> oneAccount = new ArrayList<>();
        oneAccount.add(new Account(725, "5678"));
        User otherUser = new User("Boris", "906");
        List<Account> otherAccount = new ArrayList<>();
        otherAccount.add(new Account(2275, "2619"));
        expect.put(oneUser, oneAccount);
        expect.put(otherUser, otherAccount);
        Execute result = new Execute();
        result.addUser(new User("Petr", "4567"));
        result.addAccountToUser("4567", new Account(1000, "5678"));
        result.addUser(new User("Boris", "906"));
        result.addAccountToUser("906", new Account(2000, "2619"));
        result.transferMoney("4567", "5678", "906", "2619", 275);
        assertThat(expect, is(result.users));
    }

    //Тестируем TransferMoney() сначала когда денег на исходном аккаунте недостаточно, затем тестируем когда ошиблись в реквизитах аккаунта назначения
    @Test
    public void transferMoneyWhenNotEnoughMoney() {
        Execute result = new Execute();
        result.addUser(new User("Petr", "4568"));
        result.addAccountToUser("4568", new Account(100, "830"));
        result.addUser(new User("Sergey", "592"));
        result.addAccountToUser("592", new Account(100, "8412"));
        assertThat(result.transferMoney("4568", "830", "592", "8412", 101), is(false));
        assertThat(result.transferMoney("4568", "830", "592", "8402", 50), is(false));
    }

}
