package stackjava.com.sbfacebook.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import stackjava.com.sbfacebook.model.UserShortToken;

@Repository
public interface UserShortTokenRepository extends JpaRepository<UserShortToken, Long> {

}
