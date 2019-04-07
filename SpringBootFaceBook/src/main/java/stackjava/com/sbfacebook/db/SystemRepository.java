package stackjava.com.sbfacebook.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restfb.types.Note;

import stackjava.com.sbfacebook.model.Phone;

@Repository
public interface SystemRepository extends  JpaRepository<Phone, Long> {

}
