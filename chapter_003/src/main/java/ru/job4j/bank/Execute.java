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
        for (User user : users.keySet()) {
            if (user.getPassport().equals(passport)) {
                users.get(user).add(account);
            }
        }
    }

    //Удаляет аккаунт из списка аккаунтов List<Account> юзера
    public void deleteAccountFromUser(String passport, Account account) {
        for (User user : users.keySet()) {
            if (user.getPassport().equals(passport)) {
                for (Account delAccount : users.get(user)) {
                    if (delAccount.equals(account)) {
                        users.get(user).remove(account);
                    }
                }
            }
        }
    }

    //Возвращает список всех существующих аккаунтов юзера
    public List<Account> getUserAccounts(String passport) {
        List<Account> result = new ArrayList<>();
        for (User user : users.keySet()) {
            if (user.getPassport().equals(passport)) {
                result = users.get(user);
            }
        }
        return result;
    }

    //переводит деньги с аккауна на аккаунт ондного юзера, либо разных. Если один из счетов не найден, либо денег не достаточно, то return false
    public boolean transferMoney(String srcPassport, String srcRequisite, String dstPassport, String dstRequisite, double amount) {
        boolean result = false;
        int srcIndex = -1;
        int dstIndex = -1;
        User srcUser = new User(null, null);
        User dstUser = new User(null, null);

        for (User user : users.keySet()) {
            if (user.getPassport().equals(srcPassport)) {
                for (Account account : users.get(user)) {
                    if (account.getRequisits().equals(srcRequisite)) {
                        if (account.getValue() >= amount) {
                            srcIndex = users.get(user).indexOf(account);
                            srcUser = user;
                        }
                    }
                }
            } else if (user.getPassport().equals(dstPassport)) {
                for (Account account : users.get(user)) {
                    if (account.getRequisits().equals(dstRequisite)) {
                        dstIndex = users.get(user).indexOf(account);
                        dstUser = user;
                    }
                }
            }
        }
        if (srcIndex != -1 && dstIndex != -1) {
            users.get(srcUser).get(srcIndex).setValue(users.get(srcUser).get(srcIndex).getValue() - amount);
            users.get(dstUser).get(dstIndex).setValue(users.get(dstUser).get(dstIndex).getValue() + amount);
            result = true;
        }
        return result;
    }
}
