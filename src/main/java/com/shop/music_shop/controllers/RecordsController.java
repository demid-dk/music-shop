package com.shop.music_shop.controllers;

import com.shop.music_shop.models.Record;
import com.shop.music_shop.repo.RecordRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/records")
public class RecordsController {

  @Autowired
  private CartDAO cartDAO;
  @Autowired
  private RecordRepository recordRepository;

  @GetMapping("/")
  public String catalog(Model model, @RequestParam(required = false) String keyword,
      @RequestParam(defaultValue = "1") int page,
      @RequestParam(defaultValue = "25") int size,
      @RequestParam(defaultValue = "id,asc") String[] sort) {
    try {
      List<Record> records = new ArrayList<Record>();

      String sortField = sort[0];
      String sortDirection = sort[1];

      Direction direction = sortDirection.equals("desc") ? Direction.DESC : Direction.ASC;
      Order order = new Order(direction, sortField);

      Pageable pageable = PageRequest.of(page - 1, size, Sort.by(order));

      Page<Record> pageRecs;

      if (keyword == null) {
        pageRecs = recordRepository.findAll(pageable);
      } else {
        pageRecs = recordRepository.findByNameContainingIgnoreCase(keyword, pageable);
        if(pageRecs.isEmpty()) {
          pageRecs = recordRepository.findByMusicianContainingIgnoreCase(keyword, pageable);
        }
        model.addAttribute("keyword", keyword);
      }

      records = pageRecs.getContent();

      model.addAttribute("records", records);
      model.addAttribute("currentPage", pageRecs.getNumber() + 1);
      model.addAttribute("totalItems", pageRecs.getTotalElements());
      model.addAttribute("totalPages", pageRecs.getTotalPages());
      model.addAttribute("pageSize", size);
      model.addAttribute("sortField", sortField);
      model.addAttribute("sortDirection", sortDirection);
      model.addAttribute("reverseSortDirection", sortDirection.equals("asc") ? "desc" : "asc");
    } catch (Exception e) {
      model.addAttribute("message", e.getMessage());
    }
    return "catalog";
  }

  @PostMapping("/")
  public String addToCart(@RequestParam long id, Model model) {
    Record record = recordRepository.findById(id).orElseThrow(NoSuchElementException::new);
    cartDAO.add(record);
    return "redirect:/cart";
  }

  @GetMapping("/{id}")
  public String view(@PathVariable("id") long id, Model model) {
    if(!recordRepository.existsById(id)) {
      return "redirect:/records";
    }
    Record record = recordRepository.findById(id).orElseThrow(NoSuchElementException::new);
    model.addAttribute("record", record);

    Iterable<Record> artistRecords = recordRepository.findAll();
    model.addAttribute("artistRecords", artistRecords);

    Iterable<Record> genreRecords = recordRepository.findAll();
    model.addAttribute("genreRecords", genreRecords);
    return "record/page";
  }

  @DeleteMapping("/{id}")
  public String delete(@PathVariable("id") long id) {
    recordRepository.deleteById(id);
    return "redirect:/records/";
  }

  @GetMapping("/{id}/edit")
  public String editView(@PathVariable("id") long id, Model model) {
    if(!recordRepository.existsById(id)) {
      return "redirect:/records";
    }
    Record record = recordRepository.findById(id).orElseThrow(NoSuchElementException::new);
    model.addAttribute("record", record);
    return "record/edit";
  }

  @PatchMapping("/{id}/edit")
  public String editRecord(@Valid @ModelAttribute("record") Record record,
      BindingResult bindingResult, @PathVariable("id") long id, Model model) {
    if (bindingResult.hasErrors())
      return "records/{id}/edit";
    recordRepository.save(record);
    return "redirect:/records/";
  }

  @GetMapping("/new")
  public String createView(@ModelAttribute("record") Record record) {
    return "record/new";
  }

  @PostMapping("/new")
  public String createRecord(@ModelAttribute("record") @Valid Record record,
      BindingResult bindingResult, Model model) {
    if (bindingResult.hasErrors())
      return  "records/new";
    recordRepository.save(record);
    return "redirect:/records/{id}";
  }
}
