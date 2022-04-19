package plan3.recruitment.backend.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import plan3.recruitment.backend.entity.PersonEntity;
import plan3.recruitment.backend.repositories.PersonRepository;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PersonServiceTest {
    @InjectMocks
    private PersonServiceImpl personService;

    @Mock
    private PersonRepository personRepository;


    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test_whenFindAll() {
        personService.fetchAll();
        verify(personRepository, times(1)).findAll();
    }

    @Test
    public void test_removeNonExistingPerson() {
        when(personRepository.findByEmail(anyString())).thenReturn(null);
        personService.removePerson(anyString());
        verify(personRepository, times(0)).delete(any());
        personRepository.flush();
    }

    @Test
    public void test_removeExistingPerson() {
        PersonEntity person = new PersonEntity();
        when(personRepository.findByEmail(anyString())).thenReturn(person);
        personService.removePerson(anyString());
        verify(personRepository, times(1)).delete(any());
    }
}
