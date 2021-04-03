package com.bondle.shortenurl.repository;

import com.bondle.shortenurl.model.Url;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author nguyen.tam.thi at 4:44 PM 4/1/21
 */
public interface UrlRepository extends JpaRepository<Url, Long> {
  Optional<Url> findByHash(String hash);
}
