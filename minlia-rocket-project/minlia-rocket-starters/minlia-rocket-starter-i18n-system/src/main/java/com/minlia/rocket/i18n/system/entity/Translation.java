package com.minlia.rocket.i18n.system.entity;

import com.minlia.rocket.data.entity.AbstractEntity;
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

/**
 * @author will
 */
@Entity
@Table(
    name = "translations",
    uniqueConstraints = @UniqueConstraint(columnNames = {"language", "code"})
)
public class Translation extends AbstractEntity<Long> {

//  public enum AvailableLanguage {
//    zh("zh"),
//    en("en");
//    private String code;
//
//    AvailableLanguage(String code) {
//      this.code = code;
//    }
//
//    public String code() {
//      return this.code;
//    }
//
//    public static AvailableLanguage fromCode(String code) {
//      for (AvailableLanguage element : AvailableLanguage.values()) {
//        if (code.equals(element.code)) {
//          return element;
//        }
//      }
//      return null;
//    }
//
//  }

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "code")
  @Length(max = 100)
  @NotNull
  private String code;

  @Column(name = "language")
//  @Enumerated(EnumType.STRING)
  @NotNull
  private String language;

  @Column(name = "value", columnDefinition = "TEXT")
  @NotNull
  private String value;

  @Override
  public Long getId() {
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

  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }


}
