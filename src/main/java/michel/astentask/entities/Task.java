package michel.astentask.entities;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "tasks")
@Data
public class Task {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private String status;

    @Column(name = "priority")
    private String priority;

    @Column(name = "estimated_hours")
    private Duration estimatedHours;

    @Column(name = "actual_hours")
    private Duration actualHours;

    @Column(name = "due_date")
    private LocalDateTime dueDate;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assignee_id")
    private User assignee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reporter_id")
    private User reporter;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    private Set<Comment> comments = new HashSet<>();

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    private Set<Timelog> timelogs = new HashSet<>();

    public Task() {}

    public Task(String title, String description, String status, String priority, Duration estimatedHours,
            Duration actualHours, LocalDateTime dueDate, LocalDateTime createdAt, LocalDateTime updatedAt,
            Project project, User assignee, User reporter) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.priority = priority;
        this.estimatedHours = estimatedHours;
        this.actualHours = actualHours;
        this.dueDate = dueDate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.project = project;
        this.assignee = assignee;
        this.reporter = reporter;
    }

}
