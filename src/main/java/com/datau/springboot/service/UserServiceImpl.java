package com.datau.springboot.service;


import com.datau.springboot.bean.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service("UserService")
public class UserServiceImpl implements UserService{
    private static final AtomicLong connter = new AtomicLong();
    private static List<User> users;
    static{
        users = populateDummyUsers();
    }
    private static List<User> populateDummyUsers(){
        List<User> users = new ArrayList<User>();
        users.add(new User(connter.incrementAndGet(),"sam",30,1000));
        users.add(new User(connter.incrementAndGet(),"xiaoming",29,2000));
        users.add(new User(connter.incrementAndGet(),"lihua",28,3000));
        users.add(new User(connter.incrementAndGet(),"xiaolei",27,4000));
        users.add(new User(connter.incrementAndGet(),"yangshuo",26,5000));
        users.add(new User(connter.incrementAndGet(),"xiaoxiang",25,6000));
        return users;
    }

    @Override
    public User findById(long id) {
        for(User user : users){
            if(user.getId() == id ){
                return user;
            }
        }
        return null;
    }

    @Override
    public User findByName(String name) {
        for(User user : users){
            if(user.getName().equalsIgnoreCase(name)){
                return user;
            }
        }
        return null;
    }

    @Override
    public void saveUser(User user) {
        user.setId(connter.incrementAndGet());
        users.add(user);
    }

    @Override
    public void updateUser(User user) {
        int index = users.indexOf(user);
        System.out.println(index);
        users.set(index,user);
    }

    @Override
    public void deleteUserById(long id) {
        for(Iterator<User> iterator = users.iterator(); iterator.hasNext();){
            User user = iterator.next();
            if(user.getId() == id){
                iterator.remove();
            }
        }
    }

    @Override
    public List<User> findAllUsers(){

        return users;
    }

    @Override
    public void deleteAllUsers() {
        users.clear();
    }

    @Override
    public boolean isUserExist(User user) {
        return findByName(user.getName())!= null;

    }


}
