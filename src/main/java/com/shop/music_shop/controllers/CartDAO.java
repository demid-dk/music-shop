package com.shop.music_shop.controllers;


import com.shop.music_shop.models.Record;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class CartDAO {
  private static int RECORDS_COUNT;
  private List<Record> records;

  {
    records = new ArrayList<>();
  }

  public List<Record> index() {
    return records;
  }

  public Record show(int id) {
    return records.stream().filter(record -> record.getId() == id).findAny().orElse(null);
  }

  public void add(Record record) {
    ++RECORDS_COUNT;
    records.add(record);
  }

  public void delete(int index) {
    --RECORDS_COUNT;
    records.remove(index);
  }
}
