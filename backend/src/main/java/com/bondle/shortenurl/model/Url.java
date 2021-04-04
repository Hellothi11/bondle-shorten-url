package com.bondle.shortenurl.model;

import com.bondle.shortenurl.model.listener.BaseEntity;
import com.bondle.shortenurl.model.listener.EntityListener;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * @author nguyen.tam.thi at 16:30 2021-04-01
 */
@Entity
@Getter
@Setter
@Table(name = "url")
@EntityListeners(EntityListener.class)
public class Url implements BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "long_url")
  private String longUrl;

  @Column(name = "hash")
  private String hash;

  @Column(name = "uuid")
  private String uuid;

  @Column(name = "created_at")
  private Timestamp createdAt;

  @Column(name = "created_by")
  private String createdBy;

  @Column(name = "modified_at")
  private Timestamp modifiedAt;

  @Column(name = "modified_by")
  private String modifiedBy;

}
