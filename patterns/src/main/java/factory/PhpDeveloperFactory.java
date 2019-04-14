package factory;

public class PhpDeveloperFactory implements DeveloperFactory{
    public Developer createDeveloper() {
        Developer phpDeveloper = new PhpDeveloper();
        return phpDeveloper;
    }
}
