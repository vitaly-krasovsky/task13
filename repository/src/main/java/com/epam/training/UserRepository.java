package com.epam.training;

import java.util.Collection;

/**
 * @author vkrasovsky
 */
public interface UserRepository {
    User create(User user);

    User update(User user);

    void delete(String id);

    User get(String id);

    Collection<User> getAll();

    void uploadPhoto(String id, byte[] photo);

    byte[] downloadPhoto(String id);
}
