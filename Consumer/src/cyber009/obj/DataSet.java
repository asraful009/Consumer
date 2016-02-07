/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cyber009.obj;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author pavel
 */
public class DataSet {
    Queue<String> consumer;
    Queue<String> produser;
    
    public DataSet() {
        consumer = new ArrayDeque<>();
        produser = new ArrayDeque<>();
    }
    
    public String getProduserData() {
        return produser.poll();
    }
    
    public void setProduserData(String data) {
        produser.add(data);        
    }
    
    public String getConsumerData() {
        return consumer.poll();
    }
    
    public void setConsumerData(String data) {
        consumer.add(data);        
    }
}
