package michel.astentask.entities;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "user_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    //projetos que o usuario eh dono
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private Set<Project> ownedProjects = new HashSet<>();

    //projetos que o usuario eh membro
    @ManyToMany(mappedBy = "member")
    private Set<Project> memberProjects = new HashSet<>();

    //tarefas que o usuario eh responsavel
    @OneToMany(mappedBy = "assignee")
    private Set<Task> assignedTasks = new HashSet<>();

    //tarefas que o usuario eh reportante
    @OneToMany(mappedBy = "reporter")
    private Set<Task> reportedTasks = new HashSet<>();

    //comentarios do usuario
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private Set<Comment> comments = new HashSet<>();

    //timelogs do usuario
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Timelog> timelogs = new HashSet<>();

    public User() {}

    public User(String name, String email, String password, 
            LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime deletedAt, 
            Set<Role> roles, Set<Project> ownedProjects, Set<Project> memberProjects, 
            Set<Task> assignedTasks, Set<Task> reportedTasks, Set<Comment> comments, Set<Timelog> timelogs) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        this.roles = roles;
        this.ownedProjects = ownedProjects;
        this.memberProjects = memberProjects;
        this.assignedTasks = assignedTasks;
        this.reportedTasks = reportedTasks;
        this.comments = comments;
        this.timelogs = timelogs;
    }

}
