package io.pivotal.pal.tracker;

import io.pivotal.pal.tracker.TimeEntry;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TimeEntryController {

    private TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository=timeEntryRepository;
    }

    @PostMapping("/time-entries")
    public ResponseEntity<String> create(@RequestBody TimeEntry timeEntryToCreate) {
        TimeEntry savedTimeEntry = timeEntryRepository.create(timeEntryToCreate);
        return new ResponseEntity(savedTimeEntry, HttpStatus.CREATED);
    }


    @GetMapping("/time-entries/{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable("id") long timeEntryId) {
        TimeEntry savedTimeEntry = timeEntryRepository.find(timeEntryId);

        if(savedTimeEntry==null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity(savedTimeEntry, HttpStatus.OK);
        }

    }

    @GetMapping("/time-entries")
    public ResponseEntity<List<TimeEntry>> list() {
        List<TimeEntry> returnedList = timeEntryRepository.list();

        return new ResponseEntity(returnedList, HttpStatus.OK);
    }

    @PutMapping("/time-entries/{id}")
    public ResponseEntity update(@PathVariable("id") long timeEntryId, @RequestBody TimeEntry expected) {
        TimeEntry returnedTimeEntry = timeEntryRepository.update(timeEntryId, expected);

        if (returnedTimeEntry == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity(returnedTimeEntry, HttpStatus.OK);
        }
    }

    @DeleteMapping("/time-entries/{id}")
    public ResponseEntity delete(@PathVariable("id") long timeEntryId) {
       timeEntryRepository.delete(timeEntryId);

        return new ResponseEntity(HttpStatus.NO_CONTENT);

    }
}
