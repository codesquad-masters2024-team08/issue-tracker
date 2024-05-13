package team08.issuetracker.milestone.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import team08.issuetracker.milestone.model.Milestone;

@Repository
public interface MilestoneRepository extends CrudRepository<Milestone, Long> {

    @Query("SELECT COUNT(*) FROM milestone WHERE is_open = true")
    long countOpenedMilestones();

    @Query("SELECT COUNT(*) FROM milestone WHERE is_open = false")
    long countClosedMilestones();

}
