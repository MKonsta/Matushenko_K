package generics.base;

public class User extends Base {

    public User(String id) {
        super(id);
    }

    public static void main(String[] args) {
        User user = new User("erer");
        System.out.println(user.getId());
    }
}
