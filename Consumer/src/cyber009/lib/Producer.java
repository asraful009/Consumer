/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cyber009.lib;

import cyber009.obj.DataSet;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pavel
 */
public class Producer implements Runnable {

    DataSet dataset;
    Random rand;
    String charSet = "qwertyuiopasdfghjklzxcvbnm";
    public Producer(DataSet dataset) {
        rand = new Random(System.currentTimeMillis());
        this.dataset = dataset;
        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    String data = dataset.getConsumerData();
                    if(data !=null) {
                        System.out.println("================final data=================\n" + data+ "========================\n");
                    } else {
                        try {
                            //System.out.println("----------pro: consumer does not process any data ....-----------------------");
                            Thread.sleep(400);                    
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        });
        th.start();
    }
    
    @Override
    public void run() {
        while(true) {
            dataset.setProduserData(createData(rand.nextInt(10000)));
        }
    }
    
    String createData(int wcount) {
        String str = "";
        int size = 0;
        for(int i=0; i<wcount; i++) {
            size = rand.nextInt(2)+3;
            for(int j=0; j<size; j++) {
                str += charSet.charAt(rand.nextInt(charSet.length()));
            }
            str +=  " ";
        }        
        return str;
    }
}
