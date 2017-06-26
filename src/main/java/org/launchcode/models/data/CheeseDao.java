package org.launchcode.models.data;

import org.launchcode.models.Cheese;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by LaunchCode
 */
@Repository                                                             //indicates that the interface below is an actual repository and should be managed for us
@Transactional                                                          //specifies that all the methods in this interface should be wrapped by a database transaction (one interaction/transaction with he database)
public interface CheeseDao extends CrudRepository<Cheese, Integer> {    //DAO = Data Access Object (serves as the interface through which we interact with the database)
}

//says that we are storing Cheese objects and that the keys for those Cheese objects will be Integers
//(can't be int because you can't use primitive types in the context of collections)
//CrudRepository defines a contract - specifies several methods that will allow us to put data into database and take data out