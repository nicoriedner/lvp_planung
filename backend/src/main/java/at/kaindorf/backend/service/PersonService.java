package at.kaindorf.backend.service;

import at.kaindorf.backend.dto.TraineeDTO;
import at.kaindorf.backend.dto.TrainerDTO;
import at.kaindorf.backend.exceptions.PersonNotFoundException;
import at.kaindorf.backend.mapper.TraineeMapper;
import at.kaindorf.backend.mapper.TrainerMapper;
import at.kaindorf.backend.model.Competence;
import at.kaindorf.backend.model.Trainee;
import at.kaindorf.backend.model.Trainer;
import at.kaindorf.backend.repositories.TraineeRepository;
import at.kaindorf.backend.repositories.TrainerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService {
    private final TrainerRepository trainerRepository;
    private final TraineeRepository traineeRepository;
    private final TrainerMapper trainerMapper;
    private final TraineeMapper traineeMapper;

    /*            TRAINER             */

    public List<TrainerDTO> findAllTrainer() {
        List<Trainer> trainers = trainerRepository.findAll();
        return trainers.stream()
                .map(trainerMapper::toDTO)
                .toList();
    }

    public TrainerDTO findTrainerById(Long id) {
        Trainer trainer = trainerRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
        return trainerMapper.toDTO(trainer);
    }

    public List<TrainerDTO> findTrainerByCompetences(List<Competence> competences) {
        List<Trainer> trainers = trainerRepository.findTrainerByCompetences(competences);
        return trainers.stream()
                .map(trainerMapper::toDTO)
                .toList();
    }

    public List<TrainerDTO> findTrainerByLastname(String lastname) {
        List<Trainer> trainers = trainerRepository.findTrainerByLastname(lastname);
        return trainers.stream()
                .map(trainerMapper::toDTO)
                .toList();
    }

    public Long createNewTrainer(TrainerDTO trainerDTO) {
        Trainer trainer = trainerMapper.toEntity(trainerDTO);
        return trainerRepository.save(trainer).getId();
    }

    public void deleteTrainer(Long id) {
        Trainer trainer = trainerRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
        trainerRepository.delete(trainer);
    }

    public void updateTrainer(Long id, TrainerDTO trainerDTO) {
        Trainer trainer = trainerRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));

        Trainer newTrainer = trainerMapper.toEntity(trainerDTO);
        newTrainer.setId(trainer.getId());
        trainerRepository.save(newTrainer);
    }

    /*            TRAINEE             */

    public List<TraineeDTO> findAllTrainees() {
        List<Trainee> trainees = traineeRepository.findAll();
        return trainees.stream()
                .map(traineeMapper::toDTO)
                .toList();
    }

    public TraineeDTO findTraineeById(Long id) {
        Trainee trainee = traineeRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
        return traineeMapper.toDTO(trainee);
    }

    public List<TraineeDTO> findTraineeByLastname(String lastname) {
        List<Trainee> trainees = traineeRepository.findTraineeByLastname(lastname);
        return trainees.stream()
                .map(traineeMapper::toDTO)
                .toList();
    }

    public Long createNewTrainee(TraineeDTO traineeDTO) {
        Trainee trainee = traineeMapper.toEntity(traineeDTO);
        return traineeRepository.save(trainee).getId();
    }

    public void deleteTrainee(Long id) {
        Trainee trainee = traineeRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
        traineeRepository.delete(trainee);
    }

    public void updateTrainee(Long id, TraineeDTO traineeDTO) {
        Trainee trainee = traineeRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));

        Trainee newTrainee = traineeMapper.toEntity(traineeDTO);
        newTrainee.setId(trainee.getId());
        traineeRepository.save(newTrainee);
    }
}
