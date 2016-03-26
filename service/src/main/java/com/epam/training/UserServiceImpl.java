package com.epam.training;

import java.util.Collection;

/**
 * @author vkrasovsky
 */
public class UserServiceImpl implements UserService {
    private UserRepository userRepository = new UserRepositoryImpl();


    public User create(User user) {
        return userRepository.create(user);
    }

    public User update(User user) {
        return userRepository.update(user);
    }

    public void delete(String id) {
        userRepository.delete(id);
    }

    public User get(String id) {
        return userRepository.get(id);
    }

    public Collection<User> getAll() {
        return userRepository.getAll();
    }

    public void uploadPhoto(String id, byte[] photo) {
        userRepository.uploadPhoto(id, photo);
    }

    public byte[] downloadPhoto(String id) {
        return userRepository.downloadPhoto(id);
    }
}
