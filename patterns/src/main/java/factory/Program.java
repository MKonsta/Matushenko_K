package factory;

public class Program {
    public static void main(String[] args) {
        DeveloperFactory developerFactory1 = new JavaDeveloperFactory();
        developerFactory1.createDeveloper().writeCode();

        DeveloperFactory developerFactory = createDeveloperFactoryBySpeciality("php");
        Developer developer = developerFactory.createDeveloper();

        developer.writeCode();
    }

    static DeveloperFactory createDeveloperFactoryBySpeciality(String speciality) {
        if (speciality.equalsIgnoreCase("java")) {
            return new JavaDeveloperFactory();
        } else if (speciality.equalsIgnoreCase("cpp")) {
            return new CppDeveloperFactory();
        } else if (speciality.equalsIgnoreCase("php")) {
            return new PhpDeveloperFactory();
        } else {
            throw new RuntimeException(speciality + " is unknown speciality");
        }
    }
}
