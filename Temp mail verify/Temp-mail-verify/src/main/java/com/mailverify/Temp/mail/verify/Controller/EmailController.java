package com.mailverify.Temp.mail.verify.Controller;

import com.mailverify.Temp.mail.verify.Model.Address;
import com.mailverify.Temp.mail.verify.Service.EmailService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/email")
public class EmailController {

    private final EmailService service;

    public EmailController(EmailService service) {
        this.service = service;
    }

    @GetMapping("/validate/syntax/{email}")
    public String emailSyntaxCheck(@PathVariable String email)
    {
        if(this.service.emailSyntaxCheck(email))
            return ("Email provided has the required syntax");

        return ("Email provided does not have the required syntax");

    }

    @GetMapping("/validate/{email}")
    public String validateEmail(@PathVariable String email){
        return this.service.validateEmail(email);
    }
    @PostMapping("/save/{address}/{disposable}/{spam}")
    public Address saveAddress(@PathVariable String address,@PathVariable boolean disposable,@PathVariable boolean spam)
    {
        return this.service.saveAddress(address,disposable,spam);
    }


}
