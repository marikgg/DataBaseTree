package demo;

import java.security.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import demo.entity.User;
import demo.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;


@SpringBootApplication
@EnableJdbcRepositories
public class DemoApplication {

  public static void main(String[] args) {
    ConfigurableApplicationContext context = SpringApplication.run(DemoApplication.class, args);
    UserRepository userRepository = context.getBean(UserRepository.class);

    System.out.println("Окно регистрации\n");
    int process = 1;
    while (process != 0) {
      Scanner scanner = new Scanner(System.in);
      System.out.println("Введите номер действия: 0 - Выход , 1 - Регистраця, 2 - Вывод данных");
      process = scanner.nextInt();
      if (process > -1 && process < 3) {
        switch (process) {
          case 1 -> {
            System.out.println("Введите логин");
            String login = scanner.next();
            while (true) {
              boolean alreadyEx = userRepository.existsByLogin(login);  // проверка есть ли в базе такой login
              if (!alreadyEx) break;
              else {
                System.out.println("Данный логин уже занят, введите новое значение");
                login = scanner.next();
              }
            }
            System.out.println("Введите пароль");
            String password = scanner.next();
            SecureRandom random = new SecureRandom();
            byte[] salt = random.generateSeed(20);
            password += Arrays.toString(salt);
            password = String.valueOf(password.hashCode());
            User user = new User(login, password, Arrays.toString(salt), LocalDateTime.now().toString());
            userRepository.save(user);
          }
          case 2 -> {
            System.out.println("Список пользователей");
            ArrayList<User> users = (ArrayList<User>) userRepository.findAll();
            users.forEach(u -> System.out.println(u.toString()));
            System.out.println();
          }
          case 0 -> process = 0;
        }
      }
    }
  }
}
