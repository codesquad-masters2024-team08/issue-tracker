package team08.issuetracker.milestone.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import team08.issuetracker.milestone.model.Milestone;
import team08.issuetracker.support.db.WithInsert;

@Repository
public interface MilestoneRepository extends CrudRepository<Milestone, Long> {

}
