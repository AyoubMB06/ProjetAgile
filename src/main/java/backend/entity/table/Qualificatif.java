package backend.entity.table;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "QUALIFICATIF")
public class Qualificatif {
    @Id
    @Column(name = "ID_QUALIFICATIF", nullable = false)
    private Integer id;

    @Size(max = 16)
    @NotNull
    @Column(name = "MAXIMAL", nullable = false, length = 16)
    private String maximal;

    @Size(max = 16)
    @NotNull
    @Column(name = "MINIMAL", nullable = false, length = 16)
    private String minimal;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMaximal() {
        return maximal;
    }

    public void setMaximal(String maximal) {
        this.maximal = maximal;
    }

    public String getMinimal() {
        return minimal;
    }

    public void setMinimal(String minimal) {
        this.minimal = minimal;
    }

}