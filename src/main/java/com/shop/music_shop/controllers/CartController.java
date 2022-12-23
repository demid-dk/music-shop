package com.shop.music_shop.controllers;

import com.shop.music_shop.models.Record;
import com.shop.music_shop.repo.RecordRepository;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CartController {
  @Autowired
  private CartDAO cart;

  @Autowired
  private RecordRepository recordRepository;

  @GetMapping("/cart")
  public String cartView(Model model) {
    Iterable<Record> records = cart.index();
    model.addAttribute("records", records);
    return "cart";
  }

  @DeleteMapping("/cart")
  public String deleteFromCart(@RequestParam(value = "index", required = true) int index, Model model) {
//    Record record = recordRepository.findById(id).orElseThrow(NoSuchElementException::new);
    cart.delete(index);
    return "redirect:/cart";
  }
}
