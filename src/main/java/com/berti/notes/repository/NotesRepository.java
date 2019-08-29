package com.berti.notes.repository;

import com.berti.notes.model.Notes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NotesRepository {

    @Autowired
    JdbcTemplate template;

    /* Getting all */
    public List<Notes> getAllNotes() {
        List<Notes> items = template.query("select id,content from NOTES", (result,
                rowNum) -> new Notes(result.getLong("id"), result.getString("content")));
        return items;
    }

    /* Getting all name by query string */
    public List<Notes> getSearch(String search) {
        List<Notes> items = template.query("SELECT * FROM NOTES WHERE content LIKE ?", (result,
                rowNum) -> new Notes(result.getLong("id"), result.getString("content")),
                "%" + search + "%");
        return items;
    }

    /* Getting by id */
    public Notes getNote(int search) {
        Notes items = template.queryForObject("SELECT * FROM NOTES WHERE ID=?", (result,
                rowNum) -> new Notes(result.getLong("id"), result.getString("content")),
                search);
        return items;
    }

    /* Adding into database table */
    public int addNote(String content) {
        String query = "INSERT INTO NOTES (content) VALUES(?)";
        return template.update(query, content);
    }

    /** 
     * update a note or insert a note if tat ID doesn't exist
    */
    public int updateNote(int id, String content) {
        int q1 = 0;
        String checkid = "SELECT * FROM NOTES WHERE ID=?1";
        String query = "INSERT INTO NOTES (content) VALUES(?2)";
        String query2 = "UPDATE  NOTES SET CONTENT = ?2 WHERE ID = ?1";
        Notes items = template.queryForObject(checkid, (result, rowNum) -> new Notes(result.getLong("id"), result.getString("content")), id);
        System.out.println(items.getContent());

        if (items.getContent() != "") {
            System.out.println(items);

            q1 = template.update(query2, id, content);
        } else {

            q1 = template.update(query, content);
        }
        System.out.println(q1);
        return q1;
    }
    /**
     *  get the last note
     */
    public Notes lastNote() {
        Notes items = template.queryForObject("SELECT * from NOTES WHERE id=(SELECT max(id) FROM NOTES)", (result,
                rowNum) -> new Notes(result.getLong("id"), result.getString("content")));
        return items;
    }

    /* Delete an item */
    public int deleteItem(int id) {
        String query = "DELETE FROM NOTES WHERE ID =?";
        return template.update(query, id);
    }
}
