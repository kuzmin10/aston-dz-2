import models.User;
import services.UserService;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException {

        UserService userService = new UserService();
        Scanner scanner = new Scanner(System.in);
        User user;
        String name;
        String email;
        int input;
        int id;
        int age;
        List<User> list;

        while (true) {
            System.out.println("1. создание");
            System.out.println("2. чтение");
            System.out.println("3. обновление");
            System.out.println("4. удаление");
            System.out.println("5. вывести таблицу");
            System.out.println("0. выход");

            input = scanner.nextInt();

            switch (input) {
                case 1:
                    System.out.println("Введите имя");
                    name = scanner.next();
                    System.out.println("Введите возраст");
                    age = scanner.nextInt();
                    System.out.println("Введите email");
                    email = scanner.next();
                    user = new User(name, age, email);
                    userService.saveUser(user);
                    break;
                case 2:
                    System.out.println("Введите id");
                    id = scanner.nextInt();
                    user = userService.findUser(id);
                    System.out.println(user);
                    break;
                case 3:
                    System.out.println("Введите id");
                    id = scanner.nextInt();
                    user = userService.findUser(id);
                    System.out.println("Введите имя");
                    user.setName(scanner.next());
                    System.out.println("Введите возраст");
                    user.setAge(scanner.nextInt());
                    System.out.println("Введите email");
                    user.setEmail(scanner.next());
                    userService.updateUser(user);
                    break;
                case 4:
                    System.out.println("Введите id");
                    id = scanner.nextInt();
                    user = userService.findUser(id);
                    userService.deleteUser(user);
                    break;
                case 5:
                    list = userService.findAllUsers();
                    list.forEach(System.out::println);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Введите число");
            }
        }
    }
}
