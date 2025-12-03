package at.kaindorf.backend.service;

import at.kaindorf.backend.dto.ClassroomDTO;
import at.kaindorf.backend.dto.ConcreteCourseDTO;
import at.kaindorf.backend.exceptions.ClassroomNotFoundException;
import at.kaindorf.backend.exceptions.ConcreteCourseNotFoundException;
import at.kaindorf.backend.mapper.ClassroomMapper;
import at.kaindorf.backend.mapper.ConcreteCourseMapper;
import at.kaindorf.backend.model.Classroom;
import at.kaindorf.backend.model.ConcreteCourse;
import at.kaindorf.backend.model.Status;
import at.kaindorf.backend.repositories.ClassroomRepository;
import at.kaindorf.backend.repositories.ConcreteCourseRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClassroomService {
    private static final Logger log = LoggerFactory.getLogger(ClassroomService.class);
    private final ClassroomRepository classroomRepository;
    private final ConcreteCourseRepository concreteCourseRepository;
    private final ClassroomMapper classroomMapper;
    private final ConcreteCourseMapper concreteCourseMapper;

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
        List<Classroom> classrooms = classroomRepository.findClassroomByMinimumAmountOfSeats(seating);
        return classrooms.stream()
                .map(classroomMapper::toDTO)
                .toList();
    }

    public ClassroomDTO findByName(String name) {
        Classroom classroom = classroomRepository.findClassroomByName(name);
        return classroomMapper.toDTO(classroom);
    }

    public Long bookClassroom(ConcreteCourseDTO creation) {
        Classroom classroom = classroomRepository.findClassroomById(creation.getClassroom().getId());

        if (classroom == null) {
            throw new ClassroomNotFoundException(creation.getClassroom().getId());
        }

        List<ConcreteCourse> existingConcreteCourses = classroom.getCourses();
        LocalDateTime newStart = creation.getStart();
        LocalDateTime newEnd = creation.getEnd();

        if (newStart == null || newEnd == null || newStart.isAfter(newEnd)) {
            throw new RuntimeException("Ungültiger Zeitraum für den neuen Termin.");
        }

        for (ConcreteCourse existingConcreteDate : existingConcreteCourses) {
            LocalDateTime existingStart = existingConcreteDate.getStartTime();
            LocalDateTime existingEnd = existingConcreteDate.getEndTime();

            if (existingStart == null || existingEnd == null) {
                throw new RuntimeException("Schulungstermin ID " + existingConcreteDate.getId() + " hat ungültige Daten.");
            }

            if (newStart.isBefore(existingEnd) && newEnd.isAfter(existingStart)) {
                throw new RuntimeException("Lehrsaal " + classroom.getName() + " ist bereits belegt (Überlappung mit Termin ID: " + existingConcreteDate.getId() + ").");
            }
        }

        log.info("Lehrsaal " + classroom.getName() + " ist verfügbar für den Zeitraum " + newStart + " bis " + newEnd + ".");

        ConcreteCourse newBooking = concreteCourseMapper.toEntity(creation);
        newBooking.setClassroom(classroom);
        newBooking.setStatus(Status.PLANNED);
        newBooking = concreteCourseRepository.save(newBooking);
        return newBooking.getId();

    }

    public void deleteBooking(ConcreteCourseDTO dto) {
        Classroom classroom = classroomRepository.findById(dto.getClassroom().getId())
                .orElseThrow(() -> new ClassroomNotFoundException(dto.getClassroom().getId()));

        ConcreteCourse termin = concreteCourseRepository.findConcreteCourseByClassroomAndStartDateAndEndDate(
                classroom, dto.getStart(), dto.getEnd());

        if (termin != null) {
            concreteCourseRepository.delete(termin);
            log.info("Schulungstermin erfolgreich gelöscht.");
        }
    }

    public void updateBooking(Long id, ConcreteCourseDTO concreteCourseDTO)
    {

        ConcreteCourse concreteCourse = concreteCourseRepository.findById(id)
                .orElseThrow(() -> new ConcreteCourseNotFoundException(id));
        ConcreteCourse newConcreteCourse = concreteCourseMapper.toEntity(concreteCourseDTO);

        newConcreteCourse.setId(concreteCourse.getId());
        concreteCourseRepository.save(newConcreteCourse);
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
