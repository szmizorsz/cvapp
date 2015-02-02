package io.szmizorsz.cvapp.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

/**
 * A Other.
 */
@Document(collection = "T_OTHER")
public class Other implements Serializable {

    @Id
    private String id;

    @Field("category_en")
    private String categoryEn;

    @Field("category_hu")
    private String categoryHu;

    @Field("description_en")
    private String descriptionEn;

    @Field("description_hu")
    private String descriptionHu;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoryEn() {
        return categoryEn;
    }

    public void setCategoryEn(String categoryEn) {
        this.categoryEn = categoryEn;
    }

    public String getCategoryHu() {
        return categoryHu;
    }

    public void setCategoryHu(String categoryHu) {
        this.categoryHu = categoryHu;
    }

    public String getDescriptionEn() {
        return descriptionEn;
    }

    public void setDescriptionEn(String descriptionEn) {
        this.descriptionEn = descriptionEn;
    }

    public String getDescriptionHu() {
        return descriptionHu;
    }

    public void setDescriptionHu(String descriptionHu) {
        this.descriptionHu = descriptionHu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Other other = (Other) o;

        if (id != null ? !id.equals(other.id) : other.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Other{" +
                "id=" + id +
                ", categoryEn='" + categoryEn + "'" +
                ", categoryHu='" + categoryHu + "'" +
                ", descriptionEn='" + descriptionEn + "'" +
                ", descriptionHu='" + descriptionHu + "'" +
                '}';
    }
}
