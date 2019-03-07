package ru.job4j.servlets.ajaxjquery;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class Test {
    private static Person setter(String name, String lastname, String gender, String discription) {
        Person person = new Person();
        person.setName(name);
        person.setLastName(lastname);
        person.setGender(gender);
        person.setDiscription(discription);
        return person;
    }

    public static void main(String[] args) throws IOException {
        String line = "{\"name\":\"Ivan\", \"lastName\":\"Petrov\", \"gender\":\"male\", \"discription\":\"discr\"}";
        System.out.println(line);
        ObjectMapper objectMapper = new ObjectMapper();
        Person person = objectMapper.readValue(line, Person.class);
        System.out.println(person);
        //        String ee = "name=Ivan&surname=Petrov&gender=male&discription=discr";
//        StringBuilder sb  = new StringBuilder();
//        sb.append(line);
//
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        Map<Integer, Person> personMap = new ConcurrentHashMap<>();
//        Person person = objectMapper.readValue(sb.toString(), Person.class);
//        System.out.println(person);
//        personMap.put(personMap.size() + 1, person);


//        Map<Integer, Person> map = new ConcurrentHashMap<>();
//        map.put(map.size(), setter("Ivan", "Petrov", "male", "disc"));
//        map.put(map.size(), setter("Helen", "Shvartz", "female", "disc"));
//        map.put(map.size(), setter("Inga", "Starkova", "female", "disc"));
//
//        Set<Person> set = new HashSet<>();
//
//        for (Person person : map.values()) {
//            set.add(person);
//        }
//
//        String toJson = objectMapper.writeValueAsString(set);
//
//        System.out.println(toJson);

//        String line = "{\"name\":\"aa\", \"login\":\"aa\", \"password\":\"aa\", \"email\":\"aa\", \"createDate\":\"aa\", \"role\":\"admin\", \"country\":\"France\", \"city\":\"aa\"}";
//        String line = "{\"name\":\"aa\", \"login\":\"aa\", \"password\":\"aa\", \"email\":\"aa\", \"createDate\":\"11\", \"role\":\"admin\", \"country\":\"aa\", \"city\":\"aa\"}";
//        System.out.println(line);
//        ObjectMapper objectMapper = new ObjectMapper();
//        User user = objectMapper.readValue(line, User.class);
//        System.out.println(user);


    }
}
