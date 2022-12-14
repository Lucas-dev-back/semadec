package br.ifrn.semadec.repositories;

import java.util.UUID;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.ifrn.semadec.entities.user.User;
import br.ifrn.semadec.entities.user.UserStatus;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    @Query("""
            SELECT
                u
            FROM
                User u
            WHERE
                u.status = 'ACTIVE'
            AND
                lower(u.academicEmail) = ?1
                or lower(u.personalEmail) = ?1
                """)
    User findByEmail(String email);

    @Query("""
            SELECT
                u
            FROM
                User u
            WHERE
                u.status = 'ACTIVE'
            AND
                lower(u.username) = ?1
                """)
    User findByUsername(String username);

    @Query("""
            SELECT
                u
            FROM
                User u
            WHERE
                u.status = 'ACTIVE'
            AND
                u.cpf = ?1
                """)
    User findByCpf(String cpf);

    @Query("""
            SELECT
                u
            FROM
                User u
            WHERE
                u.status = 'ACTIVE'
            AND
                lower(u.fullName) LIKE %?1%
                """)
    Collection<User> findByFullName(String fullName);

    @Query("""
            SELECT
                u
            FROM
                User u
            WHERE
                u.status = 'ACTIVE'
            AND
                lower(u.academicEmail) LIKE %?1%
                or lower(u.personalEmail) LIKE %?1%
                """)
    Collection<User> findAllByEmail(String email);

    @Query("""
            SELECT
                u
            FROM
                User u
            WHERE
                u.status = 'ACTIVE'
            AND
                lower(u.username) LIKE %?1%
                """)
    Collection<User> findAllByUsername(String username);

    @Query("""
            SELECT
                u
            FROM
                User u
            WHERE
                u.status = 'ACTIVE'
            AND
                u.cpf LIKE %?1%
                """)
    Collection<User> findAllByCpf(String cpf);

    @Query("""
            SELECT
                u
            FROM
                User u
            WHERE
                u.status = 'ACTIVE'
            AND
                (lower(u.academicEmail) = ?1
                or lower(u.personalEmail) = ?1
                or lower(u.username) = ?1
                or u.cpf = ?1)
            AND
                u.password = ?2
                """)
    User findByUsernameAndPassword(String email, String password);

    @Query("""
            SELECT
                u
            FROM
                User u
            WHERE
                u.status = 'ACTIVE'
            AND
                lower(u.academicEmail) = ?1
                """)
    User findByAcademicEmail(String academicEmail);

    @Query("""
            SELECT
                u
            FROM
                User u
            WHERE
                u.status = 'ACTIVE'
            AND
                lower(u.personalEmail) = ?1
                """)
    User findByPersonalEmail(String personalEmail);

    @Query("""
            SELECT
                u
            FROM
                User u
            WHERE
                u.status = 'ACTIVE'
            AND
                u.matriculation = ?1
            """)
    User findByMatriculation(String matriculation);

    @Query("""
            SELECT
                u
            FROM
                User u
            WHERE
                u.status = ?1
                """)
    Collection<User> findByStatus(UserStatus status);
}
