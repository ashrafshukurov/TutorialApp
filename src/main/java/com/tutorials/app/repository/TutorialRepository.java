package com.tutorials.app.repository;

import com.tutorials.app.model.Tutorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface TutorialRepository extends JpaRepository<Tutorial,Long> {
    List<Tutorial> findByPublished(boolean published);


}
