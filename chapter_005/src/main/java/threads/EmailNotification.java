package threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmailNotification {

    private ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public synchronized void executeNotification() {
        pool.execute(new Runnable() {
            @Override
            public void run() {
                emailTo(new User("User", "user@mail.ru"));
            }
        });
    }

    public void emailTo(User user) {
        StringBuilder subject = new StringBuilder();
        subject.append("Notification ").append(user.getUsername()).append(" to E-mail: ").append(user.getEmail());

        StringBuilder body = new StringBuilder();
        body.append("Add new event to ").append(user.getUsername());

        send(subject.toString(), body.toString(), user.getEmail());
    }

    public void send(String subject, String body, String email) {

    }
}

class User {
   private String username;
    private String email;

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }
}