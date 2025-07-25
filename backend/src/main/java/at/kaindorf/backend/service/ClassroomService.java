package at.kaindorf.backend.service;

import at.kaindorf.backend.dto.ClassroomDTO;
import at.kaindorf.backend.dto.TrainingdateDTO;
import at.kaindorf.backend.exceptions.ClassroomNotFoundException;
import at.kaindorf.backend.exceptions.TrainingdateNotFoundException;
import at.kaindorf.backend.mapper.ClassroomMapper;
import at.kaindorf.backend.mapper.TrainingdateMapper;
import at.kaindorf.backend.model.Classroom;
import at.kaindorf.backend.model.Trainingdate;
import at.kaindorf.backend.model.Status;
import at.kaindorf.backend.repositories.ClassroomRepository;
import at.kaindorf.backend.repositories.TrainingdateRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ClassroomService {
    private static final Logger log = LoggerFactory.getLogger(ClassroomService.class);
    private final ClassroomRepository classroomRepository;
    private final ClassroomMapper classroomMapper;
    private final TrainingdateRepository trainingdateRepository;
    private final TrainingdateMapper trainingdateMapper;

    public List<ClassroomDTO> findAll() {
        List<Classroom> classrooms = classroomRepository.findAll();
        return classrooms.stream()
                .map(classroomMapper::toDTO)
                .toList();
    }

    public ClassroomDTO findById(Long id) {
        Classroom classroom = classroomRepository.findById(id)
                .orElseThrow(() -> new ClassroomNotFoundException(id));
        return classroomMapper.toDTO(classroom);
    }

    public List<ClassroomDTO> findAllWithMinSeating(int seating) {
        List<Classroom> classrooms = classroomRepository.findClassroomBySeatingGreaterThanEqual(seating);
        return classrooms.stream()
                .map(classroomMapper::toDTO)
                .toList();
    }

    public ClassroomDTO findByName(String name) {
        Classroom classroom = classroomRepository.findClassroomByName(name);
        return classroomMapper.toDTO(classroom);
    }

    public List<ClassroomDTO> findByTrainingdate(Trainingdate trainingdate) {
        return classroomRepository.findClassroomByTrainingdate(trainingdate)
                .stream()
                .map(classroomMapper::toDTO)
                .toList();
    }

    public Long bookClassroom(TrainingdateDTO creation) {
        Classroom classroom = classroomRepository.findClassroomById(creation.getClassroom().getId());

        if (classroom == null) {
            throw new ClassroomNotFoundException(creation.getClassroom().getId());
        }

        List<Trainingdate> existingTrainingdates = classroom.getTrainingdates();
        LocalDateTime newStart = creation.getStartDate();
        LocalDateTime newEnd = creation.getEndDate();

        if (newStart == null || newEnd == null || newStart.isAfter(newEnd)) {
            throw new RuntimeException("Ungültiger Zeitraum für den neuen Termin.");
        }

        for (Trainingdate existingTrainingdate : existingTrainingdates) {
            LocalDateTime existingStart = existingTrainingdate.getStartDate();
            LocalDateTime existingEnd = existingTrainingdate.getEndDate();

            if (existingStart == null || existingEnd == null) {
                throw new RuntimeException("Schulungstermin ID " + existingTrainingdate.getId() + " hat ungültige Daten.");
            }

            if (newStart.isBefore(existingEnd) && newEnd.isAfter(existingStart)) {
                throw new RuntimeException("Lehrsaal " + classroom.getName() + " ist bereits belegt (Überlappung mit Termin ID: " + existingTrainingdate.getId() + ").");
            }
        }

        log.info("Lehrsaal " + classroom.getName() + " ist verfügbar für den Zeitraum " + newStart + " bis " + newEnd + ".");

    Trainingdate newBooking = trainingdateMapper.toEntity(creation);
    newBooking.setClassroom(classroom);
    newBooking.setStatus(Status.PLANNED);
    newBooking = trainingdateRepository.save(newBooking);
        return newBooking.getId();

    }

    public void deleteBooking(TrainingdateDTO dto) {
        Classroom classroom = classroomRepository.findById(dto.getClassroom().getId())
                .orElseThrow(() -> new ClassroomNotFoundException(dto.getClassroom().getId()));

        Trainingdate termin = trainingdateRepository.findTrainingdateByClassroomAndStartDateAndEndDate(
                classroom, dto.getStartDate(), dto.getEndDate());

        if (termin != null) {
            trainingdateRepository.delete(termin);
            log.info("Schulungstermin erfolgreich gelöscht.");
        }
    }

    public void updateBooking(Long id, TrainingdateDTO trainingdateDTO)
    {

        Trainingdate trainingdate = trainingdateRepository.findById(id)
                .orElseThrow(() -> new TrainingdateNotFoundException(id));
        Trainingdate newTrainingdate = trainingdateMapper.toEntity(trainingdateDTO);

        newTrainingdate.setId(trainingdate.getId());
        trainingdateRepository.save(newTrainingdate);
    }

    public Long createNewClassroom(ClassroomDTO classroomDTO) {
        Classroom classroom = classroomMapper.toEntity(classroomDTO);
        return classroomRepository.save(classroom).getId();
    }

    public void deleteClassroom(Long id) {
        if (!classroomRepository.existsById(id)) {
            throw new ClassroomNotFoundException(id);
        }
        classroomRepository.deleteById(id);
    }

    public void updateClassroom(Long id, ClassroomDTO classroomDTO) {
        if(classroomRepository.findById(id).isPresent()) {
            throw new ClassroomNotFoundException(id);
        }
        Classroom newClassroom = classroomMapper.toEntity(classroomDTO);
        newClassroom.setId(id);
        classroomRepository.save(newClassroom);
    }
}
