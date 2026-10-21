package id.my.hendisantika.flywaymongodb;

import id.my.hendisantika.flywaymongodb.entity.User;
import id.my.hendisantika.flywaymongodb.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.mongodb.test.autoconfigure.DataMongoTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.mongodb.MongoDBContainer;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@DataMongoTest
class SpringBootFlywayMongodbApplicationTests {


    @Container
    @ServiceConnection
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:8.0.1");

    @Autowired
    private UserRepository userRepository;

    @Test
    void testFindByEmail() {
        User user = new User(UUID.randomUUID(), "Test User", "test@yopmail.com");
        userRepository.save(user);

        Optional<User> foundUser = userRepository.findByEmail("test@yopmail.com");
        assertThat(foundUser).isPresent();
        assertThat(foundUser.get().getName()).isEqualTo("Test User");
    }
}
