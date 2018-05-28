package ru.job4j.bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Банковские переводы. [#10038]
 */
public class Execute {
    Map<User, List<Account>> users = new HashMap<>();

    /**
     * Метод добавляет юзера в коллекцию @users
     * @param user
     */
    public void addUser(User user) {
        List<Account> accounts = new ArrayList<>();
        users.put(user, accounts);
    }

    //Метод удаляет юзера из коллекции
    public void deleteUser(User user) {
        users.remove(user);
    }

    //Добавляет аккаунт в список аккаунтов List<Account> юзера
    public void addAccountToUser(String passport, Account account) {
        users.get(new User(null, passport)).add(account);
    }

    //Удаляет аккаунт из списка аккаунтов List<Account> юзера
    public void deleteAccountFromUser(String passport, Account account) {
        users.get(new User(null, passport)).remove(account);
    }

    //Возвращает список всех существующих аккаунтов юзера
    public List<Account> getUserAccounts(String passport) {
        return users.get(new User(null, passport));
    }

    //переводит деньги с аккауна на аккаунт ондного юзера, либо разных. Если один из счетов не найден, либо денег не достаточно, то return false
    public boolean transferMoney(String srcPassport, String srcRequisite, String dstPassport, String dstRequisite, double amount) {
        int srcIndex = -1;
        int dstIndex = -1;
        boolean result = false;
        for (Account account : users.get(new User(null, srcPassport))) {
            if (account.getRequisits().equals(srcRequisite) && account.getValue() >= amount) {
                srcIndex = users.get(new User(null, srcPassport)).indexOf(account);
            }
        }
        for (Account account : users.get(new User(null, dstPassport))) {
            if (account.getRequisits().equals(dstRequisite)) {
                dstIndex = users.get(new User(null, dstPassport)).indexOf(account);
            }
        }
        if (srcIndex != -1 && dstIndex != -1) {
            users.get(new User(null, srcPassport)).get(srcIndex).setValue(users.get(new User(null, srcPassport)).get(srcIndex).getValue() - amount);
            users.get(new User(null, dstPassport)).get(dstIndex).setValue(users.get(new User(null, dstPassport)).get(dstIndex).getValue() + amount);
            result = true;
        }
        return result;
    }
}
