package com.shop.music_shop.controllers;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.shop.music_shop.models.Record;
import com.shop.music_shop.repo.RecordRepository;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UploadController {
  @Autowired
  RecordRepository recordRepository;

  @GetMapping("/upload-many")
  public String uploadView(Model model) {
    return "upload";
  }

  @PostMapping("/upload-many")
  public String uploadPost (Model model) throws IOException {
    String fileName = "src/main/resources/catalog.csv";
    Path myPath = Paths.get(fileName);

    try (BufferedReader br = Files.newBufferedReader(myPath,
        StandardCharsets.UTF_8)) {

      HeaderColumnNameMappingStrategy<Record> strategy
          = new HeaderColumnNameMappingStrategy<>();
      strategy.setType(Record.class);

      CsvToBean<Record> csvToBean = new CsvToBeanBuilder<Record>(br)
          .withMappingStrategy(strategy)
          .withIgnoreLeadingWhiteSpace(true)
          .withSeparator(';')
          .build();

      List<Record> records = csvToBean.parse();
      recordRepository.saveAll(records);
    }
    return "redirect:/records/";
  }
}
