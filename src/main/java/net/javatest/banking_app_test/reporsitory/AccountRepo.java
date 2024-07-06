
package net.javatest.banking_app_test.reporsitory;

import org.springframework.data.jpa.repository.JpaRepository;

import net.javatest.banking_app_test.entity.Account;

public interface AccountRepo extends JpaRepository<Account, Long> {

    
}