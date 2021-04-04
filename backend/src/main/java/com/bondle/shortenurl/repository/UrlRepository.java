package com.bondle.shortenurl.repository;

import com.bondle.shortenurl.model.Url;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author nguyen.tam.thi at 4:44 PM 4/1/21
 */
public interface UrlRepository extends JpaRepository<Url, Long> {

  Optional<Url> findByHash(String hash);

  List<Url> findAllByUuidOrderByCreatedAtDesc(String uuid);

  @Query(value = "select nextval(pg_get_serial_sequence('url', 'id')) as next_id", nativeQuery = true)
  Long generateNextId();
}
