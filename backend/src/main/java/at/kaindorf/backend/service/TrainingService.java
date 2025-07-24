package at.kaindorf.backend.service;

import at.kaindorf.backend.dto.TrainingdateDTO;
import at.kaindorf.backend.exceptions.GlobalExceptionHandler;
import at.kaindorf.backend.exceptions.TrainingdateNotFoundException;
import at.kaindorf.backend.mapper.TrainingdateMapper;
import at.kaindorf.backend.model.Course;
import at.kaindorf.backend.model.Classroom;
import at.kaindorf.backend.model.Person;
import at.kaindorf.backend.model.Trainingdate;
import at.kaindorf.backend.repositories.TrainingdateRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TrainingService {
    private final TrainingdateMapper trainingMapper;
    private final TrainingdateRepository trainingdateRepository;

    public List<TrainingdateDTO> findAll() {
        return (trainingdateRepository.findAll()
                .stream()
                .map(trainingMapper::toDTO)
                .toList());
    }

    public List<TrainingdateDTO> findAllByTeacher(Person teacher) {
        return (trainingdateRepository.findTrainingdateByTeacher(teacher)
                .stream()
                .map(trainingMapper::toDTO).
                toList());
    }

    public List<TrainingdateDTO> findAllByParticipant(Person participant) {
        return (trainingdateRepository.findAll()
                .stream().filter(s -> s.getParticipants().contains(participant))
                .map(trainingMapper::toDTO)
                .toList());
    }

    public List<TrainingdateDTO> findAllByCourse(Course course) {
        return (trainingdateRepository.findTrainingdateByCourse(course)
                .stream()
                .map(trainingMapper::toDTO)
                .toList());
    }

    public List<TrainingdateDTO> findAllByClassroom(Classroom classroom) {
        return (trainingdateRepository.findTrainingdateByClassroom(classroom)
                .stream()
                .map(trainingMapper::toDTO)
                .toList());
    }

    public TrainingdateDTO findById(Long id) {
        return trainingdateRepository.findById(id)
                .map(trainingMapper::toDTO)
                .orElseThrow(() -> new TrainingdateNotFoundException(id));
    }

    public void deleteTrainingdate(Long id) {
            Trainingdate date = trainingdateRepository.findById(id)
                    .orElseThrow(() -> new TrainingdateNotFoundException(id));
            trainingdateRepository.delete(date);
        }

    public Long createNewTrainingdate(TrainingdateDTO trainingdateDTO) {
        Trainingdate trainingdate = trainingdateRepository.save(trainingMapper.toEntity(trainingdateDTO));
        return trainingdate.getId();
    }

    public void updateTrainingdate(Long id, TrainingdateDTO trainingdateDTO) {
        Trainingdate trainingdate = trainingdateRepository.findById(id)
                .orElseThrow(() -> new TrainingdateNotFoundException(id));
        Trainingdate newTrainingdate = trainingMapper.toEntity(trainingdateDTO);
        newTrainingdate.setId(trainingdate.getId());
        trainingdateRepository.save(newTrainingdate);
    }
}
