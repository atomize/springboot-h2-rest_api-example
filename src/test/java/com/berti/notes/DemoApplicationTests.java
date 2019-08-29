package com.berti.notes;
import com.berti.notes.model.Notes;
import com.berti.notes.repository.NotesRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@RunWith(SpringRunner.class)
@WebMvcTest
public class DemoApplicationTests {

@Autowired
    private MockMvc mockMvc;

    @MockBean
    private   NotesRepository noteRepo;

    @Test
    public void getAllNotes() throws Exception {
        // given
        Notes note = new Notes();
       
        note.setContent("This is a new note");

        List<Notes> notes = Arrays.asList(note);
        given(noteRepo.getAllNotes()).willReturn(notes);

        // when + then
        this.mockMvc.perform(get("/api/notes/2"))
                .andExpect(status().isOk())
               .andExpect(content().string("{'id': 2,'content': 'ladder back\nto the strategy parallel path helicopter view, or run it up the flag pole get buy-in'}"));
    }
}
