package com.example.demo.jpa;

import com.example.demo.model.Man;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ManDao extends JpaRepository<Man, Long> {
    @Query("select m from Man m where m.login = ?1")
    Optional<Man> findByLogin(String login);


    @Query("select m from Man m " +
            " where " +
            " (upper(m.firstName) like concat('%', upper(?1), '%') or m.firstName is null) and " +
            " (upper(m.secondName) like concat('%', upper(?2), '%') or m.secondName is null)  and  " +
            " (upper(m.lastName) like concat('%', upper(?3), '%') or m.lastName is null)  and  " +
            " (upper(m.phone) like concat('%', upper(?4), '%') or m.phone is null) and " +
            " (upper(m.email) like concat('%', upper(?5), '%') or m.email is null) and " +
            " (upper(m.login) like concat('%', upper(?6), '%') or m.login is null)")
    List<Man> search(String firstName, String secondName, String lastName, String phone, String email, String login);
}
