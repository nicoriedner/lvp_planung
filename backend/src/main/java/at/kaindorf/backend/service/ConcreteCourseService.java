package at.kaindorf.backend.service;

import at.kaindorf.backend.dto.ConcreteCourseDTO;
import at.kaindorf.backend.dto.ConcreteCourseDetailsDTO;
import at.kaindorf.backend.dto.WeeklyCoursesDTO;
import at.kaindorf.backend.exceptions.ConcreteCourseNotFoundException;
import at.kaindorf.backend.mapper.ConcreteCourseMapper;
import at.kaindorf.backend.model.Classroom;
import at.kaindorf.backend.model.ConcreteCourse;
import at.kaindorf.backend.model.Trainee;
import at.kaindorf.backend.model.Trainer;
import at.kaindorf.backend.repositories.ConcreteCourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConcreteCourseService {
    private final ConcreteCourseMapper concreteCourseMapper;
    private final ConcreteCourseRepository concreteCourseRepository;

    public List<ConcreteCourseDTO> findAll() {
        return (concreteCourseRepository.findAll()
                .stream()
                .map(concreteCourseMapper::toDTO)
                .toList());
    }

    public List<ConcreteCourseDTO> findAllByTrainer(Trainer trainer) {
        return (concreteCourseRepository.findConcreteCourseByTrainer(trainer)
                .stream()
                .map(concreteCourseMapper::toDTO).
                toList());
    }

    public List<ConcreteCourseDTO> findAllByTrainees(Trainee trainee) {
        return (concreteCourseRepository.findAll()
                .stream().filter(s -> s.getTrainees().contains(trainee))
                .map(concreteCourseMapper::toDTO)
                .toList());
    }

    public List<ConcreteCourseDTO> findAllByClassroom(Classroom classroom) {
        return (concreteCourseRepository.findConcreteCourseByClassroom(classroom)
                .stream()
                .map(concreteCourseMapper::toDTO)
                .toList());
    }

    public ConcreteCourseDTO findById(Long id) {
        return concreteCourseRepository.findById(id)
                .map(concreteCourseMapper::toDTO)
                .orElseThrow(() -> new ConcreteCourseNotFoundException(id));
    }

    public void delete(Long id) {
        ConcreteCourse date = concreteCourseRepository.findById(id)
                .orElseThrow(() -> new ConcreteCourseNotFoundException(id));
        concreteCourseRepository.delete(date);
    }

    public Long create(ConcreteCourseDTO concreteCourseDTO) {
        ConcreteCourse concreteCourse = concreteCourseRepository.save(concreteCourseMapper.toEntity(concreteCourseDTO));
        return concreteCourse.getId();
    }

    public void update(Long id, ConcreteCourseDTO concreteCourseDTO) {
        ConcreteCourse concreteCourse = concreteCourseRepository.findById(id)
                .orElseThrow(() -> new ConcreteCourseNotFoundException(id));
        ConcreteCourse newConcreteCourse = concreteCourseMapper.toEntity(concreteCourseDTO);
        newConcreteCourse.setId(concreteCourse.getId());
        concreteCourseRepository.save(newConcreteCourse);
    }

    public List<WeeklyCoursesDTO> getConcreteCoursesForWeekKey(String weekKey) {
        String[] tokens = weekKey.split("-W");
        int year = Integer.parseInt(tokens[0]);
        int weekOfYear = Integer.parseInt(tokens[1]);
        return concreteCourseRepository.getConcreteCourseForWeekKey(year, weekOfYear);
    }

    public ConcreteCourseDetailsDTO getConcreteCourseDetails(long id) {
        return concreteCourseRepository.getConcreteCourseDetails(id);
    }
}
