package generics.base;

public interface Store <T extends Base> {
    void add(T model);

    boolean replase(String id, T model);

    boolean delete(String id);

    T findById(String id);
}
