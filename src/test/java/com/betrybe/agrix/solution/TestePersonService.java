package com.betrybe.agrix.solution;

import com.betrybe.agrix.ebytr.staff.entity.Person;
import com.betrybe.agrix.ebytr.staff.exception.PersonNotFoundException;
import com.betrybe.agrix.ebytr.staff.repository.PersonRepository;
import com.betrybe.agrix.ebytr.staff.service.PersonService;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.util.Optional;
@SpringBootTest
public class TestePersonService {
  @Autowired
  PersonService personService;
  @MockBean
  PersonRepository personRepository;

  @Test
  @DisplayName("Deve retornar objeto correto quando id existente for passado")
  public void testGetPersonServiceId() {
    Long id = 1L;
    Person fakePerson = new Person();
    fakePerson.setId(id);

    Mockito.when(personRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(fakePerson));
    Person person = personService.getPersonById(id);

    assertNotNull(person);
    assertEquals(id, person.getId());
    assertEquals(person, fakePerson);
    Mockito.verify(personRepository).findById(id);
  }

  @Test
  @DisplayName("Deve retornar not found quando id existente for passado")
  public void testGetPersonServiceNotFound() {
    Long id = 1L;


    Mockito.when(personRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());


    assertThrows(PersonNotFoundException.class, () -> personService.getPersonById(id));

    Mockito.verify(personRepository).findById(id);
  }

  @Test
  @DisplayName("Deve retornar objeto correto quando username existente for passado")
  public void testGetUsernamePerson() {
    String username = "Ana";
    Person fakePerson = new Person();
    fakePerson.setUsername(username);
    Mockito.when(personRepository.findByUsername(Mockito.anyString())).thenReturn(Optional.of(fakePerson));
    Person person = personService.getPersonByUsername(username);

    assertNotNull(person);
    assertEquals(username, person.getUsername());
    assertEquals(person, fakePerson);
    Mockito.verify(personRepository).findByUsername(username);

  }
}
