package io.szmizorsz.cvapp.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

/**
 * A Education.
 */
@Document(collection = "T_EDUCATION")
public class Education implements Serializable {

    @Id
    private String id;

    @Field("period_en")
    private String periodEn;

    @Field("period_hu")
    private String periodHu;

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

    public String getPeriodEn() {
        return periodEn;
    }

    public void setPeriodEn(String periodEn) {
        this.periodEn = periodEn;
    }

    public String getPeriodHu() {
        return periodHu;
    }

    public void setPeriodHu(String periodHu) {
        this.periodHu = periodHu;
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

        Education education = (Education) o;

        if (id != null ? !id.equals(education.id) : education.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Education{" +
                "id=" + id +
                ", periodEn='" + periodEn + "'" +
                ", periodHu='" + periodHu + "'" +
                ", descriptionEn='" + descriptionEn + "'" +
                ", descriptionHu='" + descriptionHu + "'" +
                '}';
    }
}
