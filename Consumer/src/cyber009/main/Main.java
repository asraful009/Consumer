/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cyber009.main;

import cyber009.lib.Consumer;
import cyber009.lib.Producer;
import cyber009.obj.DataSet;

/**
 *
 * @author pavel
 */
public class Main {
    public static void main(String[] args) {
        DataSet data = new DataSet();
        Producer producer = new Producer(data);
        Consumer consumer = new Consumer(data);
        Thread tPro = new Thread(producer);
        Thread tCon = new Thread(consumer);
        tPro.start();
        tCon.start();
    }
}
