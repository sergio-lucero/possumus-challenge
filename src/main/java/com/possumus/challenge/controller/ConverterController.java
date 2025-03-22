package com.possumus.challenge.controller;

import com.possumus.challenge.service.ConverterService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Validated
@RequestMapping
public class ConverterController {

  private final ConverterService converterService;

  public ConverterController(ConverterService converterService) {
    this.converterService = converterService;
  }

  @GetMapping("/api/converter/{romanNumber}/arabic")
  public int converterFromRomanNumber(@PathVariable String romanNumber) {
    return converterService.converterFromRomanNumber(romanNumber);
  }

  @GetMapping("/api/converter/{arabicNumber}/roman")
  public String converterFromArabicNumber(@PathVariable int arabicNumber) {
    return converterService.converterFromArabicNumber(arabicNumber);
  }
}
