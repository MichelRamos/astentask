package michel.astentask.entities;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import michel.astentask.enums.TaskPriority;
import michel.astentask.enums.TaskStatus;

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

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private TaskStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "priority")
    private TaskPriority priority;

    @Column(name = "estimated_hours")
    private Float estimatedHours;

    @Column(name = "actual_hours")
    private Float actualHours;

    @Column(name = "due_date")
    private LocalDateTime dueDate;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    //pertence a um projeto
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    //responsavel pela tarefa
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assignee_id")
    private User assignee;

    //tem um reportante
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reporter_id")
    private User reporter;

    //tem comentarios
    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    private Set<Comment> comments = new HashSet<>();

    //tem timelogs
    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    private Set<Timelog> timelogs = new HashSet<>();

    public Task() {}

    public Task(String title, String description, TaskStatus status, TaskPriority priority, Float estimatedHours,
            Float actualHours, LocalDateTime dueDate, LocalDateTime createdAt, LocalDateTime updatedAt,
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
