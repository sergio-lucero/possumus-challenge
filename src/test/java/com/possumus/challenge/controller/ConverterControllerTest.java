package com.possumus.challenge.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
class ConverterControllerTest {

  private final MockMvc mockMvc;

  @Autowired
  ConverterControllerTest(MockMvc mockMvc) {
    this.mockMvc = mockMvc;
  }

  @Test
  void converterFromRomanNumber() throws Exception {
    mockMvc.perform(get("/api/converter/XXX/arabic")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").value(30));
  }

  @Test
  void converterFromRomanNumberHundred() throws Exception {
    mockMvc.perform(get("/api/converter/DLXXIX/arabic")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").value(579));
  }

  @Test
  void converterFromRomanNumberThousands() throws Exception {
    mockMvc.perform(get("/api/converter/MDCLXXXII/arabic")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").value(1682));
  }

  @Test
  void converterFromRomanNumberUnits() throws Exception {
    mockMvc.perform(get("/api/converter/IX/arabic")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").value(9));
  }

  @Test
  void converterFromRomanNumberBadRequest() throws Exception {
    mockMvc.perform(get("/api/converter/XXXX/arabic")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());
  }

  @Test
  void converterFromRomanNumberBadRequestArabicNumber() throws Exception {
    mockMvc.perform(get("/api/converter/7859/arabic")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());
  }

  @Test
  void converterFromArabicNumber() throws Exception {
    mockMvc.perform(get("/api/converter/859/roman")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").value("DCCCLIX"));
  }

  @Test
  void converterFromArabicNumberMil() throws Exception {
    mockMvc.perform(get("/api/converter/2559/roman")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").value("MMDLIX"));
  }

  @Test
  void converterFromArabicNumberTen() throws Exception {
    mockMvc.perform(get("/api/converter/75/roman")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").value("LXXV"));
  }

  @Test
  void converterFromArabicNumberUnit() throws Exception {
    mockMvc.perform(get("/api/converter/8/roman")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").value("VIII"));
  }

  @Test
  void converterFromArabicNumberMinError() throws Exception {
    mockMvc.perform(get("/api/converter/-15/roman")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.description").value("Error the number arabic is minor to 0"));
  }

  @Test
  void converterFromArabicNumberMaxError() throws Exception {
    mockMvc.perform(get("/api/converter/7500/roman")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.description").value("Error the number arabic is mayor to 3999"));
  }
}