package tk.aurelmarishta.imagegallery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import tk.aurelmarishta.imagegallery.dao.ConfirmationTokenRepository;
import tk.aurelmarishta.imagegallery.model.ConfirmationToken;
import tk.aurelmarishta.imagegallery.model.User;
import tk.aurelmarishta.imagegallery.service.MailService;
import tk.aurelmarishta.imagegallery.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

    final static String prefix = "user/";

    UserService userService;
    ConfirmationTokenRepository tokenDao;
    MailService mailSender;
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserController(UserService theUserService, ConfirmationTokenRepository theTokenDao, BCryptPasswordEncoder encoder, MailService theMailSender) {
        userService = theUserService;
        tokenDao = theTokenDao;
        mailSender = theMailSender;
        bCryptPasswordEncoder = encoder;
    }


    @GetMapping("")
    public String curretUserProfile() {
        return prefix + "myProfile";
    }

    @GetMapping("/register")
    public String displayRegistration(Model theModel) {
        User tempUser = new User();
        theModel.addAttribute("user", tempUser);

        return prefix + "register";
    }


    @PostMapping("/save")
    public String newUser(@ModelAttribute("user") User tempUser, Model theModel) {

        User existingUser = userService.findByEmailIgnoreCase(tempUser.getEmail());
        if (existingUser != null) {

            return "";
        } else {

            String encodedPassword = bCryptPasswordEncoder.encode(tempUser.getPassword());
            tempUser.setPassword(encodedPassword);

            System.out.println("\n\n\n " + tempUser + "\n\n\n");

            userService.save(tempUser);

            ConfirmationToken confirmationToken = new ConfirmationToken(tempUser);

            tokenDao.save(confirmationToken);

            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(tempUser.getEmail());
            mailMessage.setSubject("Complete Registration!");
            mailMessage.setFrom("aurel.marishta@atis.com");
            mailMessage.setText("To confirm your account, please click here : "
                    + "http://localhost:8080/users/confirm-account?token=" + confirmationToken.getConfirmationToken());

            System.out.println(mailMessage);

            mailSender.sendEmail(mailMessage);

            theModel.addAttribute("user", tempUser);


            return prefix + "successfulRegisteration";
        }


    }

    @RequestMapping(value = "/confirm-account", method = {RequestMethod.GET, RequestMethod.POST})
    public String confirmUserAccount(Model theModel, @RequestParam("token") String confirmationToken) {
        ConfirmationToken token = tokenDao.findByConfirmationToken(confirmationToken);

        if (token != null) {
            User user = userService.findByEmailIgnoreCase(token.getUser().getEmail());
            user.setEnabled(true);
            userService.save(user);
            return "redirect:/login";
        } else {
            return "";
        }


    }


}