/*
 * First you have to add weka.jar to your project external libraries
 */

import java.util.*;

import weka.associations.AssociationRule;
import weka.associations.AssociationRules;
import weka.associations.BinaryItem;
import weka.associations.FPGrowth;

import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class FpGrowth {
    public ArrayList<Double> time_fp;
    public ArrayList<Double> sup_min_fp;

    public void weka_fp() throws Exception{

        System.out.println("Weka loaded.");

        String dataset = "C:\\Program Files\\Weka-3-9\\data\\supermarket.arff";

        DataSource source = new DataSource(dataset);

        Instances data=source.getDataSet();

        sup_min_fp= new ArrayList<Double>();
        sup_min_fp.add(0.05);
        sup_min_fp.add(0.06);
        sup_min_fp.add(0.07);
        sup_min_fp.add(0.08);
        sup_min_fp.add(0.09);
        sup_min_fp.add(0.1);
        sup_min_fp.add(0.11);
        sup_min_fp.add(0.12);
        sup_min_fp.add(0.13);
        sup_min_fp.add(0.14);
        sup_min_fp.add(0.15);

        List<AssociationRule> ruleList = null;
        time_fp = new ArrayList<Double>();

        for ( int i = 0; i < sup_min_fp.size() ; i++) {
            double first_time = System.nanoTime()/1000000000.0;
            FPGrowth fpgrowth_model = new FPGrowth();
            String[] options = {"-N", "10000", "-C", "0.9", "-M", Double.toString(sup_min_fp.get(i))};
            fpgrowth_model.setOptions(options);
            fpgrowth_model.buildAssociations(data);
            AssociationRules ARS = fpgrowth_model.getAssociationRules();
            ruleList = ARS.getRules();

            double time = System.nanoTime()/1000000000.0 -first_time;
            time_fp.add(time/60.0);
        }

        for (int i = 0; i < ruleList.size(); i++) {
            System.out.println(ruleList.get(i));
        }
        System.out.println(ruleList.size());

        for (int i = 0; i <time_fp.size() ; i++) {
            System.out.println(time_fp.get(i));
            System.out.println(sup_min_fp.get(i));
        }


    }

    public static void main(String[] args) throws Exception {
        FpGrowth fpGrowth = new FpGrowth();
        fpGrowth.weka_fp();
    }
}
