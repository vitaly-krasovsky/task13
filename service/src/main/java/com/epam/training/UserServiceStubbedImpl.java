package com.epam.training;

import com.google.common.collect.Lists;

import java.util.Collection;

/**
 * @author vkrasovsky
 */
public class UserServiceStubbedImpl implements UserService {
    private UserRepository userRepository = new UserRepositoryImpl();

    public User create(User user) {
        return new User("Vitaly_Krasovsky@epam.com", "Vitaly", "Krasovsky", "qwerty");
    }

    public User update(User user) {
        return new User("Vitaly_Krasovsky@epam.com_", "Vitaly_", "Krasovsky_", "qwerty_");
    }

    public void delete(String id) {

    }

    public User get(String id) {
        if ("qwerty".equals(id)) {
            return new User("Vitaly_Krasovsky@epam.com", "Vitaly", "Krasovsky", "qwerty");
        } else {
            return null;
        }
    }

    public Collection<User> getAll() {
        return Lists.newArrayList(
                new User("Vitaly_Lenin@epam.com", "Vitaly", "Lenin", "1234567"),
                new User("Vitaly_Medvedev@epam.com", "Vitaly", "Medvedev", "09876"),
                new User("Vitaly_Putin@epam.com", "Vitaly", "Putin", "56783")
        );
    }

    public void uploadPhoto(String id, byte[] photo) {

    }

    public byte[] downloadPhoto(String id) {
        return new byte[0];
    }
}
