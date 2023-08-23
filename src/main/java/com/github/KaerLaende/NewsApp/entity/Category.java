package com.github.KaerLaende.NewsApp.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

/**
 * Сущность для работы с категориями, к которым возможно отнести новость.
 */
@Entity
@Data
@Table(name = "category")
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 2, max = 30, message = "Название категории должно содержать от 2 до 30 символов")
    @Column(name = "category_name")
    private String categoryName;

    @OneToMany(mappedBy = "category")
    private List<News> news;
    @Override
    public String toString() {
        return "Category(id=" + id + ", categoryName=" + categoryName + ")";
    }

}