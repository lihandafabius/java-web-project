/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business;

/**
 *
 * @author FABIUS
 */
public class User {
    private String username;
    private String password;
    public User(){
        password = "";
        username = "";
    }
    
    public User(String username, String password){
        this.password = password;
        this.username = username;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public String getUsername(){
        return username;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public String getPassword(){
        return password;
    }
    
}