package id.my.hendisantika.flywaymongodb;

import id.my.hendisantika.flywaymongodb.entity.User;
import id.my.hendisantika.flywaymongodb.repository.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@DataMongoTest
class SpringBootFlywayMongodbApplicationTests {


    @Container
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:8.0.0");

    @Autowired
    private UserRepository userRepository;

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @BeforeAll
    static void setUp() {
        mongoDBContainer.start();
    }

    @Test
    void testFindByEmail() {
        User user = new User(UUID.randomUUID(), "Test User", "test@yopmail.com");
        userRepository.save(user);

        Optional<User> foundUser = userRepository.findByEmail("test@yopmail.com");
        assertThat(foundUser).isPresent();
        assertThat(foundUser.get().getName()).isEqualTo("Test User");
    }
}
