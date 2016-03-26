package com.epam.training;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author vkrasovsky
 */
public class UserRepositoryImpl implements UserRepository {
    private Map<String, User> userDb = new HashMap<String, User>();
    private Map<String, byte[]> imageDb = new HashMap<String, byte[]>();


    public User create(User user) {
        if (!userDb.containsKey(user.getEmail())) {
            userDb.put(user.getEmail(), user);
        } else {
            throw new RuntimeException("User already exists with email: " + user.getEmail());
        }
        return user;
    }

    public User update(User user) {
        userDb.put(user.getEmail(), user);
        return user;
    }

    public void delete(String id) {
        userDb.remove(id);
    }

    public User get(String id) {
        return userDb.get(id);
    }

    public Collection<User> getAll() {
        return userDb.values();
    }

    public void uploadPhoto(String id, byte[] photo) {
        imageDb.put(id, photo);
    }

    public byte[] downloadPhoto(String id) {
        return imageDb.get(id);
    }
}
