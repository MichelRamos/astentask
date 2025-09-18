package michel.astentask.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import michel.astentask.entities.Project;

@Repository
public interface ProjectsRepository extends JpaRepository<Project, Long> {

    @Query("SELECT p FROM Project p JOIN p.members m WHERE p.owner.id = :userId OR m.id = :userId")
    Page<Project> findByOwnerOrMember(@Param("userId") Long userId, Pageable pageable);
    
}
