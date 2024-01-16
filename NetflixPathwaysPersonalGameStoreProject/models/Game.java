package com.company.gamestore.model;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "video_game")
public class VideoGame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Game title required")
    @Size(max = 50, message = "Title max 50 characters")
    private String title;

    @NotBlank(message = "Rating required")
    @Size(max = 50, message = "Rating max 50 characters")
    private String rating;

    @NotBlank(message = "Description needed")
    @Size(max = 255, message = "Description max 255 characters")
    private String description;

    @NotNull(message = "Price needed")
    @DecimalMin(value = "0.00", message = "Positive price required")
    private BigDecimal price;

    @NotBlank(message = "Studio name required")
    @Size(max = 50, message = "Studio max 50 characters")
    private String studio;

    @Min(value = 0, message = "Non-negative quantity required")
    private Integer quantity;

    public VideoGame() {}

    public VideoGame(String title, String rating, String description, BigDecimal price, String studio, Integer quantity) {
        this.title = title;
        this.rating = rating;
        this.description = description;
        this.price = price;
        this.studio = studio;
        this.quantity = quantity;
    }

    // Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    // Other getters and setters remain unchanged

    // equals, hashCode, and toString methods

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VideoGame)) return false;
        VideoGame that = (VideoGame) o;
        return Objects.equals(id, that.id) && 
               Objects.equals(title, that.title) &&
               Objects.equals(rating, that.rating) &&
               Objects.equals(description, that.description) &&
               Objects.equals(price, that.price) &&
               Objects.equals(studio, that.studio) &&
               Objects.equals(quantity, that.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, rating, description, price, studio, quantity);
    }

    @Override
    public String toString() {
        return "VideoGame{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", rating='" + rating + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", studio='" + studio + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
