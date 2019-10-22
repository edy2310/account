package com.edgar.account.Repositories;

import com.edgar.account.Models.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Integer> {
}
