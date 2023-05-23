package com.mailverify.Temp.mail.verify.Repository;

import com.mailverify.Temp.mail.verify.Model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmailRepo extends JpaRepository<Address,Long> {

    public Address findByEmailAddress(String emailAddress);
    @Query(value = "SELECT DISTINCT SUBSTRING_INDEX(email_address, '@', -1)AS domain FROM address", nativeQuery = true)
    List<String> findAllUniqueDomains();

}
