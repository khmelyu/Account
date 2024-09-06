package account.service;

import account.model.entity.Users;
import account.model.entity.UsersToTrainings;
import account.model.rep.UsersToTrainingsRepository;
import account.util.Points;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CounterService {

    private final UsersToTrainingsRepository usersToTrainingsRepository;

    public int countVisitedTrainings(Users user) {
        List<UsersToTrainings> usersToTrainings = usersToTrainingsRepository.findByUserAndActualAndPresenceAndTrainingDateBefore(user);
        return usersToTrainings.size();
    }

    public int countPoints(Users users) {
        int totalPoints = 0;

         if (users != null && users.getDepartment() != null && users.getDepartment().equals("Галерея")) {
            totalPoints += Points.FILLING.getPoints();
            // Добавляем баллы за уровень
            switch (users.getLevel()) {
                case "1 уровень":
                    totalPoints += Points.LEVEL_1.getPoints();
                    break;
                case "2 уровень":
                    totalPoints += Points.LEVEL_2.getPoints();
                    break;
                case "3 уровень":
                    totalPoints += Points.LEVEL_3.getPoints();
                    break;
                case "4 уровень":
                    totalPoints += Points.LEVEL_4.getPoints();
                    break;
                case "5 уровень":
                    totalPoints += Points.LEVEL_5.getPoints();
                    break;
            }
            // Добавляем баллы за позицию
            switch (users.getPosition()) {
                case "Заместитель 1 уровень":
                    totalPoints += Points.POSITION_ZAM_1.getPoints();
                    break;
                case "Заместитель 2 уровень":
                    totalPoints += Points.POSITION_ZAM_2.getPoints();
                    break;
                case "Управляющий 1 галереи":
                    totalPoints += Points.POSITION_UPR_1.getPoints();
                    break;
                case "Управляющий 2 галерей":
                    totalPoints += Points.POSITION_UPR_2.getPoints();
                    break;
                case "Управляющий 3 галерей":
                    totalPoints += Points.POSITION_UPR_3.getPoints();
                    break;
                case "Управляющий 4 галерей":
                    totalPoints += Points.POSITION_UPR_4.getPoints();
                    break;
            }

        // Добавляем баллы за стаж
        switch (users.getSeniority()) {
            case "1 год":
                totalPoints += Points.SENIORITY_1.getPoints();
                break;
            case "2 года":
                totalPoints += Points.SENIORITY_2.getPoints();
                break;
            case "3 года":
                totalPoints += Points.SENIORITY_3.getPoints();
                break;
            case "4 года":
                totalPoints += Points.SENIORITY_4.getPoints();
                break;
            case "5 лет":
                totalPoints += Points.SENIORITY_5.getPoints();
                break;
            case "6 лет":
                totalPoints += Points.SENIORITY_6.getPoints();
                break;
            case "7 лет":
                totalPoints += Points.SENIORITY_7.getPoints();
                break;
            case "8 лет":
                totalPoints += Points.SENIORITY_8.getPoints();
                break;
            case "9 лет":
                totalPoints += Points.SENIORITY_9.getPoints();
                break;
            case "10 лет":
                totalPoints += Points.SENIORITY_10.getPoints();
                break;
            case "11 лет":
                totalPoints += Points.SENIORITY_11.getPoints();
                break;
            case "12 лет":
                totalPoints += Points.SENIORITY_12.getPoints();
                break;
            case "13 лет":
                totalPoints += Points.SENIORITY_13.getPoints();
                break;
            case "14 лет":
                totalPoints += Points.SENIORITY_14.getPoints();
                break;
            case "15 лет":
                totalPoints += Points.SENIORITY_15.getPoints();
                break;
            case "16 лет":
                totalPoints += Points.SENIORITY_16.getPoints();
                break;
            case "17 лет":
                totalPoints += Points.SENIORITY_17.getPoints();
                break;
            case "18 лет":
                totalPoints += Points.SENIORITY_18.getPoints();
                break;
            case "19 лет":
                totalPoints += Points.SENIORITY_19.getPoints();
                break;
            case "20 лет":
                totalPoints += Points.SENIORITY_20.getPoints();
                break;
            case "21 год":
                totalPoints += Points.SENIORITY_21.getPoints();
                break;
            case "22 года":
                totalPoints += Points.SENIORITY_22.getPoints();
                break;
            case "23 года":
                totalPoints += Points.SENIORITY_23.getPoints();
                break;
            case "24 года":
                totalPoints += Points.SENIORITY_24.getPoints();
                break;
            case "25 лет":
                totalPoints += Points.SENIORITY_25.getPoints();
                break;
            case "26 лет":
                totalPoints += Points.SENIORITY_26.getPoints();
                break;
            case "27 лет":
                totalPoints += Points.SENIORITY_27.getPoints();
                break;
        }
    }
        return totalPoints;
    }
}
