package org.example.evaluations.evaluation.services;

import org.example.evaluations.evaluation.dtos.LoginRequest;
import org.example.evaluations.evaluation.dtos.LoginResponse;
import org.example.evaluations.evaluation.dtos.SignupRequest;
import org.example.evaluations.evaluation.dtos.SignupResponse;
import org.example.evaluations.evaluation.exceptions.BadCredentialsException;
import org.example.evaluations.evaluation.exceptions.UserNotSignedUpException;
import org.example.evaluations.evaluation.models.AuthCredential;
import org.example.evaluations.evaluation.models.Session;
import org.example.evaluations.evaluation.models.SessionState;
import org.example.evaluations.evaluation.models.User;
import org.example.evaluations.evaluation.repos.AuthCredentialRepo;
import org.example.evaluations.evaluation.repos.SessionRepo;
import org.example.evaluations.evaluation.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Date;
import java.util.Optional;
import java.util.Random;

@Service
public class AuthService implements IAuthService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AuthCredentialRepo authCredentialRepo;

    @Autowired
    private SessionRepo sessionRepo;

    @Override
    public SignupResponse signup(SignupRequest request) {

        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setAddress(request.getAddress());
        user.setPhoneNumber(request.getPhoneNumber());

        AuthCredential authCredential = new AuthCredential();
        authCredential.setEmail(request.getEmail());
        authCredential.setPassword(request.getPassword());

        AuthCredential savedAuthCredentials = this.authCredentialRepo.save(authCredential);

        user.setAuthCredential(savedAuthCredentials);

        User createdUser = this.userRepo.save(user);

        SignupResponse response = new SignupResponse();
        response.setEmail(request.getEmail());
        response.setFirstName(createdUser.getFirstName());
        response.setLastName(createdUser.getLastName());

        return response;
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) throws UserNotSignedUpException, BadCredentialsException {

        Optional<AuthCredential> credentialOptional = this.authCredentialRepo.findAuthCredentialByEmail(loginRequest.getEmail());

        if(!credentialOptional.isPresent()){
            throw new UserNotSignedUpException("Please sign up first !!");
        }

        AuthCredential credential = credentialOptional.get();

        if(loginRequest.getPassword() != credential.getPassword()){
            throw  new BadCredentialsException(("Please provide correct password !!"));
        }

        Optional<User> userOptional = this.userRepo.findUserByAuthCredential(credential);

        User user = userOptional.get();


        Optional<Session> userSessionsOptional = this.sessionRepo.findSessionByUser(user);

        if(userSessionsOptional.isPresent()) {
            Session userSession = userSessionsOptional.get();

            //if(userSession.getSessionState() == SessionState.ACTIVE){
                this.sessionRepo.deleteById(userSession.getId());
            //}
        }

        Session newUserSession = new Session();
        newUserSession.setSessionState(SessionState.ACTIVE);
        newUserSession.setToken(this.getToken());
        long expireMillis = 172800000;
        Date expireDate = new Date(System.currentTimeMillis() + expireMillis);
        newUserSession.setTtl(expireDate);

        Session savedSession = this.sessionRepo.save(newUserSession);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(savedSession.getToken());
        loginResponse.setUserEmail(loginRequest.getEmail());
        loginResponse.setTokenValidity(savedSession.getTtl());

        return loginResponse;
    }

    private String getToken() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 15) {
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }
}
