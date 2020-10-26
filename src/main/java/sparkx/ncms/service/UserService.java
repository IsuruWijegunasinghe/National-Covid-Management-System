package sparkx.ncms.service;

import sparkx.ncms.dao.User;
import sparkx.ncms.repository.UserRepo;

public class UserService {
    public User authenticateUser(String username){
        UserRepo userRepo = new UserRepo();
        return userRepo.authenticateUser(username);
    }
}
