package services;

import dao.UserDao;
import models.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @InjectMocks
    private UserService userService;
    @Mock
    private UserDao userDao;
    @Test
    public void findUser() {
        int userId = 1;
        userService.findUser(userId);
        Mockito.verify(userDao, Mockito.times(1)).findById(userId);
    }

    @Test
    public void saveUser() {
        User user = new User("Alex", 10, "i@alex.ru");
        userService.saveUser(user);
        Mockito.verify(userDao, Mockito.times(1)).save(user);
    }

    @Test
    public void updateUser() {
        User user = new User("Alex", 10, "i@alex.ru");
        userService.updateUser(user);
        Mockito.verify(userDao, Mockito.times(1)).update(user);
    }

    @Test
    public void deleteUser() {
        User user = new User("Alex", 10, "i@alex.ru");
        userService.deleteUser(user);
        Mockito.verify(userDao, Mockito.times(1)).delete(user);
    }

    @Test
    public void findAllUsers() {
        List<User> list = List.of(new User("Alex", 10, "i@alex.ru"));
        Mockito.when(userDao.findAll()).thenReturn(list);
        List<User> users = userService.findAllUsers();
        Assertions.assertEquals(list, users);
    }
}
