package com.avishek.accounts.service;

import com.avishek.accounts.constants.AccountsConstants;
import com.avishek.accounts.dto.CustomerDto;
import com.avishek.accounts.entity.Accounts;
import com.avishek.accounts.entity.Customer;
import com.avishek.accounts.exception.CustomerAlreadyExistsException;
import com.avishek.accounts.mapper.CustomerMapper;
import com.avishek.accounts.repository.AccountsRepository;
import com.avishek.accounts.repository.CustomerRespository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService{

    AccountsRepository accountsRepository;
    CustomerRespository customerRespository;

    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
        Optional<Customer> optionalCustomer = customerRespository.findByMobileNumber(customerDto.getMobileNumber());
        Customer savedCustomer = customerRespository.save(customer);
        if (optionalCustomer.isPresent())
            throw new CustomerAlreadyExistsException("Customer already exists!"+customerDto.getMobileNumber());
        accountsRepository.save(createNewAccount(savedCustomer));

    }

    private Accounts createNewAccount(Customer customer){
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);
        
        return newAccount;
    }
}
