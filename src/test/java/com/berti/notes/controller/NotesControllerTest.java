package com.berti.notes;
import com.berti.notes.model.Notes;
import com.berti.notes.repository.NotesRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import java.util.Arrays;
import java.util.List;
import org.springframework.http.MediaType;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@RunWith(SpringRunner.class)
@WebMvcTest
public class NotesControllerTest {

@Autowired
    private MockMvc mockMvc;

    @MockBean
    private   NotesRepository noteRepo;

    @Test
    public void getAllNotes() throws Exception {
    
        Notes note1 = new Notes();
       Notes note2 = new Notes();
       note1.setId(Long.valueOf(1));
        note1.setBody("This is a new note");
        note2.setId(Long.valueOf(2));
        note2.setBody("This is a second new note");
        List<Notes> notes = Arrays.asList(note1,note2);
        given(noteRepo.getAllNotes()).willReturn(notes);

    
        this.mockMvc.perform(get("/api/notes/"))
                .andExpect(status().isOk()).andDo(print());
              
    }
}
