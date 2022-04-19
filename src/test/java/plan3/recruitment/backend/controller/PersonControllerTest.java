package plan3.recruitment.backend.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import plan3.recruitment.backend.dto.Person;
import plan3.recruitment.backend.dto.PersonName;
import plan3.recruitment.backend.services.PersonServiceImpl;


import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PersonControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private PersonController personController;

    @Mock
    private PersonServiceImpl personService;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(personController).build();
    }

    @Test
    public void test_findAllPerson() throws Exception{
        String uri = "/findAll";
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(uri))
                .andReturn();

        assertEquals(200, mvcResult.getResponse().getStatus());
    }

   @Test
    public void test_readPerson() throws Exception{
        String uri = "/findByEmail/sritest1@gmail.com";
        //when(personService.fetchPersonData(anyString())).thenReturn(ResponseEntity.ok())
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(uri))
                .andReturn();

        assertEquals(200, mvcResult.getResponse().getStatus());
    }

@Test
    public void createPersonTest() throws Exception {
        final Person payload = Person.valueOf("sritest@example.com", new PersonName("Sri", "Vathsan"));
        String uri = "/persons/create";
        when(personService.createPerson(any(Person.class))).thenReturn(true);

        this.mockMvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(convertObjectToJson(payload)))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    public void updateNonExistentPerson() throws Exception {
        final Person payload = Person.valueOf("sritest@example.com", new PersonName("Sri", "Vathsan"));
        String uri = "/update/usertest@example.com";
        when(personService.updatePerson(anyString(), any(Person.class))).thenReturn(false);

        this.mockMvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(convertObjectToJson(payload)))
                .andExpect(MockMvcResultMatchers.status().is(404));
    }

    @Test
    public void updateExistentPerson() throws Exception {
        final Person payload = Person.valueOf("sritest@example.com", new PersonName("Sri", "Vathsan"));
        String uri = "/update/usertest@example.com";
        when(personService.updatePerson(anyString(), any(Person.class))).thenReturn(true);

        this.mockMvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(convertObjectToJson(payload)))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    public void test_deleteExistentPerson() throws Exception{
        String uri = "/persons/remove/sr@example.com";
        when(personService.removePerson(anyString())).thenReturn(true);

        this.mockMvc.perform(MockMvcRequestBuilders.post(uri))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    public void test_deleteNonExistentPerson() throws Exception{
        String uri = "/persons/remove/sr@example.com";
        when(personService.removePerson(anyString())).thenReturn(false);

        this.mockMvc.perform(MockMvcRequestBuilders.post(uri))
                .andExpect(MockMvcResultMatchers.status().is(404));
    }

    private String convertObjectToJson(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }
}
