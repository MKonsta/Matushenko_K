package collectionpro.map;

import java.util.Objects;


/**
 *9.1. Рассказать и продемонстрировать как переопределяют метод hashCode [#9881]
 *
 * В данном классе показана демонстрация переопределения хеш кода. К примеру мы составляем анекету на человека.
 * В анкету занесены различные данные человека - имя, дата рождения, пол, семейное положение, дети. В этом классе мы
 * переопределяем методы хэш код и иквелс. Причем делаем это всего по двум полям - имя, и дата рождения. Остальное нам не важно.
 * Таким образом - все люди у нас будут уникальны с высокой вероятностью. Если же база данных будет слишком большая,
 * и люди могут повторяться, то мы можем в хэшкод и иквелс добавит еще одно или два поля для того чтобы не возникла
 * проблема людей с одинаковыми именами и датами рождений.  И теперь мыможем полноценно работать с объектами нашего класса,
 * и передовать его в другие классы
 */

public class ExampleOverridingHashCode {

    private String name;
    private int birthday;
    private boolean sex;
    private boolean married;
    private int children;

    public ExampleOverridingHashCode(String name, int birthday, boolean sex, boolean married, int children) {
        this.name = name;
        this.birthday = birthday;
        this.sex = sex;
        this.married = married;
        this.children = children;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExampleOverridingHashCode that = (ExampleOverridingHashCode) o;
        return birthday == that.birthday &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, birthday);
    }
}
