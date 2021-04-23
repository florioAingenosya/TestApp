package test.florio.testapp.repository;

import test.florio.testapp.model.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AuthorityRepository extends JpaRepository<Authority, String> {
}