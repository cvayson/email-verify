package com.mailverify.Temp.mail.verify.Service;



import com.mailverify.Temp.mail.verify.Model.Address;
import com.mailverify.Temp.mail.verify.Repository.EmailRepo;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class EmailService {

    private final EmailRepo repo;

    public EmailService(EmailRepo repo) {
        this.repo = repo;
    }

    public boolean emailSyntaxCheck(String email) {
        String EMAIL_REGEX =
                "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

        Pattern pattern=Pattern.compile(EMAIL_REGEX);
        Matcher matcher= pattern.matcher(email);

        return matcher.matches();
    }
    public Address saveAddress(String address,Boolean disposable,Boolean spam)
    {
        Address addressNew=new Address(address,disposable,spam);
        return repo.save(addressNew);
    }

    public String validateEmail(String email)
    {
        if(!this.emailSyntaxCheck(email))
        {
            return "Incorrect syntax used";
        }

        Address addressTemp=repo.findByEmailAddress(email);
        String domain=email.substring(email.indexOf("@")+1);
        List<String> domains=repo.findAllUniqueDomains();
        if(addressTemp!=null)
        {
            if(addressTemp.getSpam())
                return "Email address recognized as spam";
            if(domains.contains(domain))
                return "Email address recognised as disposable";

        }

        return "Email is OK for use";
    }
}
