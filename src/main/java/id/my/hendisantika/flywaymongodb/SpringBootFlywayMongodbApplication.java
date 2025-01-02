package id.my.hendisantika.flywaymongodb;

import id.my.hendisantika.flywaymongodb.entity.User;
import id.my.hendisantika.flywaymongodb.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;

@Slf4j
@SpringBootApplication
public class SpringBootFlywayMongodbApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootFlywayMongodbApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(UserRepository userRepository) {
        return (args) -> {
            log.info("Inserting data...");
            userRepository.save(new User(UUID.randomUUID(), "Itadori Yuji", "yuji@yopmail.com"));
            userRepository.save(new User(UUID.randomUUID(), "Megumi Fushiguro", "megumi@yopmail.com"));
            userRepository.save(new User(UUID.randomUUID(), "Naobara Kugisaki", "naobara@yopmail.com"));
            userRepository.save(new User(UUID.randomUUID(), "Gojo Satoru", "gojo@yopmail.com"));
            userRepository.save(new User(UUID.randomUUID(), "Geto Suguru", "geto@yopmail.com"));
            userRepository.save(new User(UUID.randomUUID(), "Maki Zenin", "maki@yopmail.com"));
            userRepository.save(new User(UUID.randomUUID(), "Mai Zenin", "mai@yopmail.com"));
        };
    }
}
