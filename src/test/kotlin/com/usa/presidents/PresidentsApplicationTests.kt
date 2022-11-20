package com.usa.presidents

import com.fasterxml.jackson.databind.ObjectMapper
import com.usa.presidents.model.POTUS
import org.junit.jupiter.api.Test
import org.mockito.internal.matchers.GreaterThan
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.*
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post

@SpringBootTest
@AutoConfigureMockMvc
class presidentsApplicationTests(
  @Autowired val mockMvc: MockMvc,
  @Autowired val objectMapper: ObjectMapper
) {

  @Test
  fun `Assert POTUSs has George Washington as the first item`() {
    mockMvc.get("/presidents")
      .andExpect {
        status { isOk() }
        content { contentType(MediaType.APPLICATION_JSON) }
        jsonPath("$[0].id") { value(1) }
        jsonPath("$[0].name") { value("George Washington") }
        jsonPath("$[0].party") { value("Unaffiliated") }
        jsonPath("$.length()") { GreaterThan(1) }
      }
  }

  @Test
  fun `Assert POTUS detail endpoint with id gives an President`() {
    mockMvc.get("/presidents/1")
      .andExpect {
        status { isOk() }
        content { contentType(MediaType.APPLICATION_JSON) }
        jsonPath("$.name") { value("George Washington") }
      }
  }

  @Test
  fun `Assert we can update an POTUS`() {
    mockMvc.get("/presidents/1")
      .andExpect {
        status { isOk() }
        content { contentType(MediaType.APPLICATION_JSON) }
        jsonPath("$.name") { value("George Washington") }
        jsonPath("$.party") { value("Unaffiliated") }
      }
    val updatePOTUS = POTUS(1, "NewPresident", "NewParty")
    mockMvc.put("/presidents/1") {
      contentType = MediaType.APPLICATION_JSON
      content = objectMapper.writeValueAsString(updatePOTUS)
    }
      .andExpect {
        status { isOk() }
        content { contentType(MediaType.APPLICATION_JSON) }
        jsonPath("$.id") { value(1) }
        jsonPath("$.name") { value("NewPresident") }
        jsonPath("$.party") { value("NewParty") }
      }
  }

  @Test
  fun `Assert we can create an POTUS with PUT`() {
    mockMvc.get("/presidents/7")
      .andExpect {
        status { isNotFound() }
      }
    val updatePOTUS = POTUS(7, "NewPresident", "NewParty")
    mockMvc.put("/presidents/7") {
      contentType = MediaType.APPLICATION_JSON
      content = objectMapper.writeValueAsString(updatePOTUS)
    }
      .andExpect {
        status { isCreated() }
        content { contentType(MediaType.APPLICATION_JSON) }
        jsonPath("$.id") { value(7) }
        jsonPath("$.name") { value("NewPresident") }
        jsonPath("$.party") { value("NewParty") }
      }
    mockMvc.get("/presidents/7")
      .andExpect {
        status { isOk() }
        content { contentType(MediaType.APPLICATION_JSON) }
        jsonPath("$.id") { value(7) }
        jsonPath("$.name") { value("NewPresident") }
        jsonPath("$.party") { value("NewParty") }
      }
  }

  @Test
  fun `Assert that we can create an POTUS`() {
    mockMvc.get("/presidents/6")
      .andExpect {
        status { isNotFound() }
      }
    val newPOTUS = POTUS(0, "NewPresident", "NewParty")
    mockMvc.post("/presidents") {
      contentType = MediaType.APPLICATION_JSON
      content = objectMapper.writeValueAsString(newPOTUS)
    }
      .andExpect {
        status { isCreated() }
        content { contentType(MediaType.APPLICATION_JSON) }
        jsonPath("$.name") { value("NewPresident") }
        jsonPath("$.party") { value("NewParty") }
        jsonPath("$.id") { value(6) }
      }
    mockMvc.get("/presidents/6")
      .andExpect {
        status { isOk() }
        content { contentType(MediaType.APPLICATION_JSON) }
        jsonPath("$.name") { value("NewPresident") }
        jsonPath("$.party") { value("NewParty") }
        jsonPath("$.id") { value(6) }
      }
  }

  @Test
  fun `Assert that we can delete an POTUS`() {
    mockMvc.get("/presidents/2")
      .andExpect {
        status { isOk() }
      }
    mockMvc.delete("/presidents/2")
      .andExpect {
        status { isNoContent() }
      }
    mockMvc.get("/presidents/2")
      .andExpect {
        status { isNotFound() }
      }
  }

}