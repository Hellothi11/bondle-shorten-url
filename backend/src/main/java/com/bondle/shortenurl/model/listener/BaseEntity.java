package com.bondle.shortenurl.model.listener;

import java.sql.Timestamp;

/**
 * @author nguyen.tam.thi at 4:32 PM 4/1/21
 */
public interface BaseEntity {

  void setCreatedAt(Timestamp createdDate);

  void setCreatedBy(String createdBy);

  void setModifiedAt(Timestamp modifiedDate);

  void setModifiedBy(String modifiedBy);
}
