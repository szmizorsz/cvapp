package io.szmizorsz.cvapp.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

/**
 * A Language.
 */
@Document(collection = "T_LANGUAGE")
public class Language implements Serializable {

    @Id
    private String id;

    @Field("name_en")
    private String nameEn;

    @Field("name_hu")
    private String nameHu;

    @Field("level_en")
    private String levelEn;

    @Field("level_hu")
    private String levelHu;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getNameHu() {
        return nameHu;
    }

    public void setNameHu(String nameHu) {
        this.nameHu = nameHu;
    }

    public String getLevelEn() {
        return levelEn;
    }

    public void setLevelEn(String levelEn) {
        this.levelEn = levelEn;
    }

    public String getLevelHu() {
        return levelHu;
    }

    public void setLevelHu(String levelHu) {
        this.levelHu = levelHu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Language language = (Language) o;

        if (id != null ? !id.equals(language.id) : language.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Language{" +
                "id=" + id +
                ", nameEn='" + nameEn + "'" +
                ", nameHu='" + nameHu + "'" +
                ", levelEn='" + levelEn + "'" +
                ", levelHu='" + levelHu + "'" +
                '}';
    }
}
