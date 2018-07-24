package com.minlia.rocket.samples.web.openapi.jdbc.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(
    name = "translations",
    uniqueConstraints = @UniqueConstraint(columnNames = {"language", "code"})
)
public class Translation {

  public enum AvailableLanguage {
    zh("zh"),
    en("en");

    private String code;

    AvailableLanguage(String code) {
      this.code = code;
    }

    public String code() {
      return this.code;
    }

    public static AvailableLanguage fromCode(String code) {
      for (AvailableLanguage element : AvailableLanguage.values()) {
        if (code.equals(element.code)) {
          return element;
        }
      }
      return null;
    }

  }

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "code")
  @Length(max = 100)
  @NotNull
  private String code;

  @Column(name = "language")
  @Enumerated(EnumType.STRING)
  @NotNull
  private AvailableLanguage language;

  @Column(name = "value", columnDefinition = "TEXT")
  @NotNull
  private String value;

  @Column(name = "created_at")
//    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
//    @JsonSerialize(using=JsonDateTimeSerializer.class)
  @NotNull
  private LocalDateTime createdAt;

  @Column(name = "updated_at")
//    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
//    @JsonSerialize(using=JsonDateTimeSerializer.class)
  @NotNull
  private LocalDateTime updatedAt;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public AvailableLanguage getLanguage() {
    return language;
  }

  public void setLanguage(AvailableLanguage language) {
    this.language = language;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(LocalDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }

}
