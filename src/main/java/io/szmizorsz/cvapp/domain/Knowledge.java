package io.szmizorsz.cvapp.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

/**
 * A Knowledge.
 */
@Document(collection = "T_KNOWLEDGE")
public class Knowledge implements Serializable {

    @Id
    private String id;

    @Field("category_en")
    private String categoryEn;

    @Field("category_hu")
    private String categoryHu;

    @Field("details_en")
    private String detailsEn;

    @Field("details_hu")
    private String detailsHu;

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

    public String getDetailsEn() {
        return detailsEn;
    }

    public void setDetailsEn(String detailsEn) {
        this.detailsEn = detailsEn;
    }

    public String getDetailsHu() {
        return detailsHu;
    }

    public void setDetailsHu(String detailsHu) {
        this.detailsHu = detailsHu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Knowledge knowledge = (Knowledge) o;

        if (id != null ? !id.equals(knowledge.id) : knowledge.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Knowledge{" +
                "id=" + id +
                ", categoryEn='" + categoryEn + "'" +
                ", categoryHu='" + categoryHu + "'" +
                ", detailsEn='" + detailsEn + "'" +
                ", detailsHu='" + detailsHu + "'" +
                '}';
    }
}
