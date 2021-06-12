package org.vescm.yerbamateapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class YerbaMate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 45)
    @Column(length = 45, unique = true, nullable = false)
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    private YerbaMateType yerbaMateType;

    @DecimalMin(value = "6.0")
    @Column(precision = 5, scale = 2)
    private Double price;

    @OneToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.REMOVE})
    private List<Comment> comments;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public YerbaMateType getYerbaMateType() {
        return yerbaMateType;
    }

    public void setYerbaMateType(YerbaMateType yerbaMateType) {
        this.yerbaMateType = yerbaMateType;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
