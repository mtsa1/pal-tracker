package io.pivotal.pal.tracker;

import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    private HashMap<Long, TimeEntry> repository = new HashMap<Long, TimeEntry>();
    private Long id = 1L;


    public TimeEntry create(TimeEntry timeEntry) {
        timeEntry.setId(id);
        repository.put(timeEntry.getId(), timeEntry);
        id = id+1;
        return repository.get(timeEntry.getId());
    }

    public TimeEntry find(long id) {
        return repository.get(id);
    }

    public List<TimeEntry> list() {
        return new ArrayList(repository.values());
    }

    public TimeEntry update(long id, TimeEntry timeEntry) {
        timeEntry.setId(id);
        repository.replace(id, timeEntry);
        return repository.get(id);
    }

    public void delete(long id) {
        repository.remove(id);

    }
}
