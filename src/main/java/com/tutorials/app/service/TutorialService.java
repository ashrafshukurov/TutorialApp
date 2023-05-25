package com.tutorials.app.service;

import com.tutorials.app.dto.TutorialDto;
import com.tutorials.app.model.Tutorial;

import java.util.List;

public interface TutorialService {
    int addBook(TutorialDto tutorialDto);
    int updateTutorial(TutorialDto tutorialDto, Long id);
    TutorialDto getById(Long id);
    int deleteById(Long id);
    List<TutorialDto> getAll();
    List<TutorialDto> getByPublished(boolean published);
    int deleteAll();

}
