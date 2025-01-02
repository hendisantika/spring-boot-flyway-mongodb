package id.my.hendisantika.flywaymongodb.repository;

import id.my.hendisantika.flywaymongodb.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-flyway-mongodb
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 02/01/25
 * Time: 13.35
 * To change this template use File | Settings | File Templates.
 */
public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByEmail(String email);
}
