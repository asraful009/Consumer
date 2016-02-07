/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cyber009.lib;

import cyber009.obj.DataSet;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pavel
 */
public class Consumer implements Runnable {
    DataSet dataset;
    Map<String, Double> map;
    static double count = 0.0;
    public Consumer(DataSet dataset) {
        this.dataset = dataset;
        map = new TreeMap<>();
    }
    
    @Override
    public void run() {
        while(true) {
            String data = dataset.getProduserData();
            if(data !=null) {
                dataset.setConsumerData(calculateData(data));
            } else {
                try {
                    //System.out.println("----------con: producer does not product any data ....-----------------------");
                    Thread.sleep(400);                    
                } catch (InterruptedException ex) {
                    Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    String calculateData(String data) {
        
        String output = "";
        String [] token =  data.split(" ");
        for(int i=0; i<token.length; i++) {
            map.put(token[i], map.getOrDefault(token[i], 0.0)+1.0);
        }
        map = sortByValue(map);
        count += (double)map.size();
        //output = "count :"+ count+ "\n";
        int top = 5;
        for (Map.Entry<String, Double> entry : map.entrySet()) {
            if(top==0) {
                break;
            }
            top--;
            String key = entry.getKey();
            double value = entry.getValue();
            output += key+" ("+value+"/"+count+"):"+ (value/count) + "\n";
        }
        return output;
    }
    
    
    <K, V extends Comparable<? super V>> Map<K, V> sortByValue( Map<K, V> map ) {
        List<Map.Entry<K, V>> list =
            new LinkedList<Map.Entry<K, V>>( map.entrySet() );
        Collections.sort( list, new Comparator<Map.Entry<K, V>>() {
            @Override
            public int compare( Map.Entry<K, V> o1, Map.Entry<K, V> o2 ) {
                return (o2.getValue()).compareTo( o1.getValue() );
            }
        } );

        Map<K, V> result = new LinkedHashMap<K, V>();
        for (Map.Entry<K, V> entry : list) {
            result.put( entry.getKey(), entry.getValue() );
        }
        return result;
    }
    
}
