package michel.astentask.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "timelogs")
@Data
public class Timelog {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private Long id;

    @Column(name = "hours_worked")
    private Float hoursWorked;

    @Column(name = "description")
    private String description;

    @Column(name = "log_date")
    private LocalDateTime logDate;

    //pertence a uma tarefa
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id")
    private Task task;

    //usuario que fez o timelog
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Timelog() {}

    public Timelog(Long id, Float hoursWorked, String description, LocalDateTime logDate, Task task, User user) {
        this.id = id;
        this.hoursWorked = hoursWorked;
        this.description = description;
        this.logDate = logDate;
        this.task = task;
        this.user = user;
    }

}
