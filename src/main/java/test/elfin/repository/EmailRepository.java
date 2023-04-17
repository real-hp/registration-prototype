package test.elfin.repository;

import test.elfin.model.Email;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmailRepository extends ElasticsearchRepository<Email, String> {
   Optional<Email> findByName(String name);

}