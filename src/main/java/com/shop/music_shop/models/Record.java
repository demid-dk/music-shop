package com.shop.music_shop.models;

import com.opencsv.bean.CsvBindByName;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Entity
public class Record {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotEmpty(message = "Name should not be empty")
  @CsvBindByName
  private String name;

  @NotEmpty(message = "Name of musician should not be empty")
  @CsvBindByName
  private String musician;
  @CsvBindByName
  private String genre;
  @CsvBindByName
  private String label;
  @CsvBindByName
  private String format;
  @CsvBindByName
  private String recordCondition;
  @CsvBindByName
  private String year;
  @Min(value = 1000, message = "Price should be greater than 1000")
  @CsvBindByName
  private int price;

  public Record() {
  }

  public Record(String name, String musician, String genre, String label, String format,
      String recordCondition, String year, int price) {
    this.name = name;
    this.musician = musician;
    this.genre = genre;
    this.label = label;
    this.format = format;
    this.recordCondition = recordCondition;
    this.year = year;
    this.price = price;
  }

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

  public String getMusician() {
    return musician;
  }

  public void setMusician(String musician) {
    this.musician = musician;
  }

  public String getGenre() {
    return genre;
  }

  public void setGenre(String genre) {
    this.genre = genre;
  }

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public String getFormat() {
    return format;
  }

  public void setFormat(String format) {
    this.format = format;
  }

  public String getRecordCondition() {
    return recordCondition;
  }

  public void setRecordCondition(String recordCondition) {
    this.recordCondition = recordCondition;
  }

  public String getYear() {
    return year;
  }

  public void setYear(String year) {
    this.year = year;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  @Override
  public String toString() {
    return "Record{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", musician='" + musician + '\'' +
        ", genre='" + genre + '\'' +
        ", label='" + label + '\'' +
        ", format='" + format + '\'' +
        ", recordCondition='" + recordCondition + '\'' +
        ", year=" + year +
        ", price=" + price +
        '}';
  }
}
