package com.bondle.shortenurl.model.listener;

import java.sql.Timestamp;
import java.util.Calendar;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 * @author nguyen.tam.thi at 4:32 PM 4/1/21
 */
public class EntityListener {

  @PrePersist
  public void onCreate(BaseEntity entity) {
    entity.setCreatedBy(getActorId());
    entity.setCreatedAt(new Timestamp(Calendar.getInstance().getTimeInMillis()));
    entity.setModifiedBy(getActorId());
    entity.setModifiedAt(new Timestamp(Calendar.getInstance().getTimeInMillis()));
  }

  @PreUpdate
  public void onUpdate(BaseEntity entity) {
    entity.setModifiedBy(getActorId());
    entity.setModifiedAt(new Timestamp(Calendar.getInstance().getTimeInMillis()));
  }

  private String getActorId() {
    // Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    return "ANONYMOUS";
  }

}
